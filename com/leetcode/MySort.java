package com.leetcode;

import java.util.Random;

public class MySort {
    public static void quickSort(int [] nums){
        partition(nums, 0, nums.length - 1);
    }
    private static void partition(int [] nums, int begin, int end){
        int i = begin, j = end;
        if(begin >= end) return;
        int base = nums[i];
        while(i < j){
            while(nums[j] > base && j > i) j--;
            if(i < j) nums[i++] = nums[j];
            
            while(nums[i] < base && i < j) i++;
            if(i < j) nums[j--] = nums[i];

            nums[i] = base;
            partition(nums, begin, i - 1);
            partition(nums, i + 1, end);
        }
    }

    public static void testQuickSort(){
        int[]nums = new int[10];
        Random rand = new Random();
        for(int i = 0; i < nums.length; i++) nums[i] = rand.nextInt(20);

        for(int i = 0; i < nums.length; i++) System.out.print(nums[i] + " ");
        System.out.println();
        quickSort(nums);
        for(int i = 0; i < nums.length; i++) System.out.print(nums[i] + " ");
        System.out.println();
    }
}
