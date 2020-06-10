package com.leetcode.easy;

/*
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * 给定一个 整数 n， 如果他是完美数，返回 True，否则返回 False
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 另：本题可使用欧几里得-欧拉公式求解
 * 每个偶完全数都可以写成 2^{p-1} * (2^p-1)的形式，其中 p为素数。目前尚没有奇数形式的完全数被发现。
*/
public class PerfectNumber {
    public static boolean checkPerfectNumber(int num) {
        int sum = 1;
        if(num %2 != 0)
        {
            return false;
        }
        for(int i = 2; i <= Math.sqrt(num); i++)
        {
            if(num % i == 0)
            {
                sum += i + num/i;
            }
        }
        
        return sum == num;
    }
}