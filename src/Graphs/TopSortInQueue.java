
package Graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


public class TopSortInQueue {
    private Queue<Integer> queue = new Queue<Integer>();

    public int[] inDegree(Digraph G){
        int[] indegree = new int[G.V()];
        for(int s = 0; s < G.V(); s++){
            for(int w : G.adj(s))
                indegree[w]++;
        }
        return indegree;
    }

    public TopSortInQueue(Digraph G) {
        int[] indegree = inDegree(G);
        boolean[] inQueue = new boolean[G.V()];
        int cnt = 0;
        int i = 0;

        while (cnt < G.V()) {
            int v = i % G.V();
            if(!inQueue[v] && indegree[v] == 0){
                queue.enqueue(v);
                inQueue[v] = true;
                for(int w : G.adj(v))
                    indegree[w]--;
                cnt = 0;
            }
            cnt++;
            i++;
        }
    }

    public Iterable<Integer> TopSort(){
        return queue;
    }

    public static void main(String[] args){
        Digraph G = new Digraph(5);
        G.addEdge(4,0);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,3);
        G.addEdge(2,3);
        TopSortInQueue DG = new TopSortInQueue(G);
        Queue<Integer> queue = (Queue<Integer>)DG.TopSort();
        for(Integer i : queue)
            System.out.print(i);
    }

}