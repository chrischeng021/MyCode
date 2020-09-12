package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MyMathsUtils {
    // 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
    // 你需要让组成和的完全平方数的个数最少。
    // 链接：https://leetcode-cn.com/problems/perfect-squares/
    // TODO: 本题还有贪心、数学等解法，可以了解下。
    // 参考题解链接：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/
    public int numSquares(int n) {
        if(n < 2){
            return n;
        }
        int sqrtEdge = (int)Math.sqrt(n) + 1;
        int[] squareNumArr = new int[sqrtEdge];
        for(int i = 1; i <= sqrtEdge; i++){
            squareNumArr[i - 1] = i * i;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < sqrtEdge; j++){
                if(i < squareNumArr[j]){
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - squareNumArr[j]] + 1);
            }
        }
        return dp[n];
    }

    // 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    // 链接：https://leetcode-cn.com/problems/powx-n/
    public static double myPowI(double x, int n) {
        long m = n;
        if(m < 0){
            m = -m;
            x = 1/x;
        }
        if(x == 0.0f) return 0.0d;
        double res = 1;
        while(m > 0){
            if( (m & 1) == 0x1){
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
    // 快速冥的递归解法
    public static double myPowII(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = myPowII(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    // 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
    // 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
    // TODO: 本题有其他效率更高的解法，需要另行Review
    public static int countDigitOne(int n) {
        if(n <= 0) return 0;
        int factor = String.valueOf(n).length() - 1;
        int val = String.valueOf(n).charAt(0) - '0';
        if(factor == 0) return 1;

        int base = (int)Math.pow(10, factor);
        int last = n - base * val;
        return val == 1 ? countDigitOne(base - 1) + countDigitOne(last) + (n - base + 1) :
            val * countDigitOne(base - 1) + base + countDigitOne(last);
    }

    // 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
    // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
    public static int singleNumber(int[] nums) {
        int[] bitSum = new int[32];
        int res = 0;

        for(int num : nums){
            int index = 0;
            while(num > 0){
                bitSum[31 - index++] += (num & 0x1) != 0 ? 1 : 0;
                num >>= 1;
            }
        }

        for(int i = 0; i < 32; i++){
            res <<= 1;
            if(bitSum[i] % 3 != 0) res += bitSum[i] % 3;
        }

        return res;
    }

    // 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
    // 请写一个函数，求任意第n位对应的数字。
    // 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
    // 0 <= n < 2^31
    public static int findNthDigit(int n) {
        if(n < 10) return n;
        List<Long> list = generateCountArray(n);
        int len = list.size();
        int factor = len == 1 ? 10 : 90 * (int)Math.pow(10, len - 2);
        int leftDigit = (int)(n - list.get(len - 2));
        int val = leftDigit/len + factor/9;
        int index = leftDigit % len;
        return String.valueOf(val).charAt(index) - '0';
    }

    public static List<Long> generateCountArray(int n){
        long cur = 2;
        long base = 90;
        List<Long> list = new LinkedList<>();
        list.add(10L);
        while(list.get(list.size() - 1) <= n){
            list.add(cur * base + list.get(list.size() - 1));
            cur++;
            base *= 10;
        };
        return list;
    }
    // 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
    // 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
    // 链接：https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
    public static double[] twoSum(int n) {
        int[][] dp = new int[n + 1][n * 6 + 1];
        for(int i = 1; i <= 6; i++) dp[1][i] = 1;

        for(int i = 2; i <= n; i++){
            for(int j = i; j <= i * 6; j++){
                for(int k = 1; k <= 6; k++){
                    if(j <= k || dp[i - 1][j - k] == 0) continue;
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        int sum = 0;
        int index = 0;
        double[] res = new double[5 * n + 1];
        for(int i = 0; i < dp[n].length; i++) sum += dp[n][i];
        for(int i = 0; i < dp[n].length; i++) {
            if(dp[n][i] != 0) res[index++] = (dp[n][i] * 1.0) / sum;
        }

        return res;
    }

    // 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    // 注意：答案中不可以包含重复的三元组。
    // 链接：https://leetcode-cn.com/problems/3sum
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> cur = new ArrayList<>(3);
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            if(nums[i] > 0) break;
            for(int j = i + 1; j < nums.length - 1; j++){
                if(j > (i + 1) && nums[j] == nums[j - 1]) continue;
                if(nums[i] + nums[j] > 0) break;
                int target = 0 - nums[i] - nums[j];
                int index = Arrays.binarySearch(nums, j + 1, nums.length, target);
                if(index >= 0) {
                    cur.add(nums[i]);
                    cur.add(nums[j]);
                    cur.add(target);
                    ans.add(new ArrayList<Integer>(cur));
                    cur.clear();
                }
            }
        }
        return ans;
    }
    
    // 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
    // 如果小数部分为循环小数，则将循环的部分括在括号内。
    // 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
    public static String fractionToDecimal(int numerator, int denominator) {
        StringBuilder ans = new StringBuilder();
        if(denominator == 0) return "";
        if(numerator == 0) return "0";
        if(numerator == denominator) return "1";
        long x = numerator;
        long y = denominator;
        if(numerator < 0 && denominator < 0){
            x = -x;
            y = -y;
        }
        if(numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0){
            x = Math.abs(x);
            y = Math.abs(y);
            ans.append("-");
        }
        if(x < y) ans.append("0.");
        else{
            long val = x/y;
            ans.append(val);
            x -= val * y;
            if(x != 0) ans.append(".");
        }
        
        HashMap<Long, HashSet<Long>> restMap = new HashMap<>();
        x *= 10;
        int loopStart = -1;
        while(x > 0){
            if(x < denominator){
                x *= 10;
                ans.append("0");
                continue;
            }
            long val = x/y;
            long rest = x - val * y;
            x -= val * y;
            x *= 10;
            if(restMap.containsKey(val)){
                if(restMap.get(val).contains(rest)){
                    loopStart = (int)val;
                    break;
                }
                else{
                    ans.append(val);
                    restMap.get(val).add(rest);
                }
            }
            else{
                ans.append(val);
                restMap.put(val, new HashSet<Long>(){
					private static final long serialVersionUID = 1L;
					{
                        add(rest);
                    }
                });
            };
        }

        if(loopStart != -1){
            int index = ans.indexOf(".") + 1;
            while(ans.charAt(index) == '0') index++;
            ans.insert(ans.indexOf(String.valueOf(loopStart), index), "(");
            ans.append(")");
        }
        return ans.toString();
    }
}