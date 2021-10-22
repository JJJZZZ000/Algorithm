package Graphs;

import UnionFind.QuickFind;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.UF;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for(Edge e : G.edges())
            pq.insert(e);
        QuickFind uf = new QuickFind(G.V());
        while(!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double count = 0;
        for(Edge e : edges())
            count += e.weight();
        return count;
    }
}
