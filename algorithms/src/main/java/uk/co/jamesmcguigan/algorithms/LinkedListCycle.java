package uk.co.jamesmcguigan.algorithms;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        if (head.next == null) {
            return false;
        }

        ListNode cycle1 = head;
        ListNode cycle2 = head;

        while (true) {
            //fail case
            if (cycle2.next == null) {
                return false;
            }
            //fail case
            if (cycle2.next.next == null) {
                return false;
            }
            cycle1 = cycle1.next;
            cycle2 = cycle2.next.next;
            //success case
            if (cycle1.equals(cycle2)) {
                return true;
            }
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
