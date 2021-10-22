package Strings;

public class ViolentSubstring {
    public static void main(String[] args) {
        String pat = "hoiajh";
        String txt = "fjauiofjhoiajhfuoiajhfo";
        int i = search2(pat, txt);
        System.out.println(i);

        String trun = txt.substring(i, i+pat.length());
        System.out.println(trun);
    }

    public static int search(String pat, String txt){
        int M = pat.length();
        int N = txt.length();
        for(int i = 0; i <= N-M; i++){
            int j;
            for(j = 0; j < M; j++){
                if(pat.charAt(j) != txt.charAt(i+j)) break;
                if(j == M-1) return i;
            }
        }
        return N;
    }

    public static int search2(String pat, String txt){
        int N = txt.length();
        int M = pat.length();
        int i, j;
        for(i=0, j=0; i < N && j < M; i++){
            if(pat.charAt(j) == txt.charAt(i)) j++;
            else{ i -= j; j = 0; }
        }
        if(j == M) return i-M;
        else return N;
    }
}
