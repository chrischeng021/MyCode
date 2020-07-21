package com.leetcode.middle;

public class ContainerWithMostWater {
    // 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
    // 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
    // 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    // 说明：你不能倾斜容器，且 n 的值至少为 2。
    // 链接：https://leetcode-cn.com/problems/container-with-most-water
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int width = height.length - 1;
        int ans = width * Math.min(height[l], height[r]);
        while(l < r){
            if(height[l] < height[r]){
                l++;
                width -= 1;
            }
            else if(height[l] > height[r]){
                r--;
                width -= 1;
            }
            else{
                l++;
                r--;
                width -= 2;
            }
            if(l >= r || l >= height.length - 1 || r <= 0){
                break;
            }
            ans = Math.max(ans, width * Math.min(height[l], height[r]));
        }
        return ans;
    }
}