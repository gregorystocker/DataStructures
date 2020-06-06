public class LinearProbingHashST<Key extends Comparable,Value> {
    private int N;      //# of key-value pairs in the table
    private int M = 16; //size of the linear-probing ST
    private Key[] keys; // the keys
    private Value[] vals;   //the values

    /**
     * creates the Linear Probing Hash ST using a parallel arrays approach
     */
    public LinearProbingHashST(){
        //using a parallel array approach again simmilar to BinarySearchST
        //in java we can`t directly create an array of generics so we need to make them Objects or some interface and
        //then cast them to the generic
        keys = (Key[] )new Object[M];
        vals = (Value[]) new Object[M];
    }//ends constructor LinearProbingHashST

    /**
     * uses the hashCode() of the key to get an integer that we can use to calculate the index.
     * @param key
     * @return
     */
    private int hash(Key key){
        return ((key.hashCode() & 0x7fffffff) % M);
    }
    /**
     * uses the linear probing approach of calculating an index, then if that index is free, that`s it, but if it`s not,
     * we want to go to the next availible free index.
     * @return the index
     */
    public void put(Key key, Value val){
        int hashed = hash(key);
        //search hit
        if(keys[hashed] == null){    keys[hashed] = key;    vals[hashed] = val; M++; }
        else{
            while (keys[hashed] != null && hashed < M){
                hashed++;
            }
            if (hashed < M){
                keys[hashed] = key;    vals[hashed] = val;  M++;
            }
        }

    }
}
