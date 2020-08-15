package com.leetcode.easy;

/*
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-two-elements-in-an-array
 */
public class MaxProductOfTwoNumber {
    public static int maxProduct(int[] nums) {
        int max = -1;
        int secondMax = -1;

        for(int num : nums)
        {
            if(max < num)
            {
                secondMax = max;
                max = num;
                continue;
            }
            if(secondMax < num)
            {
                secondMax = num;
            }
        }

        return (max - 1) * (secondMax - 1);
    }
}