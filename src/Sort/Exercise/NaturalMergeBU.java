package Sort.Exercise;

import java.util.Queue;
import java.util.Stack;

public class NaturalMergeBU {
    public static void sort(QueueNode<Comparable[]> a){
        while(a.size() != 1){
            Comparable[] i = a.dequeue();
            Comparable[] j = a.dequeue();
            Comparable[] k = Merge.Merge(i,j);
            a.enqueue(k);
        }
    }

    public static void main(String[] args){
        Comparable[] a = {1,3,5};
        Comparable[] b = {2,4,6};
        Comparable[] c = {7,8,9};
        QueueNode<Comparable[]> q = new QueueNode<Comparable[]>();
        q.enqueue(a);
        q.enqueue(b);
        q.enqueue(c);
        sort(q);
        Comparable[] newq = q.dequeue();
        for(int i = 0; i < newq.length; i++)
            System.out.print(newq[i]);
    }
}
