package com.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* cSpell:disable */
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
    
    public static void main(String[] args) {
        int[] arr = MySortUtils.generateRandomArray(25);
        printIntArray(arr);
        MySortUtils.quickSort(arr, "dsc");
        printIntArray(arr);
        System.out.println();
    }
}