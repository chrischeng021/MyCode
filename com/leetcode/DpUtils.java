package com.leetcode;

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
    // 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    // 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
    // 问总共有多少条不同的路径？
    // 链接：https://leetcode-cn.com/problems/unique-paths
    // 本题同样可以直接使用排列组合求解，思路如下：
    // 机器人一定会走m+n-2步，即从m+n-2中挑出m-1步向下走，即C((m+n-2), (m-1))
    public static int uniquePaths(int m, int n) {
        if(m == 1 || n == 1){
            return 1;
        }
        int[][]dp = new int[n][m];
        for(int i = 0; i < m; i++){
            dp[0][i] = 1;
        }
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

    // 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    // 要求时间复杂度为O(n)。
    // 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int ans = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    
    // 给定一个无序的整数数组，找到其中最长上升子序列的长度。
    // https://leetcode-cn.com/problems/longest-increasing-subsequence/
    public static int lengthOfLIS(int[] nums) {
        if(nums.length < 2) return nums.length;
        int max = 1;
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) dp[i] = 1;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}