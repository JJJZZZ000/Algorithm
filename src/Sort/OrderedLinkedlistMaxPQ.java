package Sort;

public class OrderedLinkedlistMaxPQ {
/*定义结点的抽象数据类型*/
static class Node<Key extends Comparable<Key>> {
    public Key key;
    public Node next;
    public void displayNode() {
        System.out.print(key+" ");
    }
}
/*定义了一个有序链表，链表表示的是一列元素*/
static class LinkedList<Key extends Comparable<Key>>  {
    private Node first; //栈顶(最近添加的元素)
    public LinkedList() {
        first = null;
    }
    /*向链表中添加元素的push方法保证所有元素为逆序*/
    public void push(Key key) {
        Node newLink = new Node();
        newLink.key = key;
        Node previous = null;
        Node current = first;
        //得到数据插入的位置
        while(current != null && less((Key)newLink.key,(Key)current.key)) {
            previous = current;
            current = current.next;
        }
        if(previous == null) {
            first = newLink;
        }
        //移动结点到指定位置
        else {
            previous.next = newLink;
        }
        newLink.next = current;
    }
    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    public Key delete() {
        Key tmp = (Key)first.key;
        first = first.next;
        return tmp;
    }
    public void displayLinList() {
        Node current = first;
        while(current != null) {
            current.displayNode();
            current = current.next;
        }
    }
}

    public static void main(String[] args) {
        LinkedList ll= new LinkedList();
        ll.push("t");
        ll.push("i");
        ll.push("a");
        ll.push("k");
        ll.push("j");
        ll.push("p");
        ll.push("d");
        ll.push("f");
        ll.push("m");
        ll.push("s");
        ll.push("z");
        ll.push("b");
        ll.displayLinList();
        System.out.println();
        ll.delete();
        ll.displayLinList();
    }

}
