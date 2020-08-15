package com.leetcode.middle;

// 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 链接：https://leetcode-cn.com/problems/minimum-path-sum/
public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        int[][] ans = new int[grid.length][grid[0].length];
        ans[0][0] = grid[0][0];
        for(int i = 1; i < grid[0].length; i++){
            ans[0][i] = grid[0][i] + ans[0][i - 1];
        }
        for(int i = 1; i < grid.length; i++){
            ans[i][0] = grid[i][0] + ans[i - 1][0];
        }
        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j < grid[i].length; j++){
                ans[i][j] = Math.min(ans[i - 1][j], ans[i][j - 1]) + grid[i][j];
            }
        }

        return ans[grid.length - 1][grid[0].length - 1];
    }
}