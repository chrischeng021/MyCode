package com.leetcode.easy;

/*
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 * 链接：https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/
 */
public class EvenDigitsNumber {
    public int findNumbers(int[] nums) {
        int ret = 0;
        for(int num : nums)
        {
            if(((int)Math.log10(num) + 1) % 2 == 0)
            {
                ret++;
            }
        }
        return ret;
    }
}