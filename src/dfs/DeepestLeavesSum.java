package dfs;

/**
 * 1302:
 *
 * Given a binary tree, return the sum of values of its deepest leaves.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The value of nodes is between 1 and 100.
 */
public class DeepestLeavesSum {
    int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        int depth = getDepth(root);
        dfs(depth, root, 1);
        return sum;
    }

    private void dfs(int maxDepth, TreeNode node, int level) {
        if (node == null) return;
        if (level == maxDepth) sum += node.val;

        dfs(maxDepth, node.left, level + 1);
        dfs(maxDepth, node.right, level + 1);
    }

    private int getDepth(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
