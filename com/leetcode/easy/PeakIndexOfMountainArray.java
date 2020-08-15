package com.leetcode.easy;

/*
 * 我们把符合下列属性的数组 A 称作山脉：
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 */
public class PeakIndexOfMountainArray {
    public static int peakIndexInMountainArray(int[] A) {
        int peak = -1;
        for(int left = 0, right = 1; right < A.length; left++, right++)
        {
            if(A[left] < A[right])
            {
                peak = right;
            }
            else
            {
                return peak;
            }
        }
        return peak;
    }
}