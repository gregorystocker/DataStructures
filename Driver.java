import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Driver<Key extends Comparable, Value> {
    //member variables
    //will be used to sort the file by words = key and the number of times the word has been seen will be the value.
    private BinarySearchST<String, Integer> st = new BinarySearchST(10);
    //used to store the different words as they are broken down from the lines read in from the BufferedReader
    private DoublyLinkedList<String> dll = new DoublyLinkedList();
    private SequentialSearchST<String, Integer> sst = new SequentialSearchST();
    private BST<Integer,String> bst = new BST();
    private static Driver main = new Driver();

    /**
     * takes a key and records how many times that word has been seen in they symbol table
     * @param key
     * @return
     */
    public void updateST(String key){
        //word is not in the symbol table yet
        if(!st.contains(key)){
            st.put(key, 1);
            sst.put(key,  1);
        }
        else {
            int prevTimes = (Integer)st.get(key);
            st.put(key, prevTimes + 1);
            sst.put(key, prevTimes + 1);
        }
    }

    /**
     * breaks the string down into a linked list of words
     * @param line
     */
    public void breakIntoWords(String line) {
        String word = "";
        char[] chars;
        chars = line.toCharArray();

        for(int i = 0; i < chars.length; i++){
           if(chars[i] != ' '){ word += chars[i];
                if(i+1 >= chars.length){
                    dll.headInsert(word);
                    word = "";
                }
           }
           else{
                   dll.headInsert(word);
                   word = "";
           }
        }
        System.out.println(word);
    }//ends breakIntoWords

    void queues(){

        Driver main = new Driver();
        bst.put(10, "A");
        bst.put(7, "B");
        bst.put(15, "C");
        bst.put(5, "D");
        bst.put(9, "E");
        bst.put(11, "F");
        bst.put(16, "G");


        System.out.println("The result of printBST: should be 10, 7, 15, 5, 9, 11, 16");
        bst.printBFS();
    }

    /**
     * uses tinyTale.txt to count to number of times a given word was seen.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void BinaryAndSequentialSTTest() throws FileNotFoundException, IOException{

        File file = new File("C:\\Users\\grego\\IdeaProjects\\DataStructurePractice\\src\\tinyTale.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        str = br.readLine();// this is the first line that is just the %tinyTale.txt
        while( (str = br.readLine()) != null){

            main.breakIntoWords(str);
            while(main.dll.size > 0){
                String word = (String)main.dll.headDelete();
                main.updateST(word);
            }
        }//ends while loop
        //main.dll.print();
        main.st.print();
        main.sst.print();
    }

    //runs tests on the different data structures from Sedwick`s Algorithms book
    public static void main(String[] args){
        /*
        Driver main = new Driver();

        main.bst.put(20,"a");
        main.bst.put(8,"b");
        main.bst.put(21,"c");
        main.bst.put(12,"d");



        main.bst.print();
        System.out.println("get(20): " + main.bst.get(20));
        System.out.println("get(7): " + main.bst.get(9));
        System.out.println("get(9): " + main.bst.get(12));
        System.out.println("min(): " + main.bst.min());
        System.out.println("max(): " + main.bst.max());
        System.out.println("floor(10): " + main.bst.floor(10));
        System.out.println("ceiling(10): " + main.bst.ceiling(10));

        System.out.println("rank(1): " + main.bst.rank(1));
        System.out.println("rank(8): " + main.bst.rank(8));
        System.out.println("rank(12): " + main.bst.rank(12));
        System.out.println("rank(21): " + main.bst.rank(21));
        System.out.println("rank(30): " + main.bst.rank(30));

        System.out.println("select(-1): " + main.bst.select(-1));
        System.out.println("select(0): " + main.bst.select(0));
        System.out.println("select(1): " + main.bst.select(1));
        System.out.println("select(2): " + main.bst.select(2));
        System.out.println("select(3): " + main.bst.select(3));
        System.out.println("select(4): " + main.bst.select(4));
        System.out.println("select(30): " + main.bst.select(30));
*/

        //main.bst.deleteMin();
        //main.bst.deleteMin();
        //main.bst.deleteMin();
        //main.bst.deleteMin();

        //main.bst.deleteMax();
        //main.bst.deleteMax();
        //main.bst.deleteMax();
        //main.bst.deleteMax();
        //main.bst.delete(20);
       // main.bst.print();
        main.queues();

    }//ends public static void main
}//ends Driver


