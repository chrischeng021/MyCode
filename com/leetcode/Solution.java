package com.leetcode;
import java.util.List;
public class Solution {
    public static void printArray(int[] arr){
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printList(List<Integer> list){
        for(int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static int numWays(int n) {
        if(n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <=n; i++){
            dp[i] = (dp[i - 1]+ dp[i - 2]) % 1000000007;
        }

        return dp[n] % 1000000007;
    }
    public static void main(String[] args) {
        System.out.println(numWays(97));
    }
}