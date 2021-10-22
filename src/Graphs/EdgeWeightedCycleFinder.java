package Graphs;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private DirectedEdge edgeTo[];
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;



    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G){
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        for(int v = 0; v < G.V(); v++)
            if(!marked[v]) dfs(G, v);
    }

    private void dfs(EdgeWeightedDigraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for(DirectedEdge w_edge : G.adj(v)){
            int w = w_edge.to();
            if(this.hasCycle()) return;
            else if(!marked[w]) {
                edgeTo[v] = w_edge; dfs(G, w); }
            else if(onStack[w]){
                cycle = new Stack<DirectedEdge>();
                for(DirectedEdge x = w_edge; x.from() != v; x = edgeTo[x.from()])
                    cycle.push(x);
                cycle.push(w_edge);
            }
            onStack[v] = false;
        }
    }
    public boolean hasCycle(){ return cycle != null; }

    public Iterable<DirectedEdge> cycle(){ return cycle; }

}
