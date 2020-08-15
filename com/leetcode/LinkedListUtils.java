package com.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.leetcode.model.ListNode;
import com.leetcode.model.Node;

public class LinkedListUtils {
    // 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
    // 注意：本题相对原题稍作改动
    // 链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
    public static int kthToLast(ListNode head, int k) {
        if (head.next == null && k == 1) {
            return head.val;
        }
        int gap = k - 1;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < gap && fast.next != null; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
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
        while (head != null) {
            sum = sum * 2 + head.val;
            head = head.next;
        }
        return sum;
    }

    // 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
    // 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
    public ListNode reverseList(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode cur = null;
        ListNode pre = head;
        while (pre != null) {
            ListNode nextPre = pre.next;
            pre.next = cur;
            cur = pre;
            pre = nextPre;
        }
        return cur;
    }

    // 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
    // O(n) 复杂度的实现需要使用临时缓冲区存放已经出现过的唯一结点
    // 如果不使用临时缓冲区，则需要牺牲空间复杂度，进行O(n * n)复杂度的遍历
    // 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci/
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode visualHead = head;
        Set<Integer> uniqueVal = new HashSet<Integer>() {
            private static final long serialVersionUID = 1L;
            {
                add(head.val);
            }
        };

        while (visualHead != null && visualHead.next != null) {
            ListNode nextNode = visualHead.next;
            if (uniqueVal.contains(nextNode.val)) {
                visualHead.next = nextNode.next;
                continue;
            } else {
                uniqueVal.add(nextNode.val);
                visualHead = visualHead.next;
            }
        }

        return head;
    }

    // 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
    // 返回删除后的链表的头节点。
    // 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null || (head.next == null && head.val != val)) {
            return head;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val != val) {
                cur = cur.next;
                continue;
            } else {
                cur.next = cur.next.next;
                break;
            }
        }
        return head;
    }

    // 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
    // 如果有两个中间结点，则返回第二个中间结点。
    // 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list/
    // 思路：两次遍历，第一次获取长度，第二次返回结点，具体方法使用快慢指针
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        int mid = (int) (length / 2); // E.g:有三个元素的链表，则我们需要返回第二个元素; 有四个元素的链表，我们需要返回第三个元素
        cur = head;
        while (mid > 0) {
            cur = cur.next;
            mid--;
        }
        return cur;
    }

    // 请判断一个链表是否为回文链表。
    // 链接：https://leetcode-cn.com/problems/palindrome-linked-list/
    // 思路:先翻转一半 再比较
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        int length = getListLength(head);
        int subLength = length / 2;
        int move = length % 2 == 0 ? subLength : subLength + 1;

        ListNode rightSubHead = head;
        while (move > 0) {
            rightSubHead = rightSubHead.next;
            move--;
        }
        ListNode leftReverseHead = reverseSubList(head, subLength);

        while (rightSubHead != null && leftReverseHead != null) {
            if (rightSubHead.val != leftReverseHead.val) {
                return false;
            }
            rightSubHead = rightSubHead.next;
            leftReverseHead = leftReverseHead.next;
        }
        return true;
    }

    // 编写一个程序，找到两个单链表相交的起始节点。
    // 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    // 思路：分别使用X && Y两个指针遍历A，B；任意指针遍历到结尾时，遍将其指向另一链表的头结点重新遍历
    // 当X==Y时，即为两个链表的相交结点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cursorA = headA;
        ListNode cursorB = headB;

        int cursorAMoves = 0;
        int sumLength = getListLength(headA) + getListLength(headB);

        while (cursorA != cursorB && cursorAMoves < sumLength) {
            if (cursorA == null) {
                cursorAMoves++;
                cursorA = headB;
            } else {
                cursorA = cursorA.next;
                cursorAMoves++;
            }

            if (cursorB == null) {
                cursorB = headA;
            } else {
                cursorB = cursorB.next;
            }
        }

        if (cursorA == cursorB) {
            return cursorA;
        } else {
            return null;
        }
    }

    // 翻转指定长度为length的链表
    private static ListNode reverseSubList(ListNode head, int length) {
        if (null == head || head.next == null || length <= 1) {
            return head;
        }
        ListNode cur = null;
        ListNode pre = head;
        while (pre != null && length > 0) {
            ListNode nextPre = pre.next;
            pre.next = cur;
            cur = pre;
            pre = nextPre;
            length--;
        }
        return cur;
    }
    
    // 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    // 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/
    // Sample: 
    // 输入: 1->2->3->4->5->NULL, m = 2, n = 4
    // 输出: 1->4->3->2->5->NULL
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n){
            return head;
        }
        int length = n - m + 1;// 待翻转的结点个数
        int curIndex = 1;
        ListNode preNode = head; // 第一个需要翻转的结点
        ListNode startNode = null; // 第一个需要翻转的结点的前结点

        while(curIndex < m){
            startNode = preNode;
            preNode = preNode.next;
            curIndex++;
        }
        ListNode nxtNode = preNode.next;
        for(int i = 1; i < length; i++){
            ListNode trdNode = nxtNode.next;
            nxtNode.next = preNode;
            preNode = nxtNode;
            nxtNode = trdNode;
        }

        if(startNode ==  null){
            head.next = nxtNode;
            return preNode;
        }
        else{
            startNode.next.next = nxtNode;
            startNode.next = preNode;
            return head;
        }
    }

    // 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
    // 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
    // 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
    public ListNode addTwoNumbersWithHighFromHead(ListNode l1, ListNode l2) {
        Stack<Integer> a = new Stack<Integer>();
        Stack<Integer> b = new Stack<Integer>();

        while(l1 != null){
            a.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null){
            b.push(l2.val);
            l2 = l2.next;
        }

        int cap = 0;
        ListNode head = null;
        while(!a.isEmpty() && !b.isEmpty()){
            int sum = a.pop() + b.pop() + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;

            if(head == null){
                head = new ListNode(sum);
            }
            else{
                ListNode newHead = new ListNode(sum);
                newHead.next = head;
                head = newHead;
            }
        }

        while(!a.isEmpty()){
            int sum = a.pop() + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;

            if(head == null){
                head = new ListNode(sum);
            }
            else{
                ListNode newHead = new ListNode(sum);
                newHead.next = head;
                head = newHead;
            }
        }

        while(!b.isEmpty()){
            int sum = b.pop() + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;

            if(head == null){
                head = new ListNode(sum);
            }
            else{
                ListNode newHead = new ListNode(sum);
                newHead.next = head;
                head = newHead;
            }
        }

        if(cap == 1){
            ListNode newHead = new ListNode(cap);
            newHead.next = head;
            head = newHead;
        }

        return head;
    }

    // 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    // 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 
    // 如果 pos 是 -1，则在该链表中没有环。
    // 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }   
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while(slow != fast){
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    // 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    // 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;

        while(l1 != null && l2 != null){
            int val;
            if(l1.val <= l2.val){
                val = l1.val;
                l1 = l1.next;
            }
            else{
                val = l2.val;
                l2 = l2.next;
            }
            if(head == null){
                head = new ListNode(val);
                head.next = tail;
                continue;
            }
            if(tail == null){
                tail = new ListNode(val);
                head.next = tail;
            }
            else{
                ListNode newNode = new ListNode(val);
                tail.next = newNode;
                tail = newNode;
            }
        }

        while(l1 != null){
            if(head == null){
                head = new ListNode(l1.val);
                head.next = tail;
                l1 = l1.next;
                continue;
            }
            if(tail == null){
                tail = new ListNode(l1.val);
                head.next = tail;
            }
            else{
                ListNode newNode = new ListNode(l1.val);
                tail.next = newNode;
                tail = newNode;
            }
            l1 = l1.next;
        }

        while(l2 != null){
            if(head == null){
                head = new ListNode(l2.val);
                head.next = tail;
                l2 = l2.next;
                continue;
            }
            if(tail == null){
                tail = new ListNode(l2.val);
                head.next = tail;
            }
            else{
                ListNode newNode = new ListNode(l2.val);
                tail.next = newNode;
                tail = newNode;
            }
            l2 = l2.next;
        }

        return head;
    }

    // 给定两个用链表表示的整数，每个节点包含一个数位。
    // 这些数位是反向存放的，也就是个位排在链表首部。
    // 编写函数对这两个整数求和，并用链表形式返回结果。
    // 链接：https://leetcode-cn.com/problems/sum-lists-lcci/
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = null;

        int cap = 0;
        int alen = getListLength(l1);
        int blen = getListLength(l2);

        ListNode longList = Math.max(alen, blen) == alen ? l1 : l2;
        ListNode shrtList = Math.min(alen, blen) == blen ? l2 : l1;

        while(shrtList != null){
            int sum = cap + longList.val + shrtList.val;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;

            if(tail == null){
                tail = new ListNode(sum);
                head.next = tail;
            }
            else{
                ListNode newNode = new ListNode(sum);
                tail.next = newNode;
                tail = tail.next;
            }

            shrtList = shrtList.next;
            longList = longList.next;
        }

        while(longList != null){
            int sum = cap + longList.val;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;

            if(tail == null){
                tail = new ListNode(sum);
                head.next = tail;
            }
            else{
                ListNode newNode = new ListNode(sum);
                tail.next = newNode;
                tail = tail.next;
            }
            longList = longList.next;
        }

        if(cap == 1){
            ListNode newNode = new ListNode(cap);
            tail.next = newNode;
        }

        return head.next;
    }

    // 给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素，使这个列表仍然是循环升序的。
    // 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
    // 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
    // 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个点。否则。请返回原先给定的节点。
    // 链接：https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list
    public Node insert(Node head, int insertVal) {
        if(head == null){
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        if(head.val < insertVal){
            Node cur = head;
            while(cur.next != cur && cur.next != head && cur.val <= cur.next.val && cur.next.val < insertVal){
                cur = cur.next;
            }
            // 退出循环有两种情况：
            // 1. 链表仍在递增，但此时已经找到第一个大于目标值的结点
            // 2. 已经遍历到链表的尾结点，仍然没有找到大于目标值的结点
            Node node = new Node(insertVal);
            node.next = cur.next;
            cur.next = node;
        }
        else if(head.val == insertVal){
            Node node = new Node(insertVal);
            node.next = head.next;
            head.next = node;
        }
        else{
            Node cur = head;
            while(cur.next != cur && cur.next != head && cur.val <=  cur.next.val){
                cur = cur.next;
            }
            // 此时的cur为尾结点
            if(cur.next.val > insertVal){
                Node node = new Node(insertVal);
                node.next = cur.next;
                cur.next = node;
            }
            else{
                while(cur.next.val < insertVal){
                    cur = cur.next;
                }
                Node node = new Node(insertVal);
                node.next = cur.next;
                cur.next = node;
            }
        }
        return head;
    }

    // 删除链表 M 个节点之后的 N 个节点
    // 链接：https://leetcode-cn.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode slowPtr = head;
        while(slowPtr != null){
            for(int i = 1; i < m && slowPtr != null; i++){
                slowPtr = slowPtr.next;
            }
            if(slowPtr == null){
                break;
            }
            ListNode fastPtr = slowPtr;
            for(int i = 0; i < n && fastPtr != null; i++){
                fastPtr = fastPtr.next;
            }
            if(fastPtr == null){
                break;
            }
            slowPtr.next = fastPtr.next;
            slowPtr = fastPtr.next;
        }
        return head;
    }    
    
    // 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
    // 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
    // 输入: 1->2->3->3->4->4->5
    // 输出: 1->2->5
    public ListNode deleteDuplicatesII(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode baseNode = fakeHead;
        while(baseNode.next != null){
            ListNode cur = baseNode.next;
            int val = cur.val;
            boolean shouldSkip = false;
            while(cur.next != null && cur.next.val == val){
                cur = cur.next;
                shouldSkip = true;
            }
            // 此处是重点
            // 需要根据是否跳过当前结点决定BaseNode的指向
            if(shouldSkip){
                // Sample: 1->2->2->3->4 Then should be updated to 1->3->4
                baseNode.next = cur.next;
            }
            else{
                // Sample: 1->2->3->3->4 Then should be updated to 1->2->3->3->4
                baseNode.next = cur;
                baseNode = baseNode.next;
            }
        }

        return fakeHead.next;
    }

    // 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
    // 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
    // 输入: 1->1->2
    // 输出: 1->2
    public ListNode deleteDuplicatesI(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode baseNode = head;
        
        // 找到第一个不同值的结点
        while(baseNode != null){
            int baseVal = baseNode.val;
            ListNode cursor = baseNode.next;
            while(cursor != null && cursor.val == baseVal){
                cursor = cursor.next;
            }
            baseNode.next = cursor;
            baseNode = cursor;
        }

        return head;
    }
    
    // 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
    // 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    // 例如：给定 1->2->3->4, 你应该返回 2->1->4->3.
    // 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode prePr = preHead;
        ListNode pl = head;

        while(pl != null && pl.next != null){
            ListNode pr = pl.next;
            prePr.next = pr;
            ListNode np =pr.next;
            pl.next = np;
            pr.next = pl;
            prePr = pl;
            pl = np;
        }

        return preHead.next;
    }

    // 获取指定链表长度
    private static int getListLength(ListNode head) {
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}