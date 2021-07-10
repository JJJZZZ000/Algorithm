package Sort;


public class SortCompare {
    public static double time(String alg, Comparable[] a){
        long startTime=System.currentTimeMillis();
        if(alg.equals("Insertion")) InsertionSort.sort(a);
        if(alg.equals("Selection")) SelectionSort.sort(a);
        if(alg.equals("Shell")) ShellSort.sort(a);

        long endTime=System.currentTimeMillis();
        long time = endTime-startTime;
        return time;
    }

    public static double timeRandomInput(String alg, int N, int T){
        double total = 0.0;
        Double[] a = new Double[N];
        for(int t = 0; t < T; t++){
            for(int i = 0; i < N; i++)
                a[i] = Math.random();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args){
//        String alg1 = args[0];
//        String alg2 = args[1];
//        int N = Integer.parseInt(args[2]);
//        int T = Integer.parseInt(args[3]);
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Shell";
        int N = 10000;
        int T = 100;

        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        double t3 = timeRandomInput(alg3, N, T);
        System.out.print(alg1+":"+alg2);
        System.out.println();
        System.out.printf("%.1f", t1/t2);
        System.out.println();
        System.out.print(alg1+":"+alg3);
        System.out.println();
        System.out.printf("%.1f", t1/t3);

    }
}
