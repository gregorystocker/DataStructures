import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<Key extends Comparable, Value> {
    //Class member variables
public Node root;


    /**
     * Get the value at the specified key
     * @param key
     *
     */
public Value get(Key key){
    //calls recursive get
    return get(key, root);
}//ends get

    /**
     * recursive get method. Root in this context is the current node since it represents the 'root' of it`s own subtree
     * @param key
     * @param root
     */
public Value get(Key key, Node root) {
    if (root == null)
        return null;

    int cmp = key.compareTo(root.key);
    if (cmp == 0) return root.val;
    else if (cmp < 0) return get(key, root.left);
    else return get(key, root.right);
}//ends recursive get helper



public void put(Key key, Value val){
    if(root == null) {
        root = new Node(key, val, 1);
        return;
    }
    put(key, val, root);
}

    public void put(Key key, Value val, Node root){
        int cmp = key.compareTo(root.key);
        //search hit
        if (cmp == 0)  root.val = val;
        //go left
        else if (cmp < 0){
            if(root.left == null){//search hit
                Node nn = new Node(key, val, 1);
                root.left = nn;
            }else{
            put(key,val, root.left);}
            }//ends left
        else{
            //go right
            if(root.right == null){//search hit
                Node nn = new Node(key, val, 1);
                root.right = nn;
            }else{
                put(key,val, root.right);}
        }//end right
        root.N = size(root.left) + size(root.right) + 1;
    }//ends put

    public int size() {
    return size(root);
}

    public Key min(){
        return min(root).key;
    }
    public Node min(Node x){
        //no left subtree
        if(x.left == null) return x;
        //has a left subtree
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    public Node max(Node x){
    //has no right subtree
        if(x.right == null)
            return x;
        return max(x.right);
    }

    public Key floor(Key key){

        Node floored =  floor(key, root);
        if(floored == null)
            return null;
        else return floored.key;
    }

    /**
     * completely from scratch implementation, just so happens to be line for line the same as the book, except for
     * naming vars!
     * @param key
     * @param x
     * @return
     */
    public Node floor(Key key, Node x){
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        //found the exact key
        if(cmp == 0) return x;
        //key is less than x
        else if(cmp < 0) return floor(key, x.left);
        //key is greater than x
        else{
            if(x.right == null)
                return x;
            else{
                Node checkRight = floor(key, x.right);
                if(checkRight == null)return x;
                else return checkRight;
            }
        }
    }//ends floor

    public Key ceiling(Key key) {

        Node ceil =  ceiling(key, root);
        if(ceil == null)
            return null;
        else return ceil.key;
    }

    public Node ceiling(Key key, Node x) {
        if(x == null)    return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)//search hit
            return x;
        else if(cmp > 0) {//go right
            return ceiling(key, x.right); }
        else{//go left
            Node checkLeft = ceiling(key, x.left);
            if(checkLeft == null)
                return x;
            else return checkLeft;
        }
    }

    private int size(Node x){
    if (x == null) return 0;
    else return x.N;
    }

    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        // if one isn`t found, you`d be adding it where it would have no children, and no possibility of a smaller node
        //elsewhere so it would be rank 0.
        if(x == null)   return 0;
        int cmp = key.compareTo(x.key);
        //if less, pass left
        if  (cmp < 0)   return rank(key,x.left);
        //if greater, we need to pass right, BUT in order to account for all lesser nodes that come before it that are not
        //in the right subtree, we add the size of the left subtree plus one for the root which are both
        //by definition lesser than the target node.
        else if(cmp > 0)    return 1 + size(x.left) + rank(key, x.right);
        else    return size(x.left);
    }

    public Key select(int k){
        Node n = select(root,k);
        if(n == null)   return null;
        return n.key;
    }
    public Node select(Node x, int k){
        if(x == null)   return null;
        //t is size of left subtree
        int t = size(x.left);
        // if left subtree`s size > target rank, target is in left subtree, so pass it left
        if (t > k)  return select(x.left, k);
        //if left subtree`s size > target rank, target MAY be in right subtree, but we need to account for the smaller
        //nodes in the left subtree as well as the root
        else if(t < k){return select(x.right, k - (t + 1));}
        //otherwise this is a search hit
        else    return x;
    }

    public void deleteMin(){
            root =  deleteMin(root);
    }

    public Node deleteMin(Node x){

        //if we`ve found the min, return it`s right to be updated to
        //be linked to by min`s parent
        if(x.left == null)  return x.right;
        //recursively update all of the parents along the way to the result
        //of the function
        x.left = deleteMin(x.left);
        //update the sizes on the way back up the tree
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }
    public Node deleteMax(Node x){
        //if were at the max, return the max`s left child
        if(x.right == null) return x.left;
        //update the tree recursively
        x.right = deleteMax(x.right);
        //update the sizes on the way back up
        x.N = size(x.left) + size(x.right) +  1;
        return x;
    }

    /**
     * Delete follows a similar algorithm to delete nodes with 1 and 0 children, the steps to delete a node w/ 2 children
     * are based on replacing the deleted node with it`s successor in the steps below:
     * Save a link to the node to be deleted in t.
     * ■ Set x to point to its successor min(t.right).
     * ■ Set the right link of x (which is supposed to point to the BST containing all the keys larger than x.key)
     * to deleteMin(t.right), the link to the BST containing all the keys that are larger than x.key after the deletion.
     * ■ Set the left link of x (which was null) to t.left
     * (all the keys that are less than both the deleted key and its successor).
     */
    public void delete(Key key){
        root = delete(key, root);
    }

    public Node delete(Key key, Node x){
        if(x == null)    return null;
        int cmp = x.key.compareTo(key);

        if(cmp < 0) x.left = delete(key,x.left);
        else if (cmp > 0)   x.right = delete(key, x.right);
        else {
            //we have found it
            //handles case 0 children and case 1 child
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            else {
                //case of 2 children
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right); // sets it` to the subtree that would have been at it`self, but it is the
                // min that has been deleted so it points instead to it`s successor.
                x.left = t.left;
            }
        }
            x.N = size(x.left) + size(x.right) + 1;
            return x;
    }//ends delete


    /**
     * print`s an in-order traversal
     */
    public void print(){
        if(root == null) {
            System.out.println("null");
            return;
        }
        print(root);
    }

    private void print(Node root){
        if(root.left != null){ print(root.left);}
        System.out.println("( " + root.key  + " , " + root.val + " )");
        if(root.right != null){  print(root.right);}
    }

    /**
     * prints the tree breadth-first
     * uses a Queue where a node is stored, printed, poll`d off the q, and then it`s children are added to the q,
     * and the q is then passed recursively to it`self.
     */

    public void printBFS(){
        Queue<Node> q = new LinkedList<>();
        //add the root to the q
        q.add(root);
        //pass the q with only the root added to the recursive method
          printBFS(q);
        }//ends printBFS()

    private void printBFS(Queue<Node> q){
        //empty q, return
        if(q.peek() == null)    return;
        //print out the node and remove it from the q
        Node visited = q.poll();
        System.out.println("( " + visited.key  + " , " + visited.val + " )");
        //add the node`s children to the q
        q.add(visited.left);
        q.add(visited.right);
        //call recursively passing the new q without the visited node, but containing it`s children.
        printBFS(q);
    }//ends recursive printBFS(Queue<Node> q)


    /**
     * a method for appending one queue to another
     * @param q1 first Queue that will be the main Queue
     * @param q2 second Queue that will be added to q1 and emptied in the process
     */
    public void merge(Queue<Key> q1, Queue<Key> q2){
        if(q1 == null || q2 == null)   return;
        while(q2.peek() != null){
            q1.add(q2.poll());
        }
    }//ends merge

    /**
     * gives a list representation of the level order of the tree:
     *     For example:
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *   is output as:
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     * The trick is that I actually do an in-order traversal, but I keep track of which level I am on recursively so
     * I can return back to previous levels and add to the correct list index there.
     * @param root the entry point to the tree
     * @return returns a List<List<Value>> where each inner list holds the values of the nodes on that level.
     */
    public List<List<Value>> levelOrder(Node root) {
        //the list we will return
        List<List<Value>> list = new LinkedList<List<Value>>();
        //pass to the recursive helper function that does all the work with the starting point of level 0.
        list = loHelper(root, 0, list);
        //after the recursive calls are finished list is updated to the correct thing.
            return list;
    }//ends the levelOrder method

    /**
     * recursive helper function that does the heavy recursive lifting for levelOrder
     * @param x current Node
     * @param lvl current level
     * @param list the list representation of the tree
     * @return a list of lists which store the values in the nodes: level |-> inner list
     */
    public List<List<Value>>  loHelper(Node x, int lvl,List<List<Value>> list){
        //base case, at the end of a branch, return the list as it is
        if (x == null) return list;
        //resize the list if there is no list created at that point
        if(lvl >= list.size())   list.add(new LinkedList<Value>());
        //adds the node`s value to the correct sub list matching it`s level
        list = update(x,lvl, list);
        //calls it`self recursively on it`s left and right children, getting updated along the way
        list = loHelper(x.left,lvl+1,list);
        list = loHelper(x.right,lvl+1,list);

        return list;
    }

    /**
     * for testing purposes only
     * @param key key
     * @param val value
     * @return node(key,val,0)
     */
    public Node getNode(Key key, Value val){
        Node nn = new Node(key, val,  0);
        return nn;
    }

    /**
     * updates list representation with a new node`s value
     * @param x Node to pull value from
     * @param lvl level of x
     * @return the updated list
     */
    public List<List<Value>> update(Node x, int lvl, List<List<Value>> list){
       List<Value> copy =list.get(lvl);
        copy.add(x.val);
        list.set(lvl,copy);
        return list;
    }//ends update

    public class Node{
        private Node left;
        private Node right;
        private Key key;
       public Value val;
        private int N;

        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
             this.N = N;
        }

        public Node(Node left,Key key, Value val, int N, Node right){
            this.left = left;
            this.key = key;
            this.val = val;
            this.N = N;
            this.right = right;
        }
    }


}
