package Graphs;

import edu.princeton.cs.algs4.Queue;

public class KosarajuSCC {
    private boolean[] marked;
    public int[] id;
    private int count;

    public KosarajuSCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());

        for(int s : order.reversePost())
            if(!marked[s]){
                dfs(G, s);
                count++;
            }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v))
            if(!marked[w]) dfs(G, w);
    }

    public boolean stronglyConnected(int v, int w){ return id[v] == id[w]; }

    public int id(int v){ return id[v]; }

    public int count(){ return count; }

    public static void main(String[] args) {
        Digraph G = new Digraph(5);
        G.addEdge(4, 0);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 3);
        G.addEdge(2, 3);
        G.addEdge(3, 4);

        KosarajuSCC a = new KosarajuSCC(G);
        for(int i = 0; i < a.id.length; i++)
            System.out.print(a.id[i]);
    }
}
