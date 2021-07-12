package Sort.Exercise;

import java.util.Iterator;

public class QueueNode<Item> implements Iterable<Item>{
    //no iterator
    private Node first;
    private Node last;
    private int N;

    private class Node{
        Item item;
        Node next;

        Node(){}
        Node(Node p){
            Item item = p.item;
            if(p.next!=null) next = new Node(p.next);
        }
    }

//    public QueueNode(QueueNode<Item> p){
//        first = new Node(p.first);
//        last = new Node(p.last);
//    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void enqueue(Item item){
        Node oldlast = last;//oldlast 接管了last的链接，其源头是first
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        N--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){return first != null;}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
