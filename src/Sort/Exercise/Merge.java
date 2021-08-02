package Sort.Exercise;


public class Merge {
    public static Comparable[] Merge(Comparable[] a, Comparable[] b){
        Comparable[] c = new Comparable[a.length+b.length];
        int N = c.length;
        int i = 0;
        int j = 0;
        for(int n = 0; n < N; n++){
            if(i>a.length-1) c[n] = b[j++];
            else if(j>b.length-1) c[n] = a[i++];
            else if(less(a[i], b[j])) c[n] = a[i++];
            else c[n] = b[j++];
        }
        return c;
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
        Comparable[] a = {1,3,5};
        Comparable[] b = {2,4,6};
        Comparable[] c = Merge(a,b);
        for(int i = 0; i<c.length;i++)
            System.out.print(c[i]);
    }
}
