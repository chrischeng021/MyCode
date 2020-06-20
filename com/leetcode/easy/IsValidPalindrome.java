package com.leetcode.easy;

// 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
// 说明：本题中，我们将空字符串定义为有效的回文串。
// 链接：https://leetcode-cn.com/problems/valid-palindrome/

public class IsValidPalindrome {
    public static boolean isPalindrome(String s) {
        if(s.length() < 2)
        {
            return true;
        }

        for(int i = 0, j = s.length() - 1; i < s.length() && i <= j; i++, j--)
        {
            char left = s.charAt(i);
            char right = s.charAt(j);

            while(!Character.isLetterOrDigit(left))
            {
                left = s.charAt(++i);
            }

            while(!Character.isLetterOrDigit(right))
            {
                right = s.charAt(--j);
            }

            if(Character.isDigit(left) && Character.isDigit(right) && left == right ||
                (Character.toLowerCase(left) == Character.toLowerCase(right)))
            {
                    continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}