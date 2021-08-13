package Graphs;

//import Function.Bag;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i = 0; i < E; i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public Graph copy(){
        int E_copy = E;
        Bag<Integer>[] adj_copy = (Bag<Integer>[]) new Bag[V];
        for(int i = 0; i < V; i++)
            for(Integer j : adj[i])
                adj_copy[i].add(j);
        Graph G_copy = new Graph(V);
        G_copy.E = E_copy;
        G_copy.adj = adj_copy;
        return G_copy;

    }

    public int V(){ return V; }

    public int E(){ return E; }


    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }


    public static int degree(Graph G, int v) {
        int degree = 0;
        for(int w : G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G){
        int max = 0;
        for(int v = 0; v < G.V(); v++)
            if(degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    public static double avgDegree(Graph G){ return 2.0 * G.E() / G.V();}

    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        for(int v = 0; v < G.V(); v++)
            for(int w : G.adj(v))
                if(v == w) count++;
        return count / 2;
    }

    public String toString(){
        String s = V + "vertices, " + E + "edges\n";
        for(int v = 0; v < V; v++){
            s += v + ": ";
            for(int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.print(G);
    }


}
