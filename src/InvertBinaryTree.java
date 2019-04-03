public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);

        root.left = left;
        root.right = right;

        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
