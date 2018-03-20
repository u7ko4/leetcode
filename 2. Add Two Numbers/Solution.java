public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int sum = v1 + v2 + carry;
            carry = sum >= 10 ? 1 : 0;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) current.next = new ListNode(1);
        return dummy.next;
    }
}