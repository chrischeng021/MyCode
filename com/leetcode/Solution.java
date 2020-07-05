package com.leetcode;

import java.util.List;

import com.leetcode.ArrayUtils;
import com.leetcode.middle.KthMinValueInMatrix;

public class Solution {
    public static void printArray(int[] arr){
        for(int num : arr){
            System.out.print(num + " ");
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
        System.out.println(ArrayUtils.countLargestGroup(12));
    }
}