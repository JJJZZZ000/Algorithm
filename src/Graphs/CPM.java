package Graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 优先级限制下的并行任务调度问题的关键路径方法
// 多优先级的最短时间 = 图中的最长路径  => 既能追求优先级的先后顺序，无优先级的任务也能同时进行
public class CPM {
    public static void main(String[] args){
        int N = StdIn.readInt(); StdIn.readLine();
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(2*N + 2);
        int s = 2*N, t = 2*N+1;
        for(int i = 0; i < N; i++){
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i+N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, t, 0.0));
            for(int j = 1; j < a.length; j++){
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i+N, successor, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);
        StdOut.println("Start times");
        for(int i = 0; i < N; i++)
            StdOut.printf("%4d:%5.1f\n",i, lp.distTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
