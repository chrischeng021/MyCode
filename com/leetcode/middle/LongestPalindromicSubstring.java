package com.leetcode.middle;
// 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
// https://leetcode-cn.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {
    public static String longestPalindromeI(String s) {
        if(s.length() < 2) return s;

        String res = String.valueOf(s.charAt(0));
        int index = 0;
        while(index < s.length() - 1){
            // 奇对称
            int l = index - 1, r = index + 1;
            while(l >= 0 && r < s.length()){
                if(s.charAt(l) == s.charAt(r)){
                    if(res.length() < (r - l + 1)) res = s.substring(l, r + 1);
                    l--;
                    r++;
                }
                else break;
            }
            if(s.charAt(index + 1) == s.charAt(index)){
                l = index;
                r = index + 1;
                while(l >= 0 && r < s.length()){
                    if(s.charAt(l) == s.charAt(r)){
                        if(res.length() < (r - l + 1)) res = s.substring(l, r + 1);
                        l--;
                        r++;
                    }
                    else break;
                }
            }
            index++;
        }
        return res;
    }
    // Manacher算法
    // 核心思想是利用已经计算好的部分减少后续的计算量
    public static String longestPalindromeII(String s) {
        char[] arr = new char[2 + 2 * s.length() - 1];
        arr[0] = '#';
        arr[arr.length - 1] = '#';
        for(int i = 0, index = 1; i < s.length() && index < arr.length -1; i++){
            arr[index++] = s.charAt(i);
            arr[index++] = '#';
        }

        int j = -1, redge = -1, curMaxIndex = 0, curMaxLen = 0;
        int[] lens = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            int curLen = 0;
            if(i < redge){
                int skipLen = Math.min(lens[2 * j - i], j + lens[j] - i);
                curLen = expand(arr, i - skipLen, i + skipLen);
            }
            else{
                curLen = expand(arr, i, i);
                j = redge >= (i + curLen) ? j : i;
                redge = Math.max(redge, (i + curLen));
            }
            curMaxIndex = curMaxLen > curLen ? curMaxIndex : i;
            curMaxLen = Math.max(curMaxLen, curLen);
            lens[i] = curLen;
        }

        return String.valueOf(arr, curMaxIndex - curMaxLen , curMaxLen * 2 + 1).replace("#", "");
    }

    private static int expand(char[] arr, int l, int r){
        while(arr[l] == arr[r]){
            if(l > 0 && r < arr.length - 1){
                l--;
                r++;
            }
            else break;
        }
        return (r - l) / 2;
    }
}
