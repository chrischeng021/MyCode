package com.leetcode.easy;

import com.leetcode.model.ListNode;


/*
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 */
public class KthNodeFromList {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head.next == null && k == 1)
        {
            return head;
        }
        int gap = k - 1;
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i < gap && fast.next!= null; i++)
        {
            fast = fast.next;
        }
        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}