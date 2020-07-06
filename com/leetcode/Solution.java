package com.leetcode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.leetcode.easy.MaxStack;
public class Solution {
    public static void printArray(Object[] arr){
        for(Object val : arr){
            System.out.print(val.toString() + " ");
        }
        System.out.println();
    }

    public static void printList(List<Integer> list){
        for(int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(1);
        ms.popMax();
        ms.peekMax();
    }
}