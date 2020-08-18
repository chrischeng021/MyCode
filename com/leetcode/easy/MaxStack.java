package com.leetcode.easy;

import java.util.Stack;

// 设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。
// push(x) -- 将元素 x 压入栈中。
// pop() -- 移除栈顶元素并返回这个值。
// top() -- 返回栈顶元素。
// peekMax() -- 返回栈中最大元素。
// popMax() -- 返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个。
// 链接：https://leetcode-cn.com/problems/max-stack
public class MaxStack {
    /** initialize your data structure here. */
    private Stack<Integer> stack, maxStack;

    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(maxStack.isEmpty()){
            maxStack.push(x);
        }
        else{
            maxStack.push(Math.max(x, maxStack.peek()));
        }
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack<Integer>();
        while(top() != max){
            buffer.push(pop());
        }
        pop();
        while(!buffer.isEmpty()){
            push(buffer.pop());
        }
        return max;
    }
}