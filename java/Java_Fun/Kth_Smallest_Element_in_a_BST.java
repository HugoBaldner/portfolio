
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
    TreeNode[] out;
    public int kthSmallest(TreeNode root, int k) {
        out = new TreeNode[k];
        helper(root, 0);
        return out[k-1].val;
    }
    
    private int helper(TreeNode n , int c){
        
        if(n != null && c < out.length){
            c = helper(n.left, c);
            if(c < out.length){
                out[c] = n;
                c++;
            }
            
            if(c < out.length){
                c = helper(n.right, c);
            }
        }
        
        return c;
        
    }
}
