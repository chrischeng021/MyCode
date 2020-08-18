package com.leetcode.easy;

/*
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
 * 链接：https://leetcode-cn.com/problems/palindrome-number/
 * PS：不用转字符串的方式求解
 */
public class PalindromNumber {
    public static boolean isPalindrome(int x) {
        if(x < 0 || (x > 0 && x%10 == 0))
        {
            return false;
        }
        if(x < 10)
        {
            return true;
        }
        int sub = 0;
        while(x > 0)
        {
            if(sub == x || sub == x/10)
            {
                return true;
            }
            sub = sub * 10 + x%10;
            x /= 10;
        }
        return false;
    }
}