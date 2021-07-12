package Sort.Exercise;

public class NaturalMerge {
    private static Comparable[] aux;
    public static void merge(Comparable[] a, int lo, int mid, int hi){



        // 假设前后半段都分别排序好了
        int i = lo;
        int j = mid+1;

        for(int k = lo; k <= hi; k++){
            aux[k] = a[k];
        }

        for(int k = lo; k <= hi; k++) {
            if(i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static int find(Comparable[] a, int q) {
        int i = q;
        if(i == a.length-1) return i;
        while(i<a.length-1 && !less(a[i+1], a[i])){
            i++;
        }
        return i;
    }

    public static void printa(Comparable[] a){
        for(int j = 0; j < a.length; j++)
        {System.out.print(a[j]); }
        System.out.println();
    }



    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        while(!isSorted(a)){
            int i = 0;

            while(i < a.length){
                int lo = i;
                int mid = find(a, i);
                int hi ;

//                System.out.println("lo:"+lo);
//                System.out.println("mid:"+mid);
//                System.out.println("hi:"+hi);

                if(mid < a.length-1) {
                    hi = find(a, mid + 1);
//                    System.out.println("in");
//                    System.out.println("hi:"+hi);

                    merge(a, lo, mid, hi);
//                    printa(a);
                    i = hi + 1;
                }
                else break;
            }
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
        Comparable[] a = {1,2,3,5,6,7,4,9,8};

        sort(a);
        printa(a);


    }
}
