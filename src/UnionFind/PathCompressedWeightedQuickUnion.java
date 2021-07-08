package UnionFind;

public class PathCompressedWeightedQuickUnion {
    private int[] id;
    private int[] sz;
    private int count;

    public PathCompressedWeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        int root = p;
        while (root != id[root]) root = id[root];
        while (p != root){
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        count--;
    }


    public static void main(String[] args) {}
}
