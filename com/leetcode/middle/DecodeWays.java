package com.leetcode.middle;
// 一条包含字母 A-Z 的消息通过以下方式进行了编码：
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。
// 链接：https://leetcode-cn.com/problems/decode-ways
public class DecodeWays {
    static int ans = 0;
    public static int numDecodings(String s) {
        if(s.length() == 0 || s.startsWith("0")) return 0;
        dfs(s, 0);
        return ans;
    }

    private static void dfs(String s, int index){
        if(index == s.length()){
            ans++;
            return;
        }
        // 当前字符为最后一个字符
        if(index == s.length() - 1){
            if(s.charAt(index) == '0'){
                return;
            }
            else{
                dfs(s, index + 1);
            }
        }
         // 当前字符不是最后一个字符
        else{
            if(s.charAt(index) == '0') return;
            // 当前字符后继为字符0
            if(s.charAt(index + 1) == '0'){
                if(s.charAt(index) > '2') return;
                dfs(s, index + 2);
            }
            else{
                dfs(s, index + 1);
                int val = Integer.parseInt(s.substring(index, index + 2));
                if(val >= 10 && val <= 26){
                    dfs(s, index + 2);
                }
                else return;
            }
        }
    }
}
