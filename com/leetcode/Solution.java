package com.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.leetcode.middle.*;
import com.leetcode.model.TreeNode;
import com.leetcode.TestHelper;
public class Solution {
    public static void printArray(Object[] arr){
        for(Object val : arr){
            System.out.print(val.toString() + " ");
        }
        System.out.println();
    }

    public static void printIntArray(int[] arr){
        for(int val : arr){
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void printList(List<Integer> list){
        for(int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {}
}