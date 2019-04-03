import java.util.NoSuchElementException;
import java.util.Stack;

/**
 *
 * LeetCode 173:
 *
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 *
 *
 * Example:
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // return 3
 * iterator.next();    // return 7
 * iterator.hasNext(); // return true
 * iterator.next();    // return 9
 * iterator.hasNext(); // return true
 * iterator.next();    // return 15
 * iterator.hasNext(); // return true
 * iterator.next();    // return 20
 * iterator.hasNext(); // return false
 *
 *
 * Note:
 *
 * next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class BinarySearchTreeIterator {
    Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        if (stack.isEmpty()) throw new NoSuchElementException();

        TreeNode minNode = stack.pop();

        TreeNode cur = minNode.right;

        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return minNode.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
