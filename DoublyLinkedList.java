import java.util.Iterator;

/**
 * my implementation of a doublyLinkedList
 * @param <Value>
 */
public class DoublyLinkedList<Value>{

    Node head = null;
    Node tail = null;
    int size = 0;

    /**
     * inserts a new node at the head of the list
     * @param val
     */
    public void headInsert( Value val){
        //case1 empty list
        if(size == 0){head = new Node(null,  val, null); tail = head;}
        else{
            Node nn = new Node(null, val, head);
            head.prev = nn;
            head = nn;
        }

        size++;

    }//ends head insert

    public void tailInsert( Value val){
        //case1 empty list
        if(size == 0){tail = new Node(null,  val, null); head = tail;}
        else{
            Node nn = new Node(tail, val, null);
            tail.next = nn;
            tail = nn;
        }
        size++;
    }//ends head insert

    public void insertAt(Value val, int pos)throws IndexOutOfBoundsException{
        //position out of bounds
        if(pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        //head insert
        if(pos == 0) headInsert(val);
        //tail insert
        else if(pos == size-1)tailInsert(val);
        //normal case
        else{
            //iterate through the linked list
            Node iter = head;
            int counter = 0;
            while (counter < pos){
                iter = iter.next;
                counter++;
            }
            Node nn = new Node(iter.prev,val,iter);
            iter.prev.next = nn;
            iter.prev = nn;
            size++;
        }
    }//ends insertAt

    public Value headDelete()throws IndexOutOfBoundsException{
        //empty list
        if(size == 0)
            throw new IndexOutOfBoundsException();

        else {
            Value headValue = head.val;
         if (size == 1) {
                head = null;
                tail = head;

            } else {
                Node temp = new Node(head);
                head.next.prev = null;
                head = temp.next;

            }
         size--;
         return headValue;

        }//ends headDelete
    }

    public Value tailDelete()throws IndexOutOfBoundsException{
        //empty list
        if(size == 0)
            throw new IndexOutOfBoundsException();

        else {
            Value tailValue = tail.val;
            if (size == 1) {
                tail = null;
                head = tail;

            } else {
                Node temp = new Node(tail);
                tail.prev.next = null;
                tail = temp.prev;

            }
            size--;
            return tailValue;

        }
    }//ends tail delete

    public Value deleteAt( int pos)throws IndexOutOfBoundsException{
        //position out of bounds
        if(pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        //head delete
        if(pos == 0) return headDelete();
            //tail insert
        else if(pos == size-1)return tailDelete();
            //normal case
        else{
            //iterate through the linked list
            Node iter = head;
            int counter = 0;
            while (counter < pos){
                iter = iter.next;
                counter++;
            }

            iter.next.prev = iter.prev;
            iter.prev.next = iter.next;
            size--;
            return iter.val;
        }
    }//ends insertAt


    public void print() {
        if (size == 0)
            return;

        Node iter = head;

        while (iter != null){
            System.out.println(iter.val);
            iter = iter.next; }
    }


    public  class Node{
        Node next;
        Node prev;
        Value val;


        Node(Node prev, Value val, Node next){
            this.prev = prev;
            this.next = next;
            this.val = val;
        }

        Node(Node copy){
            this.prev = copy.prev;
            this.next = copy.next;
            this.val = copy.val;
        }
    }


}
