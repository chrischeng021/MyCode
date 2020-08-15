package com.leetcode.easy;

/*
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 * 链接：https://leetcode-cn.com/problems/shuffle-the-array
 */
public class ShuffleArray {
    public static int[] shuffle(int[] nums, int n) {
        int []ret = new int[nums.length];
        for(int i = 0; i < n; i++)
        {
            ret[2 * i] = nums[i];
        }
        for(int i = n; i < 2*n; i++)
        {
            ret[2 * (i - n) + 1] = nums[i];
        }
        return ret;
    }
}