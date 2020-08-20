package com.leetcode.middle;
// 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
// 链接：https://leetcode-cn.com/problems/maximal-square/
public class MaxSquare {
    // 首先尝试暴力解法
    // 可以通过，但是效率很低：
    // 执行用时：85 ms, 在所有 Java 提交中击败了5.38%的用户
    // 内存消耗：43.8 MB, 在所有 Java 提交中击败了5.01%的用户
    public static int maximalSquareI(char[][] matrix) {
        int maxEdgeLen = 0;
        int column = matrix[0].length;
        int row = matrix.length;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                int curEdgeLen = 0;
                if(matrix[i][j] == '0'){
                    continue;
                }
                curEdgeLen++;
                int k = Math.min(row - i, column - j);
                while(k > 1){
                    int cnt = 0;
                    boolean flag = true;
                    for(int x = i; x < (i + k); x++){
                        for(int y = j; y < (j + k); y++){
                            if(matrix[x][y] == '0'){
                                flag = false;
                                break;
                            }
                            else{
                                cnt++;
                            }
                        }
                        if(!flag){
                            break;
                        }
                    }
                    if(cnt == k * k){
                        curEdgeLen = k;
                        break;
                    }
                    k--;
                }
                maxEdgeLen = Math.max(maxEdgeLen, curEdgeLen);
            }
        }
        return maxEdgeLen * maxEdgeLen;
    }

    // 使用动态规划求解
    // dp[i][j] 表示以(i, j)为右下角的且只包含 1 的正方形的边长最大值
    public static int maximalSquareII(char[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxEdgeLen = 0;
        //初始化DP的两条边
        for(int i = 0; i < matrix[0].length; i++){
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            maxEdgeLen = Math.max(maxEdgeLen, dp[0][i]);
        }
        for(int i = 0; i < matrix.length; i++){
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxEdgeLen = Math.max(maxEdgeLen, dp[i][0]);
        }
        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                // 此时没有任何正方形可以以点(i, j)为右下角
                if(matrix[i][j] == '0'){
                    dp[i][j] = 0;
                }
                else{
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxEdgeLen = Math.max(maxEdgeLen, dp[i][j]);
                }
            }
        }
        return maxEdgeLen * maxEdgeLen;
    }

    // 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
    // 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/
    public static int countSquares(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int ans = 0;
        //初始化DP的两条边
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = matrix[i][j] == 1 ? 1 : 0;
                }
                // 此时没有任何正方形可以以点(i, j)为右下角
                else if(matrix[i][j] == 0){
                    dp[i][j] = 0;
                }
                else{
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }
}