package com.leetcode;

import com.leetcode.easy.ArrayUtils;
import com.leetcode.middle.KthMinValueInMatrix;

public class Solution {
    public static int hammingWeight(long n) {
        int count = 0;
        while(n > 0){
            if(n % 2 == 1){
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(new KthMinValueInMatrix().kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
    }
}