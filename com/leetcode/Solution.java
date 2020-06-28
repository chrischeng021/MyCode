package com.leetcode;

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
    }
}