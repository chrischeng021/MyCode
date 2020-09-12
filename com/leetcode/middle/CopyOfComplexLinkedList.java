package com.leetcode.middle;

public class CopyOfComplexLinkedList {
    static class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        if(head == null) return head;
        Node newHead = copyRandomListFirstStep(head);
        setRandomPointer(newHead);
        print(newHead);
        return seperateLinkedList(newHead);
    }

    //首先原地复制链表
    private static Node copyRandomListFirstStep(Node head){
        Node cursor = head;
        while(cursor != null){
            Node newNode = new Node(cursor.val);
            newNode.next = cursor.next;
            newNode.random = null;
            cursor.next = newNode;
            cursor = newNode.next;
        }
        return head;
    }

    // 设置第一步复制结点的Random指针
    private static void setRandomPointer(Node head){
        Node cursor = head;
        while(cursor != null){
            if(cursor.random != null) cursor.next.random = cursor.random.next;
            cursor = cursor.next.next;
        }
    }

    // 拆解链表
    private static Node seperateLinkedList(Node head){
        Node cursor = head;
        Node secondCursor = head.next;
        Node ret = secondCursor;
        while(secondCursor.next != null){
            cursor.next = secondCursor.next;
            cursor = cursor.next;
            secondCursor.next = cursor.next;
            secondCursor = secondCursor.next;
        }
        cursor.next = null;
        
        print(head);
        print(ret);

        return ret;
    }

    private static void print(Node head){
        Node node = head;
        while(node != null){
            System.out.print(node.toString().substring(node.toString().indexOf("@")));
            System.out.print("  " + node.val + ", ");
            String random = node.random == null ? "null" : String.valueOf(node.random.val);
            System.out.print(random + "  ");
            node = node.next;
        }
        System.out.println();
    }

    public static void test(){
        Node head = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);

        head.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;
        node1.next = null;

        head.random = null;
        node13.random = head;
        node11.random = node1;
        node10.random = node11;
        node1.random = head;

        copyRandomList(head);
    }
}
