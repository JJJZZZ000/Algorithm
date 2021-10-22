package Graphs;

import Sort.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty()) visit(G, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            int w = e.other(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges(){
        Queue<Edge> queue = new Queue<Edge>();
        for(int i = 1; i < edgeTo.length; i++)
            queue.enqueue(edgeTo[i]);
        return queue;
    }

    public double weight(){
        double count = 0;
        for(int i = 0; i < distTo.length; i++)
            count += distTo[i];
        return count;
    }
}
