package pq;

/**
 *  A  symbol table is a data structure for key-value pairs that supports two operations:
 *  insert (put) a new pair into the table and search for (get) the value associated with a given key.
 *
 *  Special Rules:
 *  Keys must not be null. As with many mechanisms in Java, use of a null key results in an exception at runtime
 *
 *  null in get:
 *   get() should return null for keys not in the table,
 *   effectively associating the value null with every key not in the table.
 *
 *   null in put:
 *   we can use the operation of calling put() with null as its second (value) argument to implement deletion
 *
 */

public class ST<Key extends Comparable<Key>, Value> {
    private Node first; // this is the headPointer

    /**
     * put key-calue pair into the table
     *
     */
    void put(Key key, Value val){


    }

    //Value get(Key key){}

    /**
     * remove key(and it`s value from the table
     */
    //void delete(Key key)t{}

    /**
     * is there a value paired with key?
     * @param key
     * @return
     */
   // boolean contains(Key key){}

    /**
     * is the table empty?
     * @return
     */
   // boolean isEmpty(){}


    /**
     * number of key-value pairs in the table
     * @return
     */
   //int size(){}

    //Ordered Symbol Table Methods--------------------------------------------------------------------------------------

    /**
     * returns an iterable with all of the keys in the table
     * @return
     */
    //Iterable<Key> keys(){}

   // Key min(){}

    //Key max(){}

    //Key ceiling(Key key){}
    //largest key less than or equal to key
    //Key floor(Key key){}
   //smallest key greater than or equal to key
    //int rank(Key key){}
    //Key select(int k){}{}
    //void deleteMin(){}
    //void deleteMax(){}

    //int size(Key lo, Key hi){}
    //Iterable<Key> keys(Key lo, Key hi) {}
    //Iterable<Key> keys() {}
    public class Node{

        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
     }//ends inner class Node


}//ends ST<Key,Value>
