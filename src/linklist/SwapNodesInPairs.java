package linklist;

/**
 * 24:
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;

        return swapPairs(head, head.next);
    }

    private ListNode swapPairs(ListNode first, ListNode second) {
        if (first == null || second == null) return first;

        ListNode temp = second.next;

        first.next = swapPairs(temp, temp == null ? null : temp.next);
        second.next = first;

        return second;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
