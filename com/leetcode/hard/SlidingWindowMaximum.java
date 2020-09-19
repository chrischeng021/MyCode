package com.leetcode.hard;

import java.util.LinkedList;

public class SlidingWindowMaximum {
    // 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    // 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    // 返回滑动窗口中的最大值。
    // 链接：https://leetcode-cn.com/problems/sliding-window-maximum
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // Sample Input: [1,3,-1,-3,5,3,6,7] k = 3
        // Sample OutOut: [3,3,5,5,6,7] 
        LinkedList<Integer> list = new LinkedList<>();
        int[]ans = new int[nums.length - k + 1];
        int index = 0;
        for(int j = 0; j < nums.length; j++){
            int val = nums[j];
            // Remove expired element.
            while(!list.isEmpty() && list.getFirst() <= (j - k)){
                list.removeFirst();
            }
            while(!list.isEmpty() && nums[list.getLast()] <= val){
                list.removeLast();
            }
            list.add(j);
            if(j < (k - 1)) continue;
            else{
                ans[index++] = nums[list.getFirst()];
            }
        }
        return ans;
    }
}
