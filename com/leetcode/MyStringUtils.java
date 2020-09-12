package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.leetcode.model.TreeNode;

/* cSpell:disable */
public class MyStringUtils {
    public static int index = 0;
    static int ans = 0;
    // 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
    // 链接：https://leetcode-cn.com/problems/reverse-only-letters/
    public String reverseOnlyLetters(final String S) {
        return "";
    }

    // 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
    // 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
    // 链接：https://leetcode-cn.com/problems/maximum-number-of-balloons
    public static int maxNumberOfBalloons(final String text) {
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

        final HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < text.length(); i++){
            final char ch = text.charAt(i);
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

        for(final Character ch : singleCharSet){
            if(!map.containsKey(ch)){
                return 0;
            }
            minSingleChar = Math.min(minSingleChar, map.get(ch));
        }

        for(final Character ch : doubleCharSet){
            if(!map.containsKey(ch)){
                return 0;
            }
            minDoubleChar = Math.min(minDoubleChar, map.get(ch)/2);
        }

        return Math.min(minSingleChar, minDoubleChar);
    }

    // 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
    // 链接： https://leetcode-cn.com/problems/number-of-segments-in-a-string/
    public int countSegments(final String s) {
        return 0;
    }

    // 罗马数字转整数
    // 链接：https://leetcode-cn.com/problems/roman-to-integer/
    public static int romanToInt(String str){
        Map<String, Integer> map = new HashMap<>(){
            private static final long serialVersionUID = 228369631327457321L;
            {
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }};

        if(str == null || str.length() == 0){
            return 0;
        }

        if(str.length() == 1){
            return map.get(str);
        }

        String pre = String.valueOf(str.charAt(str.length() - 1));
        int ans = map.get(pre);
        
        for(int i = str.length() - 2; i >= 0; i--){
            switch(str.charAt(i)){
                case 'I':
                    if(pre.equals("V") || pre.equals("X")){
                        ans -= 1;
                    }
                    else{
                        ans += 1;
                    }
                    break;
                case 'X':
                    if(pre.equals("L") || pre.equals("C")){
                        ans -= 10;
                    }
                    else{
                        ans += 10;
                    }
                    break;
                case 'C':
                    if(pre.equals("D") || pre.equals("M")){
                        ans -= 100;
                    }
                    else{
                        ans += 100;
                    }
                    break;
                default:
                    ans += map.get(String.valueOf(str.charAt(i)));
                    break;
            }
            pre = String.valueOf(str.charAt(i));
        }
        return ans;
    }

    // 给出 字符串 text 和 字符串列表 words, 
    // 返回所有的索引对 [i, j] 使得在索引对范围内的子字符串 text[i]...text[j]（包括 i 和 j）属于字符串列表 words。
    // 链接：https://leetcode-cn.com/problems/index-pairs-of-a-string
    public int[][] indexPairs(final String text, final String[] words) {
        final List<int[]> list = new ArrayList<>();
        for(final String word : words){
            int index = text.indexOf(word);
            while(-1 != index){
                list.add(new int[]{index, index + word.length() - 1});
                index = text.indexOf(word, ++index);
            }
        }
        final int[][] res = new int[list.size()][];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        Arrays.sort(res, new Comparator<int[]>() {
            @Override
            public int compare(final int[] t1, final int[] t2) {
                return t1[0] == t2[0] ? t1[1] - t2[1] : t1[0] - t2[0];
            }
        });
        return res;
    }

    // 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
    // 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
    // 返回可以通过分割得到的平衡字符串的最大数量。
    // 链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
    public int balancedStringSplit(final String s) {
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
    public static String removeOuterParentheses(final String S) {
        final StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = 0;
        final Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < S.length(); i++){
            final char ch = S.charAt(i);
            stack.push(ch);
            if(ch == '('){
                l++;
            }
            else{
                r++;
            }
            if(l == r){
                stack.pop();
                final StringBuilder temp = new StringBuilder();
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

    // 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    // 链接：https://leetcode-cn.com/problems/group-anagrams/
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new LinkedList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String dicSequenceStr = String.valueOf(arr);
            if(map.containsKey(dicSequenceStr)){
                map.get(dicSequenceStr).add(str);
            }
            else{
                map.put(dicSequenceStr, new LinkedList<String>(){
                    private static final long serialVersionUID = 1L;
                    {
                        add(str);
                    }
                });
            }
        }

        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            ans.add(entry.getValue());
        }

        return ans;
    }
        
    // 给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
    // 如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
    // 返回所有不常用单词的列表。
    // 您可以按任何顺序返回列表。
    // 链接：https://leetcode-cn.com/problems/uncommon-words-from-two-sentences
    public String[] uncommonFromSentences(final String A, final String B) {
        final Map<String, Integer> map = new HashMap<>();
        String[] arr = A.split(" ");
        final List<String> list = new LinkedList<>();
        for(final String str : arr){
            if(map.containsKey(str)){
                map.put(str, map.get(str) + 1);
            }
            else{
                map.put(str, 1);
            }
        }
        arr = B.split(" ");
        for(final String str : arr){
            if(map.containsKey(str)){
                map.put(str, map.get(str) + 1);
            }
            else{
                map.put(str, 1);
            }
        }
        for(final Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                list.add(entry.getKey());
            }
        }
        return list.toArray(new String[list.size()]);
    }

    // 给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
    // 字符（'a' - 'i'）分别用（'1' - '9'）表示。
    // 字符（'j' - 'z'）分别用（'10#' - '26#'）表示。 
    // 返回映射之后形成的新字符串。
    // 题目数据保证映射始终唯一。
    // 链接：https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping
    public static String freqAlphabets(final String s) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if((i + 2) < s.length() && s.charAt(i + 2) == '#'){
                sb.append(String.valueOf((char)('a' + Integer.valueOf(String.valueOf(s.substring(i, i + 2))) - 1)));
                i += 2;
            }
            else{
                sb.append(String.valueOf((char)('a' + Integer.valueOf(String.valueOf(s.charAt(i))) - 1)));
            }
        }
        return sb.toString();
    }

    // 编写一个函数来查找字符串数组中的最长公共前缀。
    // 如果不存在公共前缀，返回空字符串 ""。
    // 链接：https://leetcode-cn.com/problems/longest-common-prefix/
    // TODO:可优化
    public String longestCommonPrefix(final String[] strs) {
        String ans = "";
        if(strs.length == 0){
            return ans;
        }
        ans = strs[0];
        for(int i = 1; i < strs.length; i++){
            ans = this.getCommonPrefix(ans, strs[i]);
            if(ans.length() == 0){
                break;
            }
        }
        return ans;
    }
    private String getCommonPrefix(final String s1, final String s2){
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Math.min(s1.length(), s2.length()); i++){
            if(s1.charAt(i) == s2.charAt(i)){
                sb.append(s1.charAt(i));
            }
            else{
                break;
            }
        }
        return sb.toString();
    }

    // 最短完整词
    // 链接：https://leetcode-cn.com/problems/shortest-completing-word/
    public static String shortestCompletingWord(String licensePlate, final String[] words) {
        final int[] arr = new int[26];
        licensePlate = licensePlate.toLowerCase();
        for(int i = 0; i < licensePlate.length(); i++){
            if(Character.isAlphabetic(licensePlate.charAt(i))){
                arr[licensePlate.charAt(i) - 'a']++;
            }
        }
        int[] subArr;
        for(final String word : words){
            subArr = new int[26];
            boolean isValidWord = true;
            for(int i = 0; i < word.length(); i++){
                subArr[word.charAt(i) - 'a']++;
            }
            for(int j = 0; j < 26; j++){
                if(arr[j] > 0 && (arr[j] > subArr[j])){
                    isValidWord = false;
                    break;
                }
            }
            if(isValidWord){
                return word;
            }
        }
        return null;
    }

    // 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    // 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
    // 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    // 链接：https://leetcode-cn.com/problems/is-subsequence
    public boolean isSubsequence(final String s, final String t) {
        int curIndex = 0;
        for(int i = 0; i < s.length(); i++){
            char letter = s.charAt(i);
            curIndex = t.indexOf(letter, curIndex);
            if(curIndex < 0){
                return false;
            }
            curIndex++;
        }
        return true;
    }

    // 重新排列字符串
    // 链接：https://leetcode-cn.com/problems/shuffle-string/
    public String restoreString(String s, int[] indices) {
        char[] arr = new char[s.length()];
        for(int i = 0; i < indices.length; i++){
            arr[indices[i]] = s.charAt(i);
        }
        return String.valueOf(arr);
    }

    // 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
    // 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
    // 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
    // 思路：先整体翻转，再单独翻转每个单词
    public static String reverseWords(String s) {
        String reversedStr = new StringBuffer(s).reverse().toString();
        StringBuilder ans = new StringBuilder();
        int l = 0;
        int r = 0;
        while(l < reversedStr.length() && r < reversedStr.length()){
            while(l < reversedStr.length() && reversedStr.charAt(l) == ' '){
                l++;
            }
            r = l;
            while(r < reversedStr.length() && reversedStr.charAt(r) != ' '){
                r++;
            }
            if(l < reversedStr.length()){
                ans.append(new StringBuffer(reversedStr.substring(l, r)).reverse()).append(" ");
            }
            l = r;
        }
        return ans.substring(0, ans.length() - 1).toString();
    }

    // 给定一个经过编码的字符串，返回它解码后的字符串。
    // 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    // 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    // 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
    // 链接：https://leetcode-cn.com/problems/decode-string
    // TODO: 本题参考了题解，需要复习。同时本题可以通过栈来实现，考虑第二种写法。
    public static String decodeString(String s){
        int repeat = 0;
        String res = "";
        String generateRepeatString = "";
        while(index < s.length()){
            char ch = s.charAt(index);

            if(Character.isDigit(ch)){
                repeat += repeat * 10 + (ch - '0');
            }
            else if(Character.isAlphabetic(ch)){
                res += ch;
            }
            else if(ch == '['){
                index++;
                generateRepeatString = decodeString(s);
                while(repeat-- > 0){
                    res += generateRepeatString;
                }
                repeat = 0;
            }
            else{
                break;
            }
            index++;
        }
        return res;
    }
    public static String decodeStringUsingStack(String s){
        return null;
    }

    // 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    // 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
    // 链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
    public boolean isNumber(String s) {
        return false;
    }

    // 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    // 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
    // 从Index=0的位置开始遍历字符串 并建立二叉树
    // 最终二叉树的叶子节点数则是最终的答案
    public static int translateNum(int num) {
        if(num > 25 && num < 100) return 1;
        generateBinaryTree(String.valueOf(num), 0, new TreeNode(-1));
        return ans;
    }

    private static void generateBinaryTree(String str, int index, TreeNode root){
        if(index >= str.length()){
            ans++;
            return;
        }
        int l = str.charAt(index) - '0';
        root.left = new TreeNode(l);
        generateBinaryTree(str, index + 1, root.left);
        int r = -1;
        if(index < str.length() - 1){
            r = Integer.parseInt(str.substring(index, index + 2));
        }
        if(r != -1 && r < 26 && r > 9){
            root.right = new TreeNode(r);
            generateBinaryTree(str, index + 2, root.left);
        }
        else{
            root.right = null;
        }
    }
    // 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
    // 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
    public static int strToInt(String str) {
        int begin = -1;
        int flag = 1;
        if(str.length() == 0) return 0;
        int index = 0;
        while(index < str.length() && str.charAt(index) == ' ') {index++;}
        if(index >= str.length()) return 0;
        if(str.charAt(index) == '+'){
            index++;
        }
        else if(str.charAt(index) == '-') {
            index++;
            flag = -1;
        }
        else if(!Character.isDigit(str.charAt(index))){
            return 0;
        }
        begin = index;
        while(index < str.length() && Character.isDigit(str.charAt(index))) { index++; }
        if(begin < 0 || begin >= str.length()) return 0;
        String validStr = str.substring(begin, index);
        long val = 0;
        int factor = validStr.length() - 1;
        for(int i = 0; i < validStr.length(); i++){
            val += Math.pow(10, factor--) * (validStr.charAt(i) - '0');
        }

        val *= flag;

        return val <= Integer.MAX_VALUE && val >= Integer.MIN_VALUE ? (int)val : val < Integer.MIN_VALUE ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }

    // 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
    // https://leetcode-cn.com/problems/largest-number/
    public static String largestNumber(int[] nums) {
        Integer[] data = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) data[i] = nums[i];
        Arrays.sort(data, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                String x = String.valueOf(a).intern() + String.valueOf(b).intern();
                String y = String.valueOf(b).intern() + String.valueOf(a).intern();

                return y.compareTo(x);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            sb.append(String.valueOf(data[i]));
        }
        return sb.toString().startsWith("0") ? "0" : sb.toString();
    }

    // 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
    // https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
    public static int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        while(r < s.length()){
            char ch = s.charAt(r++);
            if(!set.contains(ch)){
                set.add(ch);
                max = Math.max(max, set.size());
                continue;
            }
            while(set.contains(ch) && l < s.length()){
                set.remove(s.charAt(l++));
            }
            set.add(ch);
        }
        return max;
    }
    // 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
    // https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
    static int max = 0;
    public static int longestSubstring(String s, int k) {
        if(s.length() < k) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)) map.put(ch, map.get(ch) + 1);
            else map.put(ch, 1);
        }
        HashSet<Character> chars = new HashSet<>();
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() < k){
                chars.add(entry.getKey());
            }
        }
        if(chars.size() == 0) return s.length();
        List<String> strs = new LinkedList<>();
        for(int i = 0; i < s.length(); i++){
            while(i < s.length() && chars.contains(s.charAt(i))) i++;
            if(i == s.length()) break;
            int j = i + 1;
            while(j < s.length() && !chars.contains(s.charAt(j))) j++;
            strs.add(s.substring(i, j));
        }
        for(String str : strs){
            max = Math.max(longestSubstring(str, k), max);
        }
        return max;
    }
}