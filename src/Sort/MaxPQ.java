package Sort;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(){
        pq = (Key[]) new Comparable[2];
    }

    public MaxPQ(Key[] a){
        int N = a.length;
        pq = (Key[])new Comparable[N+1];
        this.N = N;
        for(int i = 1; i <= N; i++) pq[i] = a[i-1];

        for(int i = N/2; i > 0; i--) {sink(i);}

    }

    public void resize(int n){
        Key[] newpq = (Key[]) new Comparable[n];
        for(int i = 1; i < pq.length; i++){
            newpq[i] = pq[i];
        }
        pq = newpq;
    }

    public boolean isEmpty(){ return N==0; }

    public int size(){ return N; }

    public void insert(Key v){
        if(N == pq.length-1) resize(2*pq.length);
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if(N == (pq.length-1)/4) resize((pq.length-1)/4+1);
        return max;
    }

    public Key showMax(){ return pq[1];}

    private boolean less(int i, int j){ return pq[i].compareTo(pq[j])<0;}

    private void exch(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k){
        while(2*k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args){
//        MaxPQ a = new MaxPQ();
//        a.insert(2);
//        a.insert(3);
//        a.insert(1);

        Comparable[] temp = {2,3,1};
        MaxPQ a = new MaxPQ(temp);


        Comparable b = a.delMax();

        System.out.print(b);

    }
}
