package com.leetcode;

import java.util.Arrays;

import com.leetcode.easy.DailyTemperature;
import com.leetcode.easy.LinkedListUtil;
import com.leetcode.model.ListNode;

public class Solution {
    public static void main(final String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(LinkedListUtil.isPalindrome(head));
    }
}