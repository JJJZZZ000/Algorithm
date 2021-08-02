package Sort;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN){
        keys = (Key[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for(int i = 0; i < maxN+1; i++) qp[i] = -1;
    }

    public boolean isEmpty(){ return N == 0; }

    public boolean contains(int k){ return qp[k] != -1;}

    public void insert(int k, Key key){
        N++;
        pq[N] = k;
        qp[k] = N;
        keys[k] = key;
        swim(N);
    }

    public Key min(){ return keys[pq[1]]; }

    public int delMin(){
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N+1]] = null;
        qp[pq[N+1]] = -1;
        return indexOfMin;
    }

    public int minIndex(){ return pq[1]; }

    public void change(int k, Key key){
        keys[k] = key;
        sink(qp[k]);
        swim(qp[k]);
    }

    public void delete(int k){
        keys[k] = null;
        exch(qp[k], N--);
        sink(qp[k]);
        swim(qp[k]);
        qp[k] = -1;
    }

    public void swim(int k){
        while(k>1 && less(k, k/2)){
            exch(k/2, k);
            k = k/2;
        }
    }

    public void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j+1, j)) j++;
            if(!less(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    public void exch(int i, int j){
        int temp_qp = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = temp_qp;

        int temp_pq = pq[i];
        pq[i] = pq[j];
        pq[j] = temp_pq;

    }

    public boolean less(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    public static void main(String[] args){
        IndexMinPQ a = new IndexMinPQ(3);
        a.insert(1,2);
        a.insert(2,3);
        a.insert(3, 1);
        a.change(1,0);

        Comparable b = a.min();

        System.out.print(b);

    }
}
