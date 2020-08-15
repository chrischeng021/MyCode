package com.leetcode.easy;

import java.util.Stack;

// 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
// 链接：https://leetcode-cn.com/problems/min-stack-lcci
public class MinStack {
    Stack<Integer> stack, min;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if(min.isEmpty()){
            min.push(x);
        }
        else{
            min.push(Math.min(x, min.peek()));
        }
    }
    
    public void pop() {
        min.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return min.peek();
    }
}