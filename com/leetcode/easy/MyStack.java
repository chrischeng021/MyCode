package com.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

// 用队列实现栈
// 链接：https://leetcode-cn.com/problems/implement-stack-using-queues/
public class MyStack {
    private Queue<Integer> in, out;

    /** Initialize your data structure here. */
    public MyStack() {
        in = new LinkedList<Integer>();
        out = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if(in.isEmpty()){
            swapElementFromSourceToTarget(in, out);
        }
        in.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(out.isEmpty()){
            swapElementFromSourceToTarget(out, in);
        }
        return out.poll();
    }
    
    /** Get the top element. */
    public int top() {
        if(out.isEmpty()){
            swapElementFromSourceToTarget(out, in);
        }
        return out.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void swapElementFromSourceToTarget(Queue<Integer> target, Queue<Integer> source){
        while(!source.isEmpty()){
            target.add(source.poll());
        }
    }
}