/**
 * LeetCode 106:
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) return null;

        return buildTreeHelper(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int inStart, int inEnd, int postEnd) {
        if (inEnd < inStart || postEnd < 0) return null;

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;

        while (inorder[rootIndex] != rootVal) rootIndex++;


        root.left = buildTreeHelper(inorder, postorder, inStart, rootIndex - 1, postEnd - (inEnd - rootIndex) - 1);
        root.right = buildTreeHelper(inorder, postorder, rootIndex + 1, inEnd, postEnd - 1);
        return root;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
