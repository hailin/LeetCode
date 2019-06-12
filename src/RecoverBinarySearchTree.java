import java.util.Stack;

/**
 *
 * LeetCode 99:
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 *
 *
 * Approach 1:
 *
 * Time O(n), space O(n)
 *
 * Use iterative inorder traversal, find the swapped nodes, and swap their values
 *
 * two scenarios:
 * 1: swapped nodes are not to each other, simply swap their values
 * 2: swapped nodes are not next to each other, swapped two outer(inorder) nodes
 *
 * Apprach 2:
 * Use Morris Traversal
 */
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        TreeNode badNode1Prev = null;
        TreeNode badNode1 = null;
        TreeNode badNode2Prev = null;
        TreeNode badNode2 = null;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();


            if (prev != null && prev.val > node.val) {
                if (badNode1 == null) {
                    badNode1Prev = prev;
                    badNode1 = node;
                } else {
                    badNode2Prev = prev;
                    badNode2 = node;
                }
            }

            prev = node;
            cur = node.right;
        }

        if (badNode2Prev == null) {
            swap(badNode1Prev, badNode1);
            return;
        }

        swap(badNode1Prev, badNode2);
    }

    private void swap(TreeNode x, TreeNode y) {
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
