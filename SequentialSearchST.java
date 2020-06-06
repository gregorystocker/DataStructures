/**
 * Represents an UNORDERED Symbol Table. Only the minimum operations are supported.
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key extends Comparable, Value> {

    // this is our head pointer
    private Node first;

    /**
     * create a symbol table
     */
    public SequentialSearchST(){}


    /**
     *put key-value pair into the table (remove key from table if value is null)
     */
    void put(Key key, Value val){

        //check to see if key already exists
        for(Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)){
                x.val = val;//search hit
                if(val == null)
                    delete(key);
                return;
            }
        }
               //list is empty-search miss
                if (first == null)
                    first = new Node(key, val, null);
                else {
                    Node nn = new Node(key, val, first);
                    first = nn;
                }
    }// ends put

    /**
     * value paired with key (null if  key is absent)
     * @param key
     * @return
     */
    Value get(Key key){
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key))
                return x.val;//search hit
        }
        //search miss
        return null;
    }//ends get

    /**
     * remove key (and its value) from table
     * @param key
     */
    void delete(Key key){

        for(Node x = first; x != null; x = x.next) {
            if (key == x.key) {
                if (x.equals(first))
                    first = x.next;
            } else {
                //calculate the previous node
                Node pre = new Node();
                for (pre = first; pre.next != x && x != null; pre = pre.next) {//do nothing
                }//ends for
                //delete the node
                pre.next = x.next;
            }
        }
    }//ends delete

    /**
     * is there a value paired with key?
     * @param key
     * @return
     */
    boolean contains(Key key){
        {
            for(Node x = first; x != null; x = x.next){
                if(key.equals(x.key))
                    return true;
            }
            //search miss
            return false;
        }
    }//ends contains

    /**
     * returns the size of the unordered search table
     * @return
     */
    int size(){
        int counter = 0;
        for(Node x = first; x != null; x = x.next) counter++;

        return counter;
    }

    public void print(){
        System.out.println("Sequential Symbol Table: ");
        for(Node x = first; x != null; x = x.next){
            System.out.println("( " + x.key  + " , " + x.val + " )");
        }


    }//ends print

    class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }//ends constructor

        public Node(){}



        // We have left the implementations of size(),  keys(), and eager delete() for exercises.
    }//ends inner Node class

    }//ends class SequentialSearchST

