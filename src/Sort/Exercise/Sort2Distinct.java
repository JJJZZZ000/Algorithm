package Sort.Exercise;

public class Sort2Distinct {
    public static void sort(Comparable[] a){
        int lt = 0;
        int i = 1;
        int gt = a.length - 1;

        while(i <= gt){
            int cmp = a[i].compareTo(a[lt]);
            if(cmp < 0) exch(a, lt++, i++);
            if(cmp > 0) exch(a, i, gt--);
            else i++;
        }
    }

    private static void exch(Comparable[] a, int lo, int hi){
        Comparable temp = a[lo];
        a[lo] = a[hi];
        a[hi] = temp;
    }

    public static void main(String[] args) {

        // parse command-line argument as an array of 1-character strings
        Comparable[] a = {0,1,0,1,0,1};

        // sort a print results
        sort(a);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i]);
        System.out.println();
    }


}
