import java.util.LinkedList;
import java.util.List;

/**
 * solves the problem Binary Tree Level Order Traversal
 */
class Solution {


     public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
     this.val = val;
     this.left = left;
     this.right = right;
     }
     }//ends class TreeNode


    /**
     * The trick is that I actually do an in-order traversal, but I keep track of which level I am on          *recursively so
     * I can return back to previous levels and add to the correct list index there.
     * @param root the entry point to the tree
     * @return returns a List<List<Integer>> where each inner list holds the values of the nodes on that        level.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        //the list we will return
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        //pass to the recursive helper function that does all the work with the starting point of level 0.
        list = loHelper(root, 0, list);
        //after the recursive calls are finished list is updated to the correct representation so we               //return it
        return list;
    }//ends the levelOrder method

    /**
     * recursive helper function that does the heavy recursive lifting for levelOrder
     * @param x current Node
     * @param lvl current level
     * @param list the list representation of the tree
     * @return a list of lists which store the values in the nodes: level |-> inner list
     */
    public List<List<Integer>>  loHelper(TreeNode x, int lvl,List<List<Integer>> list){
        //base case, at the end of a branch, return the list as it is
        if (x == null) return list;
        //resize the list if there is no list created at that point
        if(lvl >= list.size())   list.add(new LinkedList<Integer>());
        //adds the node`s value to the correct sub list matching it`s level
        list = update(x,lvl, list);
        //calls it`self recursively on it`s left and right children, getting updated along the way
        list = loHelper(x.left,lvl+1,list);
        list = loHelper(x.right,lvl+1,list);

        return list;
    }

    public List<List<Integer>> update(TreeNode x, int lvl, List<List<Integer>> list){
        List<Integer> copy =list.get(lvl);
        copy.add(x.val);
        list.set(lvl,copy);
        return list;
    }//ends update


    //main just creates the example given and runs the code on it and outputs it simmilarly
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Integer>> l1 = new LinkedList<List<Integer>>();
        //setting links between nodes to emulate the tree structure in the example
        TreeNode seven = new TreeNode(7, null,null);
        TreeNode fifteen= new TreeNode(15, null,null);
        TreeNode twenty = new TreeNode(20,fifteen,seven);
        TreeNode nine = new TreeNode(9,null,null);
        TreeNode three = new TreeNode(3,nine,twenty);


        //passing in the root
        l1 = sol.levelOrder(three);

        //print out the lists
        System.out.println('[');
        for(int i = 0; i < l1.size(); i++){
            System.out.print("\t[");
            for(int j = 0; j < l1.get(i).size();j++){
                if(j == l1.get(i).size() - 1)
                    System.out.print(l1.get(i).get(j) + " ]\n");
                else
                    System.out.print(l1.get(i).get(j) + " , ");

            }
        }//ends print outer loop
        System.out.println(']');

    }//ends public static void main

}//ends class Solution}//ends class Solution