package Graphs;

// CC
public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public ConnectedComponent(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s = 0; s < G.V(); s++){
            if(!marked[s]){
                dfs(G, s);
                count++;
            }
        }

    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : G.adj(v))
            dfs(G, w);
    }

    public boolean connected(int v, int w){ return id[v] == id[w];}

    public int count(){ return count; }

    public int id(int v){ return id[v]; }
}
