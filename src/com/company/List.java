package com.company;

public class List {

   private static ListNode head;

    static class ListNode {

        private int value;
        ListNode next;

        ListNode(int val) {
            value = val;
            next = null;
        }
    }

    void insert(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        head = newNode;
    }

    int length(ListNode curr) {
        if(curr != null) return length(curr.next) + 1;
        else return 0;
    }

    //Floyd's Cycle Finding Algorithm
    ListNode findLoopPosition(List llist) {

        ListNode fast = llist.head;
        ListNode slow = llist.head;
        int i = 0;
        boolean met = false;
        while(fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                met = true;
                break;
            }
        }

        if(met == true) {
            slow = llist.head;
            while(slow != fast) {
                System.out.println("slow: " + slow.value + " fast: " + fast.value);
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }

        return null;
    }

    boolean deleteWithVal(int val) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null) {
            if(curr.value == val) {
                System.out.println("Deleting node with value " + val);
                if(curr == head) {
                    head = curr.next;
                    return true;
                }
                prev.next = curr.next;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    void print(List llist) {
        ListNode curr = llist.head;
        while(curr != null) {
                System.out.print(curr.value);
                curr = curr.next;
                if(curr != null) {
                    System.out.print(", ");
                }
        }
        System.out.println();
    }

    List merge(List list1, List list2) {
        List merged = new List();
        mergeLists(merged, list1.head, list2.head);
        return merged;
    }

    void mergeLists(List merged, ListNode node1, ListNode node2) {

        if(node1 == null && node2 == null) {
            return;
        }

        if(node2 == null || node1.value >= node2.value) {
            merged.insert(node1.value);
            mergeLists(merged, node1.next, node2);
        }
        else {
            merged.insert(node2.value);
            mergeLists(merged, node1, node2.next);
        }
        return;
    }

    public static void main(String[] args) {

        List llist = new List();
        llist.insert(1);
        llist.head.next = new ListNode(2);
        llist.head.next.next = new ListNode(3);
        llist.head.next.next.next = new ListNode(4);
        llist.head.next.next.next.next = new ListNode(5);
        llist.head.next.next.next.next.next = new ListNode(6);
        llist.head.next.next.next.next.next.next = new ListNode(7);
        llist.head.next.next.next.next.next.next.next = new ListNode(9);
        ListNode loopStart = new ListNode(10);
        llist.head.next.next.next.next.next.next.next.next = loopStart;
        loopStart.next = llist.head.next.next.next.next;

        ListNode loops = llist.findLoopPosition(llist);
        System.out.println("Loop Starts at: " + loops.value);

    }
}
