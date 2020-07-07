package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class StringUtils {
    // 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
    // 链接：https://leetcode-cn.com/problems/reverse-only-letters/
    public String reverseOnlyLetters(String S) {
        
        return "";
    }

    // 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
    // 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
    // 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
    public static int maxNumberOfBalloons(String text) {
        final HashSet<Character> singleCharSet = new HashSet<Character>(){{
            add('b');
            add('a');
            add('n');
        }};

        final HashSet<Character> doubleCharSet = new HashSet<Character>(){{
            add('l');
            add('o');
        }};

        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            if(singleCharSet.contains(ch) || doubleCharSet.contains(ch)){
                if(map.containsKey(ch)){
                    map.put(ch, map.get(ch) + 1);
                }
                else{
                    map.put(ch, 1);
                }
            }
        }

        int minSingleChar = Integer.MAX_VALUE;
        int minDoubleChar = Integer.MAX_VALUE;

        for(Character ch : singleCharSet){
            if(!map.containsKey(ch)){
                return 0;
            }
            minSingleChar = Math.min(minSingleChar, map.get(ch));
        }

        for(Character ch : doubleCharSet){
            if(!map.containsKey(ch)){
                return 0;
            }
            minDoubleChar = Math.min(minDoubleChar, map.get(ch)/2);
        }

        return Math.min(minSingleChar, minDoubleChar);
    }

    // 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
    // 链接： https://leetcode-cn.com/problems/number-of-segments-in-a-string/
    public int countSegments(String s) {
        return 0;
    }

    public int romanToInt(String s) {
        int res = 0;
        return res;
    }
}