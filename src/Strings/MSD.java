package Strings;

public class MSD {
    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int d){
        if(d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    public static void insertion_sort(String[] a, int lo, int hi, int d){
        for(int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a,j,j-1);
    }

    private static void exch(String[] a, int i, int j){
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(String v, String w, int d){
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void sort(String[] a, int lo, int hi, int d){
        if(hi <= lo+M) {
            insertion_sort(a, lo, hi, d); return;
        }
        int[] count = new int[R+2];
        for(int i = lo; i <= hi; i++)
            count[charAt(a[i], d) + 1 + 1]++;
        for(int r = 0; r < R+1; r++)
            count[r+1] += count[r];
        for(int i = lo; i <= hi; i++)
            aux[count[charAt(a[i],d)+1]++] = a[i];
        for(int i = lo; i <= hi; i++)
            a[i] = aux[i-lo];

        for(int r = 0; r < R; r++)
            sort(a, lo+count[r], lo+count[r+1]-1, d+1);


    }

    public static void main(String[] args){
        String[] a = new String[14];
        a[0] = "she";
        a[1] = "sells";
        a[2] = "seashells";
        a[3] = "by";
        a[4] = "the";
        a[5] = "sea";
        a[6] = "shore";
        a[7] = "the";
        a[8] = "shells";
        a[9] = "she";
        a[10] = "sells";
        a[11] = "are";
        a[12] = "surely";
        a[13] = "seashells";
        sort(a);
        for(int i = 0; i < 14; i++)
            System.out.println(a[i]);
    }


}
