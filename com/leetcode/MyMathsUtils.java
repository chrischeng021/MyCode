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

    // 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    // 链接：https://leetcode-cn.com/problems/powx-n/
    // TODO：快速冥解法，需要Review
    public static double myPow(double x, int n) {
        long m = n;
        if(m < 0){
            m = -m;
            x = 1/x;
        }
        if(x == 0.0f) return 0.0d;
        double res = 1;
        while(m > 0){
            if( (m & 1) == 0x1){
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
    // 快速冥的递归解法
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}