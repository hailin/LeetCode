import java.util.*;

public class TreetTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            list.add(cur.val);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        return list;
    }

    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            cur = cur.right;
        }

        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }

        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return new ArrayList<>(queue);

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            queue.offerFirst(cur.val);

            if (cur.left != null) {
                stack.push(cur.left);
            }

            if (cur.right != null) {
                stack.push(cur.right);
            }
        }

        return new LinkedList<>(queue);
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
