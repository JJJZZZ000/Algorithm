package Graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class DirectedDFS {
//    private Digraph G;
//    private int s;
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for(int s : sources)
            if(!marked[s]) dfs(G, s);
    }

    private void dfs(Digraph G, int s){
        marked[s] = true;
        for(int w : G.adj(s))
            if(!marked[w]) dfs(G, w);
    }

    public boolean marked(int v){ return marked[v]; }

    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<Integer>();
        for(int i = 1; i < args.length; i++)
            sources.add(Integer.parseInt(args[i]));
        DirectedDFS reachable = new DirectedDFS(G, sources);
        for(int v = 0; v < G.V(); v++)
            if(reachable.marked(v))
                System.out.print(v + " ");
        System.out.println();
    }
}
