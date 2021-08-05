package Search;
import Sort.Exercise.QueueNode;

import java.util.Iterator;
import java.util.Queue;
// size(), keys(), delete();

public class SequentialSearchST<Key, Value>  {
    // based on disordered link
    private Node first;
    int N = 0;
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node (Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public Value get(Key key){
        for(Node  x = first; x != null; x = x.next){
            if(key.equals(x.key)) return x.val;
        }
        return null;
    }

    public void put(Key key, Value val){
        if(N == 0){ first.key = key; first.val = val; first.next = null; N++; return;};
        for(Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) { x.val = val; return;}
        first = new Node(key, val, first);
        N++;
    }

    public int size(){ return N; }

    public Iterable<Key> keys(){
        QueueNode<Key> queue = new QueueNode<Key>();
        for(Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

//
//    public void delete(Key key) {
////        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
//        first = delete(first, key);
//    }
//
//    // delete key in linked list beginning at Node x
//    // warning: function call stack too large if table is large
//    // 返回Node x（含）之后的子链表删除掉key的新链表的首节点
//    private Node delete(Node x, Key key) {
//        if (x == null) return null;
//        if (key.equals(x.key)) {
//            N--;
//            return x.next;
//        }
//        x.next = delete(x.next, key);
//        return x;
//    }
    public void delete(Key key){
        if(key.equals(first.key)){ first.key = null; first.val = null; first = first.next; N--; return;}
        for(Node x = first; x.next != null; x = x.next){
            if(key.equals(x.next.key)){
                x.next.key = null;
                x.next.val = null;
                x.next = x.next.next;
                N--;
                return;
            }
        }
    }
}
