package com.leetcode.hard;

import com.leetcode.LinkedListUtils;
import com.leetcode.model.ListNode;

public class MergeKLists {
    // 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
    // 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/
    // TODO: 可用分治或归并思路优化
    public ListNode mergeKListsI(ListNode[] lists) {
        if(lists.length == 1){
            return lists[0];
        }
        if(lists.length == 2){
            return LinkedListUtils.mergeTwoLists(lists[0], lists[1]);
        }
        ListNode mergedHead = LinkedListUtils.mergeTwoLists(lists[0], lists[1]);
        for(int i = 2; i < lists.length; i++){
            mergedHead = LinkedListUtils.mergeTwoLists(mergedHead, lists[i]);
        }
        return mergedHead;
    }
}