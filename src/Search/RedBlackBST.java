package Search;

// 红黑树 = 二叉树的查找 + 2-3树的插入
// 红链接的结点表示3结点， 黑链接的点表示普通上下级关系
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color; // x.color表示x和其父节点链接的颜色;
        Node (Key key, Value val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isEmpty(){ return root == null;}

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color==RED;
    }

    private int size(Node x){
        if(x == null) return 0;
        return size(x.left)+1+size(x.right);
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key, Value val){
        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node h, Key key, Value val){
        if(h == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if(cmp < 0){ h.left = put(h.left, key, val);}
        else if(cmp > 0){ h.right = put(h.right, key, val);}
        else h.val = val;

        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedLeft(Node h){
        flipColors(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if(!isEmpty()) root.color = BLACK;
    }

    public Node deleteMin(Node h){
       if(h.left == null) return null;
       if(!isRed(h.left) && !isRed(h.left.left))
           h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    public Node balance(Node h){
        if(isRed(h.right)) h = rotateLeft(h);
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


}
