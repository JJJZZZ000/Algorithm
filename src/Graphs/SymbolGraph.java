package Graphs;
import Function.In;
import Search.BinarySearchST;

public class SymbolGraph {
    private BinarySearchST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String stream, String sp) {
        st = new BinarySearchST<String, Integer>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);

            for (int i = 0; i < a.length; i++)
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
        }
        keys = new String[st.size()];
        for (String name : st.keys())
            keys[st.get(name)] = name;

        G = new Graph(st.size());
        in = new In (stream);
        while(in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contain(String s) { return st.contains(s); }

    public int index(String s) { return st.get(s); }

    public String name(int v) {return keys[v]; }

    public Graph G() { return G;}
}
