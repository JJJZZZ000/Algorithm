package Sort;

import Sort.Exercise.QueueNode;

public class HeapSortNode <Key extends Comparable<Key>>{
//    private Node root;
//    private Node latest;
//    private int N = 0;
//    private QueueNode<Node> f_node;
//
//    private class Node{
//        Key key;
//        Node up;
//        Node left;
//        Node right;
//    }
//
//    public void insert(Key key){
//        N++;
//        Node newp = new Node();
//        newp.key = key;
//        latest = newp;
////        Node oldfirst = first;
////        first = newp;
//        if(f_node.isEmpty()) {f_node.enqueue(newp); root = newp; }
//        else{
//            Node father = f_node.first();
//            if(father.left != null) {father.left = newp; newp.up = father;}
//            else {father.right = newp; newp.up = father; f_node.dequeue();}
//        }
//        f_node.enqueue(newp);
//        swim(newp);
//    }
//
//    public Key delMax(){
//        N--;
//        Key key = root.key;
//
//        if(root==latest){root = null; latest = null;}
//        else{
//            exch(root, latest);
//            if(latest.up.right != null) {latest = latest.up.left; latest.up.right = null;f_node.add(latest.up);}
//            else{latest = latest.up; latest.left = null;}
//            sink(root);
//        }
//        return key;
//    }
//
//    public void swim(Node p){}
//
//

    private Node root;
    private class Node{
        private Key key;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key){
            this.key = key;
//            this.left = left;
//            this.right = right;
//            this.N = N;
        }
    }

    public int size(Node x){
        if(x == null) return 0;
        return x.N;
    }

    public void insert(Key key){
        root = insert(root, key);
    }

    public int compare(Node a, Node b){
        if(a == null) return -1;
        if(a.key == null) return -1;
        if(b == null) return 1;
        if(b.key == null) return 1;
        return a.key.compareTo(b.key);
    }

    public Node insert(Node x, Key key){
        if(x == null) { Node new_x = new Node(key); new_x.N = 1; return new_x;}
        int cmp = key.compareTo(x.key);
        // key >= x.key
        if(cmp>=0){
            Node new_x = new Node(key);
            // left <= right
            if(compare(x.left,x.right)<=0) {
                new_x.left = insert(x.left, x.key);
                new_x.right = x.right;
            }
            // left > right
            else{
                new_x.right = insert(x.right, x.key);
                new_x.left = x.left;
            }
            new_x.N = size(new_x.right) + size(new_x.left) + 1;
            return new_x;
        }
        // key < x.key
        // left <= right
        else if(compare(x.left,x.right)<=0){
            x.left = insert(x.left, key);
        }
        // left > right
        else{
            x.right = insert(x.right, key);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node sink(Node x){
        if(x==null) return null;
        if(x.left == null && x.right==null) return x;
        Node empty = new Node(null);
        if(x.left == null) x.left = empty;
        if(x.right == null) x.right = empty;

        if(compare(x,x.left)<0 || compare(x,x.right)<0){
            if(compare(x.left,x.right)>=0){
                Key temp_key = x.key;
                x.key = x.left.key;
                x.left.key = temp_key;
                x.left = sink(x.left);
            }
            else{
                Key temp_key = x.key;
                x.key = x.right.key;
                x.right.key = temp_key;
                x.right = sink(x.right);
            }
        }
        return x;
    }

    public Key delMax(){
        Key temp_key = root.key;
        root.key = null;
        sink(root);
        return temp_key;
    }

    public boolean isEmpty(){ return root == null || root.key==null;}

    public Key[] sort(){
        Key[] a = (Key[]) new Comparable[size(root)];
        int i = 0;
        while(!isEmpty()){
            Key max = delMax();
            a[i++] = max;
        }
        return a;
    }

    public static void main(String[] args){
        HeapSortNode a = new HeapSortNode();
        for(int i = 0; i < 10; i++){
            a.insert(i);
        }
        Comparable[] b = a.sort();
        for(int i = 0; i < 10; i++){
            System.out.print(b[i]);
        }
    }

}
