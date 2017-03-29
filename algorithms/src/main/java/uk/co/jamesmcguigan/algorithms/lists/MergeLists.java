package Lists;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by James McGuigan on 13/09/2015.
 */
public class MergeLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public void mergeTwoSortedLists(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        //work backwards
        while (k >= 0) {
            if (j < 0 || (i >= 0 && A[i] > B[j]))
                A[k--] = A[i--];
            else
                A[k--] = B[j--];
        }
    }


//    Say you have an array for which the ith element is the price of a given stock on day i.
//
//    Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

//Biggest difference between siblings.  Sequential ordering.

    public int maxProfit2(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++){
            int diff = prices[i]-prices[i-1];
            if(diff > 0){
                profit += diff;
            }
        }
        return profit;
    }


//    Say you have an array for which the ith element is the price of a given stock on day i.
//
//    If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    //From biggest trough, identify difference between that and biggest peak.
    public int maxProfit(int[] prices) {
        int profit = 0;
        int minElement = Integer.MAX_VALUE;
        for(int i=0; i<prices.length; i++){
            profit = Math.max(profit, prices[i]-minElement);
            minElement = Math.min(minElement, prices[i]);
        }
        return profit;
    }



    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(1000, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if(o1.val>o2.val){
                    return 1;
                }
                else if(o1.val==o2.val){
                    return 0;
                }
                return -1;
            }
        });

        //add first node of each list to the queue
        for (ListNode list : lists) {
            if (list != null)
                priorityQueue.add(list);
        }

        ListNode head = new ListNode(0);
        ListNode p = head; // serve as a pointer/cursor

        while (priorityQueue.size() > 0) {
            ListNode temp = priorityQueue.poll();
            //poll() retrieves and removes the head of the queue - q.
            p.next = temp;
            //keep adding next element of each list
            if (temp.next != null)
                priorityQueue.add(temp.next);
            p = p.next;
        }

        return head.next;
    }
}