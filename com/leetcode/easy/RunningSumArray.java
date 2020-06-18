package com.leetcode.easy;


/*
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 * 请返回 nums 的动态和。
 * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
 */
public class RunningSumArray {
    public int[] runningSum(int[] nums) {
        if(nums.length <= 1)
        {
            return nums;
        }

        int[] res = new int[nums.length];
        res[0] = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            res[i] = res[i - 1] + nums[i];
        }
        return res;
    }
}