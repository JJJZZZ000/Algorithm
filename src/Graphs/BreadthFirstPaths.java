package Graphs;

import Function.QueueNode;

import java.util.Stack;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s  = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        QueueNode<Integer> queue = new QueueNode<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : G.adj(v)){
                marked[w] = true;
                edgeTo[w] = v;
                queue.enqueue(w);
            }
        }
    }

    public BreadthFirstPaths(Digraph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s  = s;
        bfs(G, s);
    }

    private void bfs(Digraph G, int s){
        QueueNode<Integer> queue = new QueueNode<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : G.adj(v)){
                marked[w] = true;
                edgeTo[w] = v;
                queue.enqueue(w);
            }
        }
    }

    public boolean hasPathTo(int v){ return marked[v]; }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x != s; x = edgeTo[v])
            path.push(x);
        path.push(s);
        return path;

    }

    public int distTo(int v){
//        int count = 0;
//        while(v != s){
//            v = edgeTo[v];
//            count++;
//        }
//        return count;
        int count = 0;
        for(Integer i : pathTo(v))
            count++;
        return count;
    }
}
