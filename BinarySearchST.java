/**
 * uses the book`s parallel array approach to create an Ordered Symbol table. Uses the 'rank' method to return where a key
 * either is or would be using a recursive binary search and inserts it there so everything is always in it`s correct spot.
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    /**
     * default constructor that instantiates the keys array as an array of comparables and the vals array as an array of
     * Objects
     * @param capacity
     */

    public BinarySearchST(int capacity){
        //casting the comparable type to Key in order to bypass the direct instantiation of a generic type error
        //same for the Object instantiation
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }


    /**
     *
     * @return size
     */
    public int size(){return N;}

    /**
     * returns the number of elements between startIndex and endIndex
     * @param startKey
     * @param endKey
     * @return # of key-value pairs in the domain
     */
    public int size(Key startKey, Key endKey){
        int start = rank(startKey);
        int end = rank(endKey);
        return end - start + 1;
    }
    /**
     * gets the key`s index as if it was just a 0 based array, uses binary search, so it assumes that this will be an ordered
     *  symbol table, returns the index that it SHOULD be placed at if it is not in the array.
     * @param key
     * @return
     */
    public int rank(Key key){
        return rank(key, 0, N-1);
    }


    //helper method for recursive binary search
    public int rank(Key key, int lo, int hi){
        //if we have searched the list and havent found it, we need to store our value in lo
        if(hi < lo) return lo;
        int mid = lo + (hi - lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if(cmp == 0) return mid;
        else if(cmp < 0) return rank(key,lo, mid-1);
        else return rank(key, mid + 1, hi);
    }//ends rank helper function

    /**
     * returns the corresponding value when passed a key,
     * uses rank to get the index that we are supposed to be checking
     * @param key
     * @return value
     */
    public Value get(Key key)throws IndexOutOfBoundsException {
        if(isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }//ends get


    /**
     * put a key value pair into the symbol table. Resizes it`self automatically when an indexOutOfBoundsError is thrown,
     * and recalls it`self
     * @param key
     * @param val
     */
    void put(Key key, Value val){
        //trys to run the put operation, if out of bounds of the arrays, it resizes the arrays and recalls put.
        try {
            int i = rank(key);
            //if were in bounds and the key was already there, just update it`s value
            if (i < N && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }
            //if it wasnt in bounds or we didn`t find that key

            //move everything over one then add our thing
            for (int j = N + 1; j > i; j--) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];
            }//ends moving stuff over

            //add the value into our array
            keys[i] = key;
            vals[i] = val;
            //increment the size
            N++;
        }catch(IndexOutOfBoundsException e){
            //System.out.println("Resizing the array at size: " + N);
            resizeArrays();
            put(key, val);
        }
      }//ends put

    //helper function that adds 3 indices to the array
    private void resizeArrays(){
            int newSize = N + 3;
            Key[] tempKey = (Key[])new Comparable[newSize];
            Value[] tempVal = (Value[])new Object[newSize];
            for(int k = 0; k < N; k++){
                tempKey[k] = keys[k];
                tempVal[k] = vals[k];
            }
            //System.out.println("The auxillary arrays are: ");
            //printArrays(tempKey, tempVal,N);
            keys = (Key[])tempKey;
            vals = (Value[])tempVal;

        }//ends resizing code


    //testing helper method
    private void printArrays(Key[] keyArr, Value[] valArr, int size){
        for(int i = 0; i < size; i++)
            System.out.println("( " + keyArr[i]  + " , " + valArr[i] + " )");
    }

    /**
     * remove key (and its value) from table
     *
     * @param key
     */
    void delete(Key key){
           int i = rank(key);
            if(!contains(key)) {
                return;
            }else{
                Comparable[] tempKeys = new Comparable[N-1];
                Object[] tempVals = new Object[N-1];
                for(int j = 0; j < i; j++){
                    tempKeys[j] = keys[j];
                    tempVals[j] = vals[j];
                }
                for(int j = N-2; j > i; j--){
                    tempKeys[j] = keys[j + 1];
                    tempVals[j] = vals[j + 1];
                }
                keys = (Key[])tempKeys;
                vals = (Value[])tempVals;
                N--;
            }

       }//ends delete

    /**
     * takes an int representing the rank (0-based index) of the element and returns the key at that rank
     * @param k
     * @return
     */
    Key select(int k){
        return keys[k];
    }


    /**
     * is there a value paired with key?
     * @param key
     * @return
     */
    boolean contains(Key key){
            int i = rank(key);
            if(i >= N)
                return false;
            if(keys[i].compareTo(key) == 0) {
                return true;
            }
             return false;

       }

        //is the table empty?
       public boolean isEmpty(){
            if(N == 0) return true;
            else return false;
       }

        //all keys in the table
        Key[] keys(){
           return keys;
        }

    //all keys in the table
        Key[] keys(Key startKey, Key endKey){
        int start = rank(startKey);
        int end = rank(endKey);
        int size = end - start + 1;
        Key[] subKeys = (Key[])new Comparable[size];
        for(int i = start; i < size; i++) subKeys[i] = keys[i];
        return subKeys;
    }//ends keys(startKey, endKey)

    /**
     *     returns the smallest key
     *     @return
     */

    public Key min()throws IndexOutOfBoundsException{
        if(isEmpty()) throw new IndexOutOfBoundsException();
        else
            return keys[0];
    }

    /**
     *     returns the smallest key
     *     @return
     */

    public Key max()throws IndexOutOfBoundsException{
        if(isEmpty()) throw new IndexOutOfBoundsException();
        else
            return keys[N-1];
    }

    /**
     * deletes the min key value pair
     */
    public void deleteMin(){
        delete(min());
    }

    /**
     * deletes the max key value pair
     */
    public void deleteMax(){
        delete(max());
    }

    /**
     * prints out the table
     */
        public void print(){
            System.out.println("Symbol Table: ");
            for(int i = 0; i < N; i++){
                System.out.println("( " + keys[i]  + " , " + vals[i] + " )");
            }
        }

    /**
     * prints the st from startKey to endKey
     * @param startKey
     * @param endKey
     */
    public void printSubSection(Key startKey, Key endKey){
        System.out.println("Symbol Table: ");
        int start = rank(startKey);
        int end = rank(endKey);
        if(end > N-1) end = N-1;

        if(start>=0 && start<= N && end >= start)
            for(int i = start; i <= end; i++)
                System.out.println("( " + keys[i]  + " , " + vals[i] + " )");
    }


    /**
     * returns
     * @return largest key less than or equal to key
     */
    public Key floor(Key key) {
        if (contains(key)) return key;
        else {
            int pos = rank(key) - 1;

            if(pos < 0)return null;
            else
                return select(pos);
        }
    }

    /**
     * returns
     * @return smallest key greater than or equal to key
     */
    public Key ceiling(Key key) {
        if (contains(key)) return key;
        else {
            int pos = rank(key);

            if(pos >= N)return null;
            else
                return select(pos);

        }//ends outer else
    }//ends ceiling





}//Ends BinarySearchST





