package Search;

import Sort.Exercise.QueueNode;

public class SeperateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;
    private static final double thred = 8;

    public SeperateChainingHashST(){ this(997); }

    public SeperateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }

    private void resize(int cap){
        SeperateChainingHashST<Key, Value> t = new SeperateChainingHashST<Key, Value>(cap);
        for(int i = 0; i < M; i++){
            for(Key key : st[i].keys()){
                t.put(key, st[i].get(key));
            }
        }
        M = t.M;
        N = t.N;
        st = t.st;

    }

    private void resize_test(int m){
        SequentialSearchST<Key, Value>[] st_new = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for(int i = 0; i < m; i++)
            st_new[i] = new SequentialSearchST();
        for (Key key : keys()) {
            int hash = key.hashCode() & 0x7fffffff % m;
            st_new[hash].put(key, st[hash(key)].get(key));
        }
        M = m;
        st = st_new;
    }


    private int hash(Key key){ return (key.hashCode() & 0x7fffffff) % M;}

    public Value get(Key key){ return (Value)st[hash(key)].get(key);}

    public void put(Key key, Value value){
        st[hash(key)].put(key,value);
        N++;
        if(N>0 && N/M > thred) resize(M*2);
    }

    public Iterable<Key> keys(){
        QueueNode<Key> queue = new QueueNode<Key>();
        for(int i = 0; i < st.length; i++){
            for(Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    public void delete(Key key){
        st[hash(key)].delete(key);
        N--;
        if(N>0 && N/M < 2) resize(M/2);
    }


    public static void main(String[] main){
        SeperateChainingHashST<String, Integer> a = new SeperateChainingHashST<String, Integer>();
        a.put("a",1);
        a.put("b",2);
        a.put("c",3);
        a.resize(6);



    }
}
