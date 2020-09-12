package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.leetcode.middle.SpiralMatrix;
import com.leetcode.model.TreeNode;

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

    public static void printIntList(List<Integer> list){
        for(int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void printStringList(List<String> list){
        for(String val : list){
            System.out.println(val + " ");
        }
        System.out.println();
    }

    public static void quickSortDev(int[] arr){
        partition(arr, 0, arr.length - 1);
    }

    public static void partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) { 
            while (a[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            while (a[--j] > v) {
                if (j == lo) {
                    break;
                }
            }
    
            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        System.out.println("i = " + i + ", j = " + j);
        System.out.println("Array between swap low and j.");
        printIntArray(a);
        swap(a, lo, j);
        System.out.println("Array after swap low and j.");
        printIntArray(a);
    
        // a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        // return j;
    }

    private static void swap(int[]arr, int i, int j){
        int iVal = arr[i];
        arr[i] = arr[j];
        arr[j] = iVal;
    }
    public static boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root) return false;
            while(!stack.isEmpty() && stack.peek() > postorder[i])
            	root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

    public static boolean canJump(int[] nums) {
        if(nums.length == 1) return true;
        int farest = 0;
        for(int i = 0; i < nums.length - 1; i++){
            farest = Math.max(farest, i + nums[i]);
            if(farest >= nums.length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(DpUtils.lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }
}