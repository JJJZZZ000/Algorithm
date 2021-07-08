package UnionFind;

public class QuickFind {
    private int[] id;
    private int count;

    public QuickFind(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++) id[i] = i;
    }

    public int count(){ return count;}

    public boolean connected(int p, int q){ return find(p) == find(q);}

    public int find(int p){ return id[p]; }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot) return;

        for(int i = 0; i < id.length; i++)
            if(id[i]==pRoot) id[i] = qRoot;

        count--;
    }


    public static void main(String[] args){ }

}
