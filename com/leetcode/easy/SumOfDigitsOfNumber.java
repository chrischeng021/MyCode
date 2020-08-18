package com.leetcode.easy;

/*
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 链接： https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
 */
public class SumOfDigitsOfNumber {
    public static int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        while(n > 0)
        {
            int digit = n%10;
            product *= digit;
            sum += digit;
            n /= 10;
        }
        return product - sum;
    }
}