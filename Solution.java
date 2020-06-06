import java.util.List;
import java.util.LinkedList;





 class Solution {


    //holds the list of lists, each inner list representing a different "level" of our binary tree.
    List<List<Integer>> list = new LinkedList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //the list that will hold just our root node
        LinkedList<TreeNode> rootList = new LinkedList<TreeNode>();
        //add the root to it`s list
        rootList.add(root);
        //pass this list to the recursive helper function
        levelOrder(rootList, 0);
        //returns the class-level variable that is being modified through each iteration of the helper function
        return list;
    }

    //the recursive helper function for levelOrder that passes the list
    // representing the current state of the tree traversal
    public void levelOrder(LinkedList<TreeNode> l, int count) {
        //case: end of a branch
        if(l.size() == 0 )   return;
        //record the parent (already checked for null so wont throw nullPointerException)
        TreeNode parent = l.get(0);
        //copy parent`s children to l
        if(parent.left != null) l.add(parent.left);
        if(parent.right != null) l.add(parent.right);

        //removes the parent from the list
        TreeNode x = l.removeFirst();
        record(x,count);
        //awkward correcting for starting at 0 instead of 1, count is used to calculate which inner list the element
        //is added to
        if(count == 0 || count == 1)
            count++;
        else count += 2;
        //calls it`self recursively down the tree
        levelOrder(l,count);
    }//end recursive levelOrder

     /**
      * Adds x to the master list and uses the fact that each level can hold a total of  2^lvl# TreeNodes
      * floor(log base 2 of lvl) is used to go from the node count to the level
      * @param x the node whose val is to be added
      * @param count used to calculate the sub list x.val will be added to
      */
     private void record(TreeNode x, int count){
         //calculate
         int lvl = (int) Math.floor(Math.log(count + 1)/Math.log(2));
        //if there is no list at this index, create one first se we don`t get an out of bounds error
         if(lvl >list.size()-1) {
             list.add(lvl,new LinkedList<Integer>());
         }
         //loops through the list and records the values of the TreeNodes to the class level list

             list.get(lvl).add(x.val);

     }


     public void print(){
         System.out.println('[');
        for(int i = 0; i < list.size(); i++){
            System.out.print("\t[");
            for(int j = 0; j < list.get(i).size(); j++){
                if(j == list.get(i).size() - 1)
                    System.out.print(list.get(i).get(j) + " ]\n");
                else
                    System.out.print(list.get(i).get(j) + " , ");
            }
        }
         System.out.print("]\n");
     }


    //inner TreeNode class that was given
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


    public static void main(String[] args){
        Solution s = new Solution();

        s.print();
        TreeNode sixtythree = new TreeNode(63, null,null);
        TreeNode sixtytwo = new TreeNode(62, null,null);
        TreeNode sixtyone = new TreeNode(61, null,null);
        TreeNode fiftythree = new TreeNode(53, sixtytwo,sixtyone);
        TreeNode fiftytwo = new TreeNode(52, sixtythree,null);
        TreeNode fiftyone = new TreeNode(51, null,null);
        TreeNode fifty = new TreeNode(50, null,null);
        TreeNode fortythree = new TreeNode(43, fiftythree,null);
        TreeNode fortytwo = new TreeNode(42, fiftytwo,fiftytwo);
        TreeNode fortyone = new TreeNode(41, fifty,fiftyone);
        TreeNode forty = new TreeNode(40, null,null);
        TreeNode seven = new TreeNode(7, forty,fortythree);
        TreeNode fifteen= new TreeNode(15, fortyone,fortytwo);
        TreeNode twenty = new TreeNode(20,fifteen,seven);
        TreeNode nine = new TreeNode(9,null,null);
        TreeNode three = new TreeNode(3,nine,twenty);
        s.levelOrder(three);
        System.out.println("Solving this level order traversal of a binary tree");
        s.print();
        //s.printNodes(three);


    }//ends public static void main

}//ends class Solution