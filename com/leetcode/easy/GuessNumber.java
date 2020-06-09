package com.leetcode.easy;

/**
 * 小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。
 * 他们一共进行三次这个游戏，请返回 小A 猜对了几次？
 * 链接：https://leetcode-cn.com/problems/guess-numbers/
 * 输入：guess = [1,2,3], answer = [1,2,3]
 * 输出：3
 * 解释：小A 每次都猜对了。
*/
public class GuessNumber {
    public static int game(final int[] guess, final int[] answer)
    {
        int match = 0;
        for(int i = 0; i < guess.length; i++)
        {
            if(guess[i] == answer[i])
            {
                match++;
            }
        }
        return match;
    }
}