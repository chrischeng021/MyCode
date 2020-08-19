package com.leetcode;

import java.util.Arrays;

public class MyMathsUtils {
    // 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
    // 你需要让组成和的完全平方数的个数最少。
    // 链接：https://leetcode-cn.com/problems/perfect-squares/
    // TODO: 本题还有贪心、数学等解法，可以了解下。
    // 参考题解链接：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/
    public int numSquares(int n) {
        if(n < 2){
            return n;
        }
        int sqrtEdge = (int)Math.sqrt(n) + 1;
        int[] squareNumArr = new int[sqrtEdge];
        for(int i = 1; i <= sqrtEdge; i++){
            squareNumArr[i - 1] = i * i;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < sqrtEdge; j++){
                if(i < squareNumArr[j]){
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - squareNumArr[j]] + 1);
            }
        }
        return dp[n];
    }
}