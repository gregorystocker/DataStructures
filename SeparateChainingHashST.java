/**
 * This basic symbol-table implementation maintains an array of linked lists,
 * using a hash function to choose a list for each key.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
public class SeparateChainingHashST<Key extends Comparable, Value> {
    private int N;  //number of key-value pairs
    private int M;  //hash table size
    private SequentialSearchST<Key,Value>[] st; //array of ST objects

    public SeparateChainingHashST(){
        this(1997);

    }

    /**
     * creates the Symbol table using an array of (linked list based) SequentialSearchST`s of size M
     * @param M indices in the HT
     */
    public SeparateChainingHashST(int M){
        //Create M linked lists
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++)  st[i] = new SequentialSearchST();
    }

    /**
     * Our hash function uses the hashCode function of the key`s type and uses the bitwise AND operator on it.
     * Bitwise AND is used to compare each place value with a large hex number and the result is used as the hash value
     * @param key value the hash is based on
     * @return
     */


    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }//ends hash

    /**
     * uses the SequentialSearch class. Uses the hash function on the key, then once the index is found st.get() is called
     * using the key to find the value inside the inner symbol table
     * @param key
     * @return
     */
    private Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val){
        int index = hash(key);
        st[index].put(key,val);
    }

}//ends class SeparateChainingHashST

