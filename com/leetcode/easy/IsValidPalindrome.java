package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

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

    // 给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。
    // 链接：https://leetcode-cn.com/problems/palindrome-permutation/
    public static boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int oddCount = 0;

        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            if(map.containsKey(letter)){
                map.put(letter, map.get(letter) + 1);
            }
            else{
                map.put(letter, 1);
            }
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() % 2 == 1){
                if(oddCount == 0){
                    oddCount++;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    // 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
    // 链接： https://leetcode-cn.com/problems/valid-palindrome-ii/
    public boolean validPalindrome(String s) {
        return true;
    }
}