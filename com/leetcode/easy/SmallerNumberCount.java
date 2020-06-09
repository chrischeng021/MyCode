package com.leetcode.easy;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案.
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 */
public class SmallerNumberCount {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int [] ret = new int[nums.length];
        int [] bitmap = new int[500];
        int sum = 0;
        for(int num : nums)
        {
            bitmap[num] += 1;
        }
        for(int i = 0; i < bitmap.length; i++)
        {
            if(bitmap[i] > 0)
            {
                int curSum = sum;
                sum += bitmap[i];
                bitmap[i] = curSum;
            }
        }
        for(int i = 0; i < nums.length; i++)
        {
            ret[i] = bitmap[nums[i]];
        }
        return ret;
    }
}