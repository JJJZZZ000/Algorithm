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

    public Value get(Key key){
        Node x = get(root, key);
        if(x == null) return null;
        return x.val;
    }

    private Node get(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp > 0) return get(x.right, key);
        return get(x.left, key);
    }

    private boolean isEmpty(){ return root == null;}

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color==RED;
    }

    public Key min(){
        Node x = root;
        while(x.left != null)
            x = x.left;
        return x.key;
    }
    public Key max(){
        Node x = root;
        while(x.right != null)
            x = x.right;
        return x.key;
    }

    public Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    public Node max(Node x){
        if(x.right == null) return x;
        return max(x.right);
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

        h = balance(h);

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

    private Node moveRedRight(Node h){
        flipColors(h);
        if(isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }


    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h){
        if(isRed(h.left)) h = rotateRight(h);
        if(h.right == null) return null;
        if(!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void deleteMax_test(){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax_test(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax_test(Node h){
        if(h.right == null) return h.left;
        if(!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax_test(h.right);
        return balance(h);
    }

    public Node balance(Node h){
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void delete(Key key){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if(!isEmpty()) root.color = BLACK;
    }

    public Node delete(Node h, Key key){
        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
            return balance(h);
        }
        else{
            if(isRed(h.left))
                h = rotateRight(h);
            if(cmp == 0 && h.right == null) return null;
            if(!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if(cmp == 0){
                Node min = min(h.right);
                h.key = min.key;
                h.val = min.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public void delete_test(Key key){
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete_test(root, key);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node delete_test(Node h, Key key){
        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete_test(h.left, key);
            return balance(h);
        }
        if(cmp > 0){
//            if(isRed(h.left))
//                h = rotateRight(h);
            if(!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            h.right = delete_test(h.right, key);
            return balance(h);
        }
        else{
            if(h.right == null) return h.left;
            else{
                h.key = min(h.right).key;
                h.val = min(h.right).val;
                h.right = deleteMin(h.right);
            }
            return balance(h);
        }
    }

    public static void main(String[] args){
        RedBlackBST a = new RedBlackBST();
        a.put('a',1);
        a.put('b',2);
        a.put('c',3);
        a.put('d',3);
        a.put('h',3);
        a.put('e',3);
        a.put('f',3);
        a.put('g',3);

        a.deleteMax_test();
        System.out.print(a.max());
    }


}
