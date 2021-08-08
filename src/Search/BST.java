package Search;

import Sort.Exercise.QueueNode;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, Node left, Node right, int N){
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.N = N;
        }
    }
    public int size(){ return size(root); }

    private int size(Node x){
        if(x == null) return 0;
        return x.N;
    }

    public Value get(Key key){ return get(root, key);}

    public Value get(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val){
        if(x == null) return new Node(key, val, null, null, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if(cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){ return min(root).key; }

    public Node min(Node x){
        if(x.left==null) return x;
        else return min(x.left);
    }

    public Key max(){ return max(root); }

    public Key max(Node x){
        if(x.right==null) return x.key;
        else return max(x.right);
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if(x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        else if(cmp <0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t != null) return t;
        else return x;
    }

    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if(x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        else if(cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null) return t;
        else return x;
    }

    public Key select(int k){
//        if(k>=size(root)) return null;
        return select(root, k).key;
    }

    public Node select(Node x, int k){
        if(x==null) return null;
        int t = size(x.left);
        if(t < k) return select(x.right, k-t-1);
        if(t > k) return select(x.left, k);
        else return x;
    }

    public int rank(Key key){
        return rank(root, key);
    }

    public int rank(Node x, Key key){
        if(x == null) return 0;
        int t = size(x.left);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(x.left, key);
        if(cmp > 0) return rank(x.right, key)+t+1;
        else return t;
    }

    public void deleteMin(){ root = deleteMin(root); }

    public Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){ root = delete(root, key); }

    public Node delete(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp > 0) return delete(x.right, key);
        if(cmp < 0) return delete(x.left, key);
        else{
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x){
        if(x == null) return;
        print(x.left);
        System.out.print(x.key);
        print(x.right);
    }

    public Iterable<Key> keys(){ return keys(min(), max());}

    public Iterable<Key> keys(Key lo, Key hi){
        QueueNode<Key> queue = new QueueNode<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, QueueNode<Key> queue, Key lo, Key hi){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, queue, lo, hi);
        if(cmplo <= 0  && cmphi >= 0)  queue.enqueue(x.key);
        if(cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int height(){ return height(root);}

    public int height(Node x) {
        if(x == null) return 0;
        int left_height = height(x.left);
        int right_height = height(x.right);
        return Math.max(left_height, right_height)+1;
    }


}
