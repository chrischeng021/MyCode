package com.leetcode.easy;

import com.leetcode.model.ListNode;

public class LinkedListUtil {
    // 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
    // 注意：本题相对原题稍作改动
    // 链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
    public static int kthToLast(ListNode head, int k) {
        if(head.next == null && k == 1)
        {
            return head.val;
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

        return slow.val;
    }
        
    // 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
    // 请你返回该链表所表示数字的 十进制值 。
    // 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
    public static int getDecimalValue(ListNode head) {
        int sum = 0;
        while(head != null)
        {
            sum = sum * 2 + head.val;
            head = head.next;
        }
        return sum;
    }
}