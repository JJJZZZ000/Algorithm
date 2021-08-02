package Sort;

import Sort.Exercise.QueueNode;
//
//public class HeapSortNode <Key extends Comparable<Key>>{
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
//
//}
