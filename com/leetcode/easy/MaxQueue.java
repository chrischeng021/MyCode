package com.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

// 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
// 若队列为空，pop_front 和 max_value 需要返回 -1
// 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
// TODO：本题也可以不使用辅助数据结构，而直接使用自定义的Node（同时携带value和maxValue）实现双端链表来实现
public class MaxQueue {
    Queue<Integer> dataQueue;
    LinkedList<Integer> maxList;

    public MaxQueue() {
        dataQueue = new LinkedList<>();
        maxList = new LinkedList<>();
    }
    
    public int max_value() {
        if(maxList.isEmpty()){
            return -1;
        }
        else{
            return maxList.peek();
        }
    }
    
    public void push_back(int value) {
        dataQueue.add(value);
        if(maxList.isEmpty()){
            maxList.add(value);
            return;
        }
        while(!maxList.isEmpty() && value > maxList.getLast()){
            maxList.removeLast();
        }
        maxList.add(value);
    }
    
    public int pop_front() {
        if(dataQueue.isEmpty()){
            return -1;
        }
        if(!maxList.isEmpty() && maxList.getFirst().equals(dataQueue.peek())){
            maxList.poll();
        }
        return dataQueue.poll();
    }
}