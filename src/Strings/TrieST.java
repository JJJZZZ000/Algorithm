package Strings;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node{
        private int size;
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if(x == null) return null;
        return (Value)x.val;
    }

    private Node get(Node x, String key, int d){
        if(x == null) return null;
        if(d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d){
        if(x == null) x = new Node();
        if(d == key.length()){ x.val = val; return x; }
        char c = key.charAt(d);
        x.next[c] =  put(x.next[c], key, val, d+1);
        x.size += 1;
        return x;
    }

    public int size(){ return root.size; }

    public int size_delay(){
        return size_delay(root);
    }

    private int size_delay(Node x){
        if(x == null) return 0;
        int cnt = 0;
        if(x.val != null) cnt++;
        for(char c = 0; c < R; c++)
            cnt += size_delay(x.next[c]);
        return cnt;
    }

    private void collect(Node x, String pre, Queue<String> q){
        if(x == null) return;
        if(x.val != null) q.enqueue(pre);
        for(char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    public Iterable<String> keysWithPrefix(String pre){
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre,  q);
        return q;
    }

    public Iterable<String> keys(){
        return keysWithPrefix("");
    }

    public Iterable<String> keysThatMatch(String pat){
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, 0, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, int d, Queue<String> q){
        // int d = pre.length();
        if(x == null) return;
        if(x.val != null && d == pat.length()) q.enqueue(pre);
        if(d == pat.length()) return;
        char next = pat.charAt(d);
        for(char c = 0; c < R; c++){
            if(next == '.' || next == c)
                collect(x.next[c], pre+c, pat, d+1, q);
        }
    }

    public String longestPrefixOf(String s){
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length){
        if(x == null) return length;
        if(x.val != null) length = d;
        if(d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d+1, length);
    }

    public String longestPrefixOf_test(String s){
        return search_test(root, "", s, 0, "");

    }

    private String search_test(Node x, String pre, String s, int d, String longest){
        if(x == null) return longest;
        if(x.val != null) longest = pre;
        if(pre.length() == s.length()) return longest;
        char c = s.charAt(d);
        return search_test(x.next[c], pre+c, s, d+1, longest);
    }

    public void delete(String s){
        root = delete(root, s, 0);
    }

    private Node delete(Node x, String s, int d){
        if(x == null) return null;
        if(d == s.length()) x.val = null;
        else {
            char c = s.charAt(d);
            x.next[c] = delete(x.next[c], s, d + 1);
        }
        if(x.val != null) return x;
        for(char c = 0; c < R; c++)
            if(x.next[c] != null) return x;
        return null;
    }

    public static void main(String[] args){
        TrieST<Integer> a = new TrieST<Integer>();
        a.put("by",4);
        a.put("sea",2);
        a.put("sells",1);
        a.put("she",0);
        a.put("shells",3);
        a.put("the",5);
        Integer b = a.get("she");
        System.out.println(b);
        System.out.println(a.size());
        System.out.println(a.size_delay());
        System.out.println();
        Queue<String> q = new Queue<String>();
        q = (Queue<String>)a.keysThatMatch("sh.");
        for(String i : q){
            System.out.println(i);
        }

        String d = a.longestPrefixOf_test("shellsbelllshabisad");
        System.out.println(d);
    }

}
