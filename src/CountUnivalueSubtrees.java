/**
 * LeetCode 250:
 *
 * Given a binary tree, count the number of uni-value subtrees.
 *
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example :
 *
 * Input:  root = [5,1,5,5,5,null,5]
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 *
 * Output: 4
 */
public class CountUnivalueSubtrees {
    int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        isUnique(root);
        return count;
    }

    private boolean isUnique(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }

        boolean uniqueLeft = node.left == null || (isUnique(node.left) && node.val == node.left.val);

        boolean uniqueRight = node.right == null || (isUnique(node.right) && node.val == node.right.val);

        if (uniqueRight && uniqueLeft) {
            count++;
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
