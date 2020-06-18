package com.leetcode.easy;


/*
 * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi]。请你计算访问所有这些点需要的最小时间（以秒为单位）。
 * 你可以按照下面的规则在平面上移动：
 * 每一秒沿水平或者竖直方向移动一个单位长度，或者跨过对角线（可以看作在一秒内向水平和竖直方向各移动一个单位长度）
 * 必须按照数组中出现的顺序来访问这些点。
 * 链接：https://leetcode-cn.com/problems/minimum-time-visiting-all-points
 */
public class MinimumTimeVisitSequencePoints {
    public int minTimeToVisitAllPoints(int[][] points) {
        int minTime = 0;

        for(int i =0; i < points.length - 1; i++)
        {
            minTime += getMinTimeBetweenTwoPoints(points, i, i + 1);
        }

        return minTime;
    }
    
    private int getMinTimeBetweenTwoPoints(int[][]points, int x, int y)
    {
        return Math.max(Math.abs(points[x][0] - points[y][0]), Math.abs(points[x][1] - points[y][1]));
    }
}