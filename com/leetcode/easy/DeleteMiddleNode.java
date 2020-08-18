package com.leetcode.easy;

import com.leetcode.model.ListNode;


/*
 *  实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 *  链接：https://leetcode-cn.com/problems/delete-middle-node-lcci/
 */
public class DeleteMiddleNode {
    public void deleteNode(ListNode node) {
        if(node.next == null)
        {
            node = null;
            return;
        }
        ListNode nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
        return;
    }
}