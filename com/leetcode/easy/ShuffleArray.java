package com.leetcode.easy;

public class ShuffleArray {
    public static int[] shuffle(int[] nums, int n) {
        int gap = nums.length/2;
        for(int i = 1; i < gap; i += 2)
        {
            swap(nums, i, i+1);
            swap(nums, i, i+gap);
        }
        return nums;
    }

    private static void swap(int[] array, int left, int right)
    {
        if(right >= array.length)
            return;
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}