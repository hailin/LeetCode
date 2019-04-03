/**
 * LeetCode 965:
 *
 * A binary tree is univalued if every node in the tree has the same value.
 *
 * Return true if and only if the given tree is univalued.
 *
 * Example 1:
 *
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 *
 * Example 2:
 *
 * Input: [2,2,2,5,2]
 * Output: false
 *
 */
public class UnivaluedBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        boolean uniqueLeft = true;
        boolean uniqueRight = true;

        if (root.left != null) {
            uniqueLeft = root.val == root.left.val && isUnivalTree(root.left);
        }

        if (root.right != null) {
            uniqueRight = root.val == root.right.val && isUnivalTree(root.right);
        }

        return uniqueLeft && uniqueRight;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
