package Strings;

// Least-Significant-Digit First
// 低位优先的字符串排序
public class LSD {
    public static void sort(String[] a, int W){
        // 通过前W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for(int d = W-1; d >= 0; d--){
            // 根据第d个字符用键索引计数法排序
            int[] count = new int[R+1];
            for(int i = 0; i < N; i++)
                count[a[i].charAt(d)+1]++;
            for(int r = 0; r < R; r++)
                count[r+1] += count[r];
            for(int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];
            for(int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

//    public static void main(String[] args){
//        String a = "abcde";
//        System.out.print(a.charAt(0));
//        int[] count = new int[257];
//    }
}
