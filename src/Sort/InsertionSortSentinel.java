package Sort;

public class InsertionSortSentinel {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int exchange = 0;
        for (int i = N - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchange++;
            }
        }

        if (exchange == 0) return;

        for(int i = 2; i < N; i++){
            Comparable temp = a[i];
            int j = i;
            while(less(temp, a[j-1])){
                a[j] = a[j-1];
                j--;
            }
            a[j] = temp;
        }
    }



    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w)<0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }

    public static void main(String[] args){
//        String[] a = In.readStrings();
//        sort(a);
//        assert isSorted(a);
//        show(a);
    }
}
