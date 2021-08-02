package Sort;

public class HeapSort<Key extends Comparable<Key>> {
    public static void sink(Comparable[] a, int k, int N){
        while(2*k < N){
            int j = 2*k+1;
            if(j < N && less(a, j, j+1)) j++;
            if(!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public static boolean less(Comparable[] a, int i, int j){return a[i].compareTo(a[j])<0;}

    public static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(Comparable[] a){
        int N = a.length;
        for(int k = N/2-1; k >= 0; k--) sink(a, k, N-1);

        while(N>=1){
            exch(a, 0, --N);
            sink(a, 0, N-1);
        }

    }
    public static void print(Comparable[] a){
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i]);
    }
    public static void main(String[] args){
        Comparable[] a = {1,5,8,1,0,7,3,2,2,0,6};


        sort(a);
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
        }
    }
}
