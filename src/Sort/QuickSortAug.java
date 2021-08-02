package Sort;

import Function.ArrayUtils;

public class QuickSortAug {
    public static void sort(Comparable[] a){
        ArrayUtils.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(lo<=hi+10)  {InsertionSort.sort(a); return;}
        if(hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        Comparable v = a[lo];

        int lo_new;
        int lo_new_1;
        int lo_new_2;
        if(a.length>2) {
            for (int k = 0; k < 3; k++) {
                lo_new = lo + k;
                lo_new_1 = lo + (k + 1) % 3;
                lo_new_2 = lo + (k + 2) % 3;

                if (less(a[lo_new], a[lo_new_1]) && less(a[lo_new_2], a[lo_new])) exch(a, lo, lo_new_1);
                if (less(a[lo_new], a[lo_new_2]) && less(a[lo_new_1], a[lo_new])) exch(a, lo, lo_new_1);
            }
        }

        while(true){
            while(less(a[++i], v)) if(i == hi) break;
            while(less(v, a[--j])) if(j == lo) break;

            if(i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
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
//        Comparable[] a = {9,8,7,6,5,4,3,2,1};
//        sort(a);
//        System.out.println();
//        for(int i = 0; i < a.length; i++)
//            System.out.print(a[i]);
        int i = 0;
        while(i++<5);
        System.out.print(i);
    }
}
