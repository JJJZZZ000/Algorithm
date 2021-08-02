package Sort.Exercise;

import Function.ArrayUtils;

public class Quick3wayPartition {
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int lo, int hi){
        Comparable temp = a[lo];
        a[lo] = a[hi];
        a[hi] = temp;
    }

    public static void sort(Comparable[] a){
//        ArrayUtils.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static boolean eq(Comparable a, Comparable b){
        return a.compareTo(b)==0;
    }

    private static void sort(Comparable[] a, int lo, int hi){

        if(lo>=hi) return;

        int[] num = partition(a, lo, hi);

        sort(a, lo, num[0]);

        sort(a, num[1], hi);

    }

    public static int[] partition(Comparable[] a, int lo, int hi){
        int i = lo+1, p = lo;
        int j = hi, q = hi+1;
        Comparable v = a[lo];
        while(i<=j){
            // 好像没用上，下面的代码i和j不存在相等情况，且ij不会被强制限制在边界内
//            if(i == j){
//                if(a[i].equals(v)) exch(a, ++p, i++);
//                break;
//            }
            while(!less(v, a[i])){
                if(less(a[i],v)) i++;
                else exch(a,++p,i++);
                if(i>hi) break;
            }
            while(!less(a[j], v)){
                if(less(v,a[j])) j--;
                else exch(a,--q, j--);
                if(j<lo) break;
            }

            if(i < j) exch(a, i, j);
        }
        int a0 = i-1;
        int a1 = j+1;

        for(int k = lo; k <= p; k++) exch(a, k, a0--);
        for(int k = hi; k >= q; k--) exch(a, k, a1++);

        int[] num = {a0, a1};
        return num;
    }

    public static void main(String[] args){
        Comparable[] a = {5,5,8,1,0,7,3,2,2,0,6,1,1};


        sort(a);
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i]);
        }
    }
}
