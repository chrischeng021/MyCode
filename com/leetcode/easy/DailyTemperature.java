package com.leetcode.easy;

import java.util.Stack;

/*
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
public class DailyTemperature {
    public static int[] dailyTemperatures(int[] T) {
        int []res = new int[T.length];
        Stack<Integer> indexStack = new Stack<Integer>();
        for(int i = 0; i < T.length; i++)
        {
            if(indexStack.empty() || T[indexStack.peek()] > T[i])
            {
                indexStack.push(i);
            }
            else
            {
                while(!indexStack.empty() && T[indexStack.peek()] < T[i])
                {
                    res[indexStack.peek()] = i - indexStack.pop();
                }
                indexStack.push(i);
            }
        }

        return res;
    }
}