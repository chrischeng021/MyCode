package com.leetcode.middle;

// 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
// 返回被除数 dividend 除以除数 divisor 得到的商。
// 链接：https://leetcode-cn.com/problems/divide-two-integers
public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        long ans = 0L;
        long dividendL = dividend;
        long divisorL = divisor;
        if(dividendL == 0 || Math.abs(dividendL) < Math.abs(divisorL)){
            return 0;
        }

        if(dividendL == divisorL){
            return 1;
        }

        if(dividend == Integer.MIN_VALUE){
            switch(divisor){
                case 1:
                    return Integer.MIN_VALUE;
                case -1:
                    return Integer.MAX_VALUE;
            }
        }

        if(dividend == Integer.MAX_VALUE){
            switch(divisor){
                case 1:
                    return Integer.MAX_VALUE;
                case -1:
                    return -Integer.MAX_VALUE;
            }
        }

        boolean l = dividendL > 0 ? true : false;
        boolean r = divisorL > 0 ? true : false;
        boolean f = !(l ^ r); // true = positive, false = negative.

        if(Math.abs(dividendL) == Math.abs(divisorL)){
            return f ? 1 : -1;
        }

        dividendL = Math.abs(dividendL);
        divisorL = Math.abs(divisorL);

        while(dividendL >= divisorL){
            long divisorPlus = divisorL;
            long factor = 0;
            while(divisorPlus <= dividendL){
                divisorPlus = divisorPlus << 1;
                factor++;
            }
            divisorPlus >>= 1;
            factor--;
            dividendL -= divisorPlus;
            ans += (long)(1 << factor);
        }

        return f ? (int)ans : (int)-ans;
    }
}