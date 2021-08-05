package Search;

import Sort.Exercise.QueueNode;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){ return N;}

    public Value get(Key key){
        if(N == 0) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public void put(Key key, Value val){
         int i = rank(key);
         if(i < N && keys[i].compareTo(key)==0) {vals[i] = val; return;}
         for(int j = N; j > i; j--){
             keys[j] = keys[j-1];
             vals[j] = vals[j-1];
         }
         keys[i] = key;
         vals[i] = val;
         N++;

    }

    public int rank(Key key){
        int lo = 0;
        int hi = N - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0 ) hi = mid - 1;
            else if(cmp > 0 ) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public int rank_reverse(Key key, int lo, int hi){
        int mid = lo + (hi - lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if(lo == hi){if(cmp>0) return 1; else return 0;}
        if(cmp < 0) return rank_reverse(key, lo, mid-1);
        else if(cmp > 0) return mid-lo+1+rank_reverse(key, mid+1, hi);
        else return mid-lo;
    }

    public int rank_reverse(Key key){
        return rank_reverse(key, 0, N-1);
    }

    public Key min(){ return keys[0]; }

    public Key max(){ return keys[N-1]; }

    public Key select(int k){ return keys[k];}

    public Key ceiling(Key key){
        int rank = rank(key);
        return keys[rank];
    }

    public Key flloor(Key key){
        int rank = rank(key);
        if(rank<N && keys[rank].compareTo(key)==0) return key;
        if(rank>0) { return keys[rank-1]; }
        return null;
    }

    public void delete(Key key){
        int i = rank(key);
        if(i==N || keys[i] != key) return;
        for(int j = i; j < N-1; j++){
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        keys[N-1] = null;
        vals[N-1] = null;
        N--;
    }

    public boolean contains(Key key){ return keys[rank(key)].compareTo(key)==0;}

    public Iterable<Key> keys(Key lo, Key hi){
        QueueNode<Key> q = new QueueNode<Key>();
        for(int i = rank(lo); i <rank(hi); i++) q.enqueue(keys[i]);
        if(contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public static void main(String[] args){
        BinarySearchST a = new BinarySearchST(3);
        a.put('a',1);
        a.put('b',2);
        a.put('d',3);
        System.out.print(a.rank_reverse('d',0,2));
    }

}
