package Search;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST(){
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap){
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int hash(Key key){ return (key.hashCode() & 0x7fffffff) % M;}

    private void resize(int cap){
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(cap);
        for(int i = 0; i < M; i++){
            if(keys[i] != null)
                t.put(keys[i], vals[i]);
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    private void resize_test(int m){
        Key[] keys_new = (Key[]) new Object[m];
        Value[] vals_new = (Value[]) new Object[m];
        for(int i = 0; i < M; i++){
            Key key = keys[i];
            Value val = vals[i];
            if(key == null) continue;
            int hash = (key.hashCode() & 0x7fffffff) % m;
            int j = hash;
            while(keys_new[j] != null) j = (j+1) % m;
            keys_new[j] = key;
            vals_new[j] = val;
        }
        M = m;
        keys = keys_new;
        vals = vals_new;
    }

    public void put(Key key, Value val){
        if(N >= M/2) resize(M*2);
        int i;
        for(i = hash(key); keys[i] != null; i = (i+1) % M)
            if(keys[i].equals(key)) {vals[i] = val; return;}
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key){
        for(int i = hash(key); keys[i] != null; i = (i+1) % M)
            if(keys[i].equals(key))
                return vals[i];
        return null;
    }

    private boolean contain(Key key){
        for(int i = hash(key); keys[i] != null; i = (i+1) % M){
            if(keys[i].equals(key)) return true;
        }
        return false;
    }

    public void delete(Key key){
        if(!contain(key)) return;
        int i = hash(key);
        while(!key.equals(keys[i]))
            i = (i+1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i+1) % M;
        while(keys[i] != null){
            Key keyToRedo = keys[i];
            Value valueToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i+1) % M;
        }
        N--;
        if(N>0 && N == M/8) resize(M/2);
    }


    public void delete_test(Key key){
        if(!contain(key)) return;
        int i = hash(key);
        int hash = i;
        while(!key.equals(keys[i]))
            i = (i+1) % M;

        int j = (i+1) % M;
        while(keys[j] != null){
            if(hash(keys[j]) == hash) {
                keys[i] = keys[j];
                vals[i] = vals[j];
                i = j;
                j = (j + 1) % M;
            }
            else j = (j + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;
        N--;
        if(N>0 && N == M/8) resize(M/2);
    }


    public static void main(String[] args){
        LinearProbingHashST<String, Integer> a = new LinearProbingHashST<String, Integer>();
        a.put("a",1);
        a.put("b",2);
        a.put("c",3);
        a.delete_test("b");
        System.out.print(a.get("b"));
    }
}
