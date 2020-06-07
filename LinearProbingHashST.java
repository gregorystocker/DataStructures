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

    }//ends hash

    public void delete(Key key) {
        //check if the hash table contains the key
        if (!contains(key)) return;
        //find the array index using our hash function
        int i = hash(key);
        //while we`re not at the right key, increase the index, but use the modulo so it will wrap around and never
        //go out of bounds
        while (!key.equals(keys[i]))     i = (i + 1) % M;

        //found correct index
        //delete that value
            keys[i] = null;
            vals[i] = null;

            //increment one past the now null key value-pair
        i = (i + 1)%M;

        //loop through all the pairs after the deleted value until you hit a null
        while(keys[i] != null)  {
            //create a temp for the key-value pair
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            //delete the current pair
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo,valToRedo);
        }//end while loop
        //decrement N once more once we are out of the loop so that we can account for the value we didnt replace
        N--;
        //grows the table to keep the N/M ratio
        if(N > 0 && N == M/8) resize(M/2);
    }//ends delete

    /**
     * returns a boolean value representing whether or not the key is in the HT
     * @param key
     * @return
     */
    public boolean contains(Key key){
        //find the index for the array to start at
        int start = hash(key);

        //loop through the keys until we see a null value using a modulo to wrap around to the beginning
        for(int i = start; keys[i] != null; i = (i + 1) % M){
            if(keys[i].equals(key))     return true;
        }
        //if the for loop reaches it`s end and we haven`t found the key, it`s not there
        return  false;
    }

    /**
     * resizes both the keys and the vals arrays to the given size
     * @param size tells the function what size to make the arrays
     */
    public void resize(int size){
        //Java does not allow us to create arrays of generics, so we must create arrays of their parents and cast
        // them to generics
        Comparable[] tempKeys = (Key[])new Comparable[size];
        Object[] tempVals = (Object[]) new Object[size];

        //copies the values from keys and vals to the temporary arrays
        for(int i = 0; i < keys.length; i++){
            tempKeys[i] = keys[i];
            tempVals[i] = vals[i];
        }
        //assign the originals to the temps
        keys = (Key[])tempKeys;
        vals = (Value[])tempVals;
    }//ends resize

    /**
     * my version of get() which accounts for and allows a search of a completely full table in contrast to the book which
     * depends on other functions to handle resizing such that the table is never full.
     * @param key the key to get
     * @return the value of the searched key
     */
    public Value get(Key key){
        boolean flag = false;
        int start = hash(key);
        for(int i = start; i < M; i = (i+1)%M ){
            if(keys[i] == key)
                return vals[i];
            else if (keys[i] == null)
                return null;
            //note this flag is to avoid an infinite loop if we have a full table, but the book actually leaves this
            //out and resizes the table such that it never becomes full
            if(i == start && flag)  return null;
            flag = true;
        }
       return null;
    }


}//ends class LinearProbingHashST
