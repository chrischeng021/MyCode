package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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
        final HashSet<Character> singleCharSet = new HashSet<Character>(){
            private static final long serialVersionUID = 1L;
            {
            add('b');
            add('a');
            add('n');
            }
    };

        final HashSet<Character> doubleCharSet = new HashSet<Character>(){
            private static final long serialVersionUID = 1L;
            {
            add('l');
            add('o');
            }
        };

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

    // 给出 字符串 text 和 字符串列表 words, 
    // 返回所有的索引对 [i, j] 使得在索引对范围内的子字符串 text[i]...text[j]（包括 i 和 j）属于字符串列表 words。
    // 链接：https://leetcode-cn.com/problems/index-pairs-of-a-string
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for(String word : words){
            int index = text.indexOf(word);
            while(-1 != index){
                list.add(new int[]{index, index + word.length() - 1});
                index = text.indexOf(word, ++index);
            }
        }
        int[][] res = new int[list.size()][];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] == t2[0] ? t1[1] - t2[1] : t1[0] - t2[0];
            }
        });
        return res;
    }

    // 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
    // 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
    // 返回可以通过分割得到的平衡字符串的最大数量。
    // 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
    public int balancedStringSplit(String s) {
        int ans = 0;
        int l = 0;
        int r = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'L'){
                l++;
            }
            else{
                r++;
            }
            if(l == r){
                ans++;
                l = 0;
                r = 0;
            }
        }
        return ans;
    }

    // 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
    // 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
    // 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
    // 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
    // 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
    // 链接：https://leetcode-cn.com/problems/remove-outermost-parentheses
    public static String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < S.length(); i++){
            char ch = S.charAt(i);
            stack.push(ch);
            if(ch == '('){
                l++;
            }
            else{
                r++;
            }
            if(l == r){
                stack.pop();
                StringBuilder temp = new StringBuilder();
                for(int j = 0; j < (2 * r - 2); j++){
                    temp.insert(0, stack.pop());
                }
                sb.append(temp);
                stack.clear();
                l = 0;
                r = 0;
            }
        }

        return sb.toString();
    }
}