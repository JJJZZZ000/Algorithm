package Sort;

public class MergeSortAug {
//    private static Comparable[] aux;
    public static void merge(Comparable[] a, Comparable[] b, int lo, int mid, int hi){
        // 假设前后半段都分别排序好了
        int i = lo;
        int j = mid+1;

        for(int k = lo; k <= hi; k++) {
            if(i > mid) b[k] = a[j++];
            else if (j > hi) b[k] = a[i++];
            else if(less(a[j],a[i])) b[k] = a[j++];
            else b[k] = a[i++];
        }
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] aux, Comparable[]a, int lo, int hi){
        if(hi - lo > 15) {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux,mid + 1, hi);
            if (!less(aux[mid], aux[mid + 1])) // 判断数组是否已经有序
                for(int i = 0; i < aux.length; i++)
                    a[i] = aux[i];
            else merge(aux, a, lo, mid, hi);
        }
        // 对短数组使用插入排序可以提升10%性能
        else InsertionSort.sort(a);
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
