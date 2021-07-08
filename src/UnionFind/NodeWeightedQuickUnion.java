package UnionFind;


public class NodeWeightedQuickUnion<Item> {

    public int count;

    public class Node{
        Item item;
        int size;
        Node pre;

        public Node(Item item){
            size = 1;
            this.item = item;
            this.pre = null;
        }
    }

    public int count(){ return count;}

    public boolean connected(Node p, Node q){ return find(p)==find(q);}

    public Node find(Node p){
        while(p.pre != null) p = p.pre;
        return p;
    }

    public void union(Node p, Node q){
        Node pRoot = find(p);
        Node qRoot = find(q);
        if(pRoot == qRoot) return;

        if(pRoot.size > qRoot.size){
            qRoot.pre = pRoot;
            pRoot.size += qRoot.size;
        }
        else{
            pRoot.pre = qRoot;
            qRoot.size += pRoot.size;
        }
        count--;
    }
    public static void main(String[] args){

    }

}
