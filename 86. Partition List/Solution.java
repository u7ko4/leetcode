public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur;
        // Find the first bigger than x node
        while (pre.next != null && pre.next.val < x)
            pre = pre.next;
        cur = pre;
        while (cur.next != null) {
            if (cur.next.val < x) {
                ListNode tmp = cur.next;
                cur.next = tmp.next;
                tmp.next = pre.next;
                pre.next = tmp;
                pre = pre.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}