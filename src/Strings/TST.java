package Strings;

import edu.princeton.cs.algs4.Queue;

public class TST<Value> {
    private Node root;
    private class Node{
        char c;
        Node left, mid ,right;
        Value val;
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null) return null;
        else return (Value)x.val;
    }

    private Node get(Node x, String key, int d){
        if(x == null) return null;
        char c = key.charAt(d);
        if(c < x.c) return get(x.left, key, d);
        else if(c > x.c) return get(x.right, key, d);
        else if(d < key.length()-1)
            return get(x.mid, key, d+1);
        else return x;

    }

    public Value get_test(String key){
        Node x = get(root, key, 0);
        if(x == null) return null;
        else return (Value)x.val;
    }

    private Node get_test(Node x, String key, int d){
        if(x == null) return null;
        char c = key.charAt(d);
        if(d == key.length()-1 && c == x.c) return x;
        if(c < x.c) return get(x.left, key, d);
        else if(c > x.c) return get(x.right, key, d);
        else return get(x.mid, key, d+1);
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    public Node put(Node x, String key, Value val, int d){
        char c = key.charAt(d);
        if(x == null) {x = new Node(); x.c = c; }
        if(c < x.c) x.left = put(x.left, key, val, d);
        if(c > x.c) x.right = put(x.right, key, val, d);
        else if(d < key.length()-1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;
        return x;
    }

    public void put_test(String key, Value val){
        root = put_test(root, key, val, 0);
    }

    public Node put_test(Node x, String key, Value val, int d){
        char c = key.charAt(d);
        if(x == null) {x = new Node(); x.c = c; }
        if(d == key.length()-1 && c == x.c) x.val = val;
        else{
            if(c < x.c) x.left = put(x.left, key, val, d);
            if(c > x.c) x.right = put(x.right, key, val, d);
            else x.mid = put(x.mid, key, val, d+1);
        }
        return x;
    }

    public String longestPrefixOf(String s){
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length){
        if(x == null) return length;
        if(x.val != null) length = d + 1;
        char c = s.charAt(d);
        if(c > x.c) return search(x.right, s, d, length);
        else if(c < x.c) return search(x.left, s, d, length);
        else if(d == s.length()-1) return d;
        else return search(x.mid, s, d+1, length);
    }

    public Iterable<String> keys(){
        Queue<String> q = new Queue<String>();
        collect(root, "", q);
        return q;
    }

    public Iterable<String> keyWithPrefixOf_test(String s){
        Queue<String> q = new Queue<String>();
        collect_test(get(root, s, 0), s.substring(0, s.length()-1), q);
        return q;
    }

    private void collect_test(Node x, String pre, Queue<String> q){
        if(x == null) return;

        if(x.val != null) q.enqueue(pre + x.c);
        collect_test(x.left, pre, q);
        collect_test(x.right, pre, q);
        collect_test(x.mid, pre+x.c, q);
    }

    public Iterable<String> keyWithPrefixOf(String s){
        Queue<String> q = new Queue<String>();
        collect(get(root, s,0), s, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q){
        if(x == null) return;
        if(x.val != null) q.enqueue(pre);
        Node next = x.mid;
        if(next == null) return;
        if(next.left != null) collect(next.left, pre+next.left.c, q);
        if(next.right != null) collect(next.right, pre+next.right.c, q);
        collect(next, pre+next.c, q);
    }

    public Iterable<String> keyThatMatch(String pat){
        Queue<String> q = new Queue<String>();
        collect2(root, "", pat, q, 0);
        return q;
    }

    private void collect2(Node x, String pre, String pat, Queue<String> q, int d){
        if(x == null) return;
        char c = pat.charAt(d);
        if(c == x.c || c == '.'){
            if(x.val != null && d == pat.length()-1) q.enqueue(pre+x.c);
            if(d == pat.length()-1) return;
            else collect2(x.mid, pre+x.c, pat, q, d+1);
        }
        if(c > x.c || c == '.'){
            collect2(x.right, pre, pat, q, d);
        }
        if(c < x.c || c == '.'){
            collect2(x.left, pre, pat, q, d);
        }

    }

    public void delete(String s){
        root = delete(root, s, 0);
    }

    private Node delete(Node x, String s, int d){
        if(x == null) return null;
        char c = s.charAt(d);
        if(c == x.c && d == s.length()-1) {
            x.val = null;
        }
        if(c > x.c) x.right = delete(x.right, s, d);
        if(c < x.c) x.left = delete(x.left, s, d);
        if(c == x.c && d < s.length()-1) x.mid = delete(x.mid, s, d+1);

        if(x.val != null || x.mid != null) return x;
        if(x.left != null) return x.left;
        if(x.right != null) return x.right;
        return null;

    }

    public static void main(String[] args){
        TST<Integer> a = new TST<Integer>();
        a.put_test("by",4);
        a.put_test("sea",2);
        a.put_test("sells",1);
        a.put_test("she",0);
        a.put_test("shells",3);
        a.put_test("the",5);
        a.delete("shells");
        Integer b = a.get_test("shells");
        System.out.println(b);
        String c = a.longestPrefixOf("shellsbbskdjsf");
        Queue<String> q = (Queue<String>)a.keyWithPrefixOf_test("sh");
        Queue<String> w = (Queue<String>)a.keyThatMatch("s..");
        for(String i : q)
            System.out.println(i);
    }

}
