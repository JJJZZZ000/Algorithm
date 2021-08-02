package Sort;

public class MedianPQ<Key extends Comparable<Key>> {
    private MaxPQ max = new MaxPQ();
    private MinPQ min = new MinPQ();
    private Key median = null;

    public void insert(Key key){
        int maxN = max.size();
        int minN = min.size();
        if(maxN == 0 && minN == 0) {max.insert(key); median = key; return;}
        if(less(key, median)) max.insert(key);
        else min.insert(key);

        Key temp;
        if((maxN+minN)%2 == 0){
            if(maxN>=minN) {
                while (maxN > minN) {
                    temp = (Key) max.delMax();
                    maxN--;
                    min.insert(temp);
                    minN++;
                }
                median = (Key)max.showMax();
            }
            else{
                while (maxN < minN) {
                    temp = (Key) min.delMin();
                    minN--;
                    max.insert(temp);
                    maxN++;
                }
                median = (Key)min.showMin();
            }

        }
        else{
            if(maxN>minN){
                while(maxN>minN+1){
                    temp = (Key)max.delMax();
                    maxN--;
                    min.insert(temp);
                    minN++;
                }
                median = (Key)max.showMax();
            }
            else {
                while(minN>maxN+1){
                    temp = (Key)min.delMin();
                    minN--;
                    max.insert(temp);
                    maxN++;
                }
                median = (Key)min.showMin();
            }
        }
    }

    public Key median(){
        return median;
    }

    public void delMedian(){
        int maxN = max.size();
        int minN = min.size();
        if(maxN==minN){
            max.delMax();
            min.delMin();
            median = (Key)max.showMax();
        }
        else if(maxN>minN){
            max.delMax();
            median = (Key)max.showMax();
        }
        else {
            min.delMin();
            median = (Key)max.showMax();
        }
    }

    public boolean less(Key a, Key b){
        return a.compareTo(b)<0;
    }

    public static void main(String[] args){
        MedianPQ<Integer> a = new MedianPQ<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        a.insert(4);
        a.insert(5);
        a.delMedian();
        System.out.println(a.median());
    }
}
