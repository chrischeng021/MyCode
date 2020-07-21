package com.leetcode.easy;

import java.util.Stack;

// 实现一个MyQueue类，该类用两个栈来实现一个队列。
// 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci/
public class MyQueue {
    private Stack<Integer> in, out;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        in = new Stack<Integer>();
        out = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if(in.isEmpty()){
            this.swapFromSourceToTarget(in, out);
        }
        in.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(out.isEmpty()){
            this.swapFromSourceToTarget(out, in);
        }
        return out.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(out.isEmpty()){
            this.swapFromSourceToTarget(out, in);
        }
        return out.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void swapFromSourceToTarget(Stack<Integer> target, Stack<Integer> source){
        while(!source.isEmpty()){
            target.push(source.pop());
        }
    }
}