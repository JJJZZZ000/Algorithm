package Sort;

public class TwosidePQ<Key extends Comparable<Key>> {
    private MaxPQ max = new MaxPQ();
    private MinPQ min = new MinPQ();

    public void insert(Key key){
        max.insert(key);
        min.insert(key);
    }

    public Key min(){ return (Key)min.showMin(); }

    public Key max(){ return (Key)max.showMax(); }

    public Key delMax(){
        if(less(max.showMax(),min.showMin())) return null;
        return (Key)max.delMax();
    }

    public Key delMin(){
        if(less(max.showMax(),min.showMin())) return null;
        if(min.showMin() != max.showMax()) return (Key)min.delMin();
        else return (Key)max.delMax();
    }

    public boolean less(Comparable<Key> a, Comparable<Key> b){
        return a.compareTo((Key)b) < 0;
    }

    public static void main(String[] args){
        TwosidePQ<Integer> a = new TwosidePQ<Integer>();
        a.insert(2);
        a.insert(3);
        a.insert(3);
        a.insert(1);

        Comparable<Integer> b = a.delMax();
        Comparable<Integer> c = a.delMin();
        Comparable<Integer> d = a.delMin();
        Comparable<Integer> e = a.delMin();
        Comparable<Integer> f = a.delMin();

        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);

    }

}
