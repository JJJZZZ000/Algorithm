package Graphs;
// 间隔度数
public class DegreesOfSeparation {
    public static void main(String[] args){
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph G = sg.G();

        String source = args[2];

        if(!sg.contain((source))){
            System.out.println(source + "not in database.");
            return;
        }

        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        String sink = args[3];
        if(sg.contain(sink)){
            int t = sg.index(sink);
            if(bfs.hasPathTo(t)){
                for(int v : bfs.pathTo(t))
                    System.out.println("    " + sg.name(v));
            }
            else System.out.println("Not connected");
        }
        else System.out.println("Not in database");
    }
}
