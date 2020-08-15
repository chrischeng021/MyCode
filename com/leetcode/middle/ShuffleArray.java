package com.leetcode.middle;

import java.util.Arrays;
import java.util.Random;
/* cSpell:disable */
// 打乱一个没有重复元素的数组。
// 链接：https://leetcode-cn.com/problems/shuffle-an-array/
public class ShuffleArray {
    public int[] originalArray;
    public int[] currentArray;

    public ShuffleArray(int[] nums) {
        originalArray = Arrays.copyOf(nums, nums.length);
        currentArray = Arrays.copyOf(nums, nums.length);
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originalArray;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        shuffle(currentArray);
        return currentArray;
    }

    private void shuffle(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int randomSwapIndex = new Random().nextInt(nums.length - i) + i;
            swap(nums, i, randomSwapIndex);
        }
    }

    private void swap(int nums[], int l, int r){
        if(l == r){
            return;
        }
        nums[l] += nums[r];
        nums[r] = nums[l] - nums[r];
        nums[l] -= nums[r];
    }
}