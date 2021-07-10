package Sort;
// 最坏情况需要N^2 / 2次交换和比较。最好好情况N-1次比较和0次交换
// 索引i的左侧始终是有序的
public class InsertionSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 1; i < N; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
//
//    public static void sort(Comparable[] a){
//        // faster (do not use exchange function)
//        int N = a.length;
//        for(int i = 1; i < N; i++){
//            Comparable temp = a[i];
//            int j = i;
//            while(j > 0 && less(temp, a[j-1])){
//                a[j] = a[j-1];
//                j--;
//            }
//            a[j] = temp;
//        }
//    }


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
