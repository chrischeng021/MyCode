package com.leetcode.StringAndArrayCard;

public class Introduction {
    /*
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
     * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     */
    public static int pivotIndex(int[] nums) {
        int sum = 0;

        if(nums.length < 3)
        {
            return -1;
        }

        for(int num : nums)
        {
            sum += num;
        }

        int leftSum = nums[0];
        for(int i = 1; i < nums.length; i++)
        {
            if((sum - nums[i])%2 == 0 && leftSum == (sum - nums[i])/2)
            {
                return i;
            }
            if(leftSum >= sum/2)
            {
                return -1;
            }
            leftSum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args)
    {
        System.out.println(pivotIndex(new int[0]));
    }
}