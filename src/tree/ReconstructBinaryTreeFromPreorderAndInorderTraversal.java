package tree;

/**
 * 105:
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ReconstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, inorder.length - 1, 0);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int inStart, int inEnd, int preStart) {
        if (inEnd < inStart || preStart >= preorder.length) return null;

        TreeNode node = new TreeNode(preorder[preStart]);

        int nodeIndex = 0;

        while (inorder[nodeIndex] != node.val) nodeIndex++;

        int leftLen = nodeIndex - inStart;

        node.left = helper(preorder, inorder, inStart, nodeIndex - 1, preStart + 1);
        node.right = helper(preorder, inorder, nodeIndex + 1, inEnd, preStart + leftLen + 1);

        return node;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
