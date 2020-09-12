package com.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 用队列实现栈
// 链接：https://leetcode-cn.com/problems/implement-stack-using-queues/
public class MyStack {
    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        int size = queue.size();
        while(size > 1){
            queue.add(queue.remove());
            size--;
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    // 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
    // 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
    // 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
    // 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer>stack = new Stack<>();
        int popIndex = 0;
        int pushIndex = 0;

        for(;pushIndex < pushed.length && popIndex < popped.length;){
            int toPush = pushed[pushIndex++];
            stack.push(toPush);
            while(popIndex < popped.length && !stack.isEmpty() && popped[popIndex] == stack.peek()){
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();
    }
}