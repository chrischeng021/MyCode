package com.leetcode.easy;

import java.util.Stack;

public class QueueByTwoStacks {
    private Stack<Integer> in, out;

    public QueueByTwoStacks() {
        this.in = new Stack<Integer>();
        this.out = new Stack<Integer>();
    }
    
    public void appendTail(int value) {
        in.push(value);
    }
    
    public int deleteHead() {
        if(in.isEmpty()){
            return -1;
        }
        while(!in.isEmpty()){
            out.push(in.pop());
        }

        int ret = out.pop();

        while(!out.isEmpty()){
            in.push(out.pop());
        }

        return ret;
    }
}