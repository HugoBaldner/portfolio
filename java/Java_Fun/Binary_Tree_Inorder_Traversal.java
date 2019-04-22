/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList();
        out = helper(out, root);
        return out;
    }
    
    private List<Integer> helper(List<Integer> out, TreeNode n){
        if(n != null){
            out = helper(out, n.left);
            out.add(n.val);
            out = helper(out, n.right);
            
        }
        return out;
    }
}