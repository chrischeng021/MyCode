package com.leetcode;


import com.leetcode.easy.AddBinary;
import com.leetcode.easy.ArrayUtils;

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
        System.out.println("=============");
        System.out.println(hammingWeight(11111111111111111111111111111101));
        System.out.println("=============");
    }
}