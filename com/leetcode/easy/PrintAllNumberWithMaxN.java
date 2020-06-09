package com.leetcode.easy;

/*
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class PrintAllNumberWithMaxN {
    public static int[] printNumbers(int n) {
        int []ret = new int[(int)Math.pow(10, n) - 1];
        for(int i =0; i < ret.length; i++)
        {
            ret[i] = i + 1;
        }
        return ret;
    }
}