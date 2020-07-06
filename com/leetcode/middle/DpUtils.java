package com.leetcode.middle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DpUtils {
    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * 链接：https://leetcode-cn.com/problems/word-break/ 参考:
     * https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<String>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    // 青蛙跳台阶问题
    public static int numWays(int n) {
        if(n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <=n; i++){
            dp[i] = (dp[i - 1]+ dp[i - 2]) % 1000000007;
        }

        return dp[n] % 1000000007;
    }
}