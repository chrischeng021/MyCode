package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

// 这里主要是使用深度优先搜索、回溯算法的题型
// 具体可参考如下链接：
// https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
public class MyDFS {
    // 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    // candidates 中的数字可以无限制重复被选取。
    // 说明：
    // 所有数字（包括 target）都是正整数。
    // 解集不能包含重复的组合。
    // 链接：https://leetcode-cn.com/problems/combination-sum
    public static List<List<Integer>> combinationSum2(int[] candidates, int target){
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates);
        dfsTraverseCombinationSum2(candidates, res, new LinkedList<>(), target, 0);
        return res;
    }

    private static void dfsTraverseCombinationSum2(int[] candidates, List<List<Integer>> res,  List<Integer> curRes, int target, int index){
        if(target == 0){
            res.add(new LinkedList<>(curRes));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(candidates[i] > target){
                break;
            }
            // candidates[i] == candidates[i - 1] 用于去除重复选项
            // i > index 用于保证不同index的相同数可以出现在同一组合中
            if(i > index && candidates[i] == candidates[i - 1]){
                continue;
            }
            curRes.add(candidates[i]);
            dfsTraverseCombinationSum2(candidates, res, curRes, target - candidates[i], i);
            curRes.remove(curRes.size() - 1);
        }
    }


    // 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    // 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    // 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
    // 链接：https://leetcode-cn.com/problems/restore-ip-addresses
    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        if(s.length() < 4 || s.length() > 12){
            return ans;
        }
        dfsTraverseIpAddresses(s, 0, new LinkedList<>(), ans);
        return ans;
    }

    private static void dfsTraverseIpAddresses(String str, int startIndex, List<String> singleIpList, List<String> ans){
        if(singleIpList.size() == 4 && startIndex == str.length()){
            StringBuilder sb = new StringBuilder();
            for(String ip : singleIpList){
                sb.append(ip).append(".");
            }
            ans.add(sb.substring(0, sb.length() - 1).toString());
        }
        for(int len = 1; len < 4 && startIndex + len <= str.length() && singleIpList.size() < 4; len++){
            String val = str.substring(startIndex, startIndex + len);
            if(val.length() > 1 && (val.startsWith("0") || Integer.valueOf(val) > 255)){
                continue;
            }
            singleIpList.add(val);
            dfsTraverseIpAddresses(str, startIndex + len, new LinkedList<>(singleIpList), ans);
            singleIpList.remove(singleIpList.size() - 1);
        }
    }

    // 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    // 链接：https://leetcode-cn.com/problems/permutations/
    public static List<List<Integer>> permute(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        List<List<Integer>> ans = new LinkedList<>();
        dfsTraverseNums(new HashSet<>(set), new LinkedList<>(), ans);
        return ans;
    }
    private static void dfsTraverseNums(HashSet<Integer> set, List<Integer> list, List<List<Integer>> ans){
        if(set.isEmpty()){
            ans.add(list);
        }
        for(int num : set){
            list.add(num);
            HashSet<Integer> setRemovedNum = new HashSet<>(set);
            setRemovedNum.remove(num);
            dfsTraverseNums(setRemovedNum, new LinkedList<>(list), ans);
            // 所有的深度搜索+回溯 一定要加上这一步Remove操作
            list.remove(list.size() - 1);
        }
    }

    // 给定一个可包含重复数字的序列，返回所有不重复的全排列。
    // 链接：https://leetcode-cn.com/problems/permutations-ii/
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        for(int num : nums) list.add(num);
        dfsTraverseNums(new LinkedList<>(list), new LinkedList<>(), new HashSet<>(), ans);
        return ans;
    }

    private static void dfsTraverseNums(List<Integer> nums, List<Integer> list, HashSet<String> set, List<List<Integer>> ans){
        if(nums.size() == 0){
            StringBuilder sb = new StringBuilder();
            for(int num : list) sb.append(num);
            if(!set.contains(sb.toString())){
                ans.add(list);
                set.add(sb.toString());
            }
        }
        int added = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++){
            if(added != Integer.MIN_VALUE && nums.get(i) == added){
                continue;
            }
            added = nums.get(i);
            list.add(added);
            LinkedList<Integer> listRemovedNum = new LinkedList<>(nums);
            listRemovedNum.remove(i);
            dfsTraverseNums(listRemovedNum, new LinkedList<>(list), set, ans);
            list.remove(list.size() - 1);
        }
    }

    // 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    // 说明：解集不能包含重复的子集。
    // 链接：https://leetcode-cn.com/problems/subsets/
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        if(nums.length > 0){
            HashSet<Integer> set = new HashSet<>();
            for(int num : nums) set.add(num);
            ans.add(new LinkedList<>(set));
            dfsSubSets(new HashSet<>(set), ans);
        }
        ans.add(new LinkedList<>());
        return ans;
    }

    private static void dfsSubSets(HashSet<Integer> set, List<List<Integer>> ans){
        for(int num : set){
            HashSet<Integer> subSet = new HashSet<>(set);
            subSet.remove(num);
            if(subSet.isEmpty()) return;
            ans.add(new LinkedList<>(subSet));
            dfsSubSets(subSet, ans);
        }
    }

    // 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    // 链接：https://leetcode-cn.com/problems/combinations/
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new LinkedList<>();

        if(k > n){
            return ans;
        }
        dfsCombineTraverse(n, 1, new LinkedList<>(), ans, k);
        return ans;
    }

    private static void dfsCombineTraverse(int num, int index, List<Integer> list, List<List<Integer>> ans, int k){
        if(list.size() == k){
            ans.add(list);
            return;
        }
        for(int i = index; i <= num; i++){
            list.add(i);
            dfsCombineTraverse(num, i + 1, new LinkedList<>(list), ans, k);
            list.remove(list.size() - 1);
        }
    }

    // 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    // 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    // 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    // 注：本题用Trie也可解答，这里的是尝试用回溯算法解决的思路。
    public static List<String> letterCombinations(String digits){
        List<String> ans = new LinkedList<>();
        if(digits == null || digits.length() == 0){
            return ans;
        }
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfsTraverseLetterCombinations(digits, map, 0, new StringBuilder(), ans);
        return ans;
    }

    private static void dfsTraverseLetterCombinations(String digits, String[] arr, int index, StringBuilder sb, List<String> ans){
        if(index == digits.length()){
            ans.add(sb.toString());
            return;
        }

        String mapString = arr[digits.charAt(index) - '0' - 2];
        for(int i = 0; i < mapString.length(); i++){
            sb.append(mapString.charAt(i));
            dfsTraverseLetterCombinations(digits, arr, index + 1, new StringBuilder(sb), ans);
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        }
    }

    // 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
    // 链接：https://leetcode-cn.com/problems/letter-case-permutation/
    // TODO: 以下解法可以AC，但是效率较差，需要优化
    public static List<String> letterCasePermutation(String S) {
        List<String> ans = new LinkedList<>();
        if(S.length() > 0){
            HashMap<Integer, Character> digitIndexMap = new HashMap<>();
            StringBuilder pureString = new StringBuilder();

            for(int i = 0; i < S.length(); i++){
                if(Character.isDigit(S.charAt(i))){
                    digitIndexMap.put(i, S.charAt(i));
                }
                else{
                    // 将问题转换为纯字符串大小写的全排列问题
                    pureString.append(S.charAt(i));
                }
            }
            if(pureString.length() > 0){
                dfsTraverseLetterCase(pureString.toString(), 0, "", digitIndexMap, ans);
            }
            else{
                ans.add(S);
            }
        }
        return ans;
    }

    private static void dfsTraverseLetterCase(String source, int index, String str, HashMap<Integer, Character> digitIndexMap, List<String> ans){
        if(str.length() == source.length()){
            char[] charArr = new char[source.length() + digitIndexMap.size()];
            for(Map.Entry<Integer, Character> entry : digitIndexMap.entrySet()){
                charArr[entry.getKey()] = entry.getValue();
            }
            for(int i = 0, j = 0; j < str.length(); i++){
                if(!Character.isDigit(charArr[i])){
                    charArr[i] = str.charAt(j++);
                }
            }
            ans.add(String.valueOf(charArr));
        }
        for(int i = index; i < source.length(); i++){
            for(int j = 0; j < 2; j++){
                str += j == 0 ? Character.toLowerCase(source.charAt(i)) : Character.toUpperCase(source.charAt(i));
                dfsTraverseLetterCase(source, i + 1, str, digitIndexMap, ans);
                str = str.substring(0, str.length() - 1);
            }
        }
    }

    // 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    // 链接：https://leetcode-cn.com/problems/generate-parentheses/
    // 本题参考了题解 需要重新Review
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new LinkedList<>();
        if(n == 0) return ans;
        generateAllParenthesisCombinations(0, 0, "", ans, n);
        return ans;
    }

    private static void generateAllParenthesisCombinations(int l, int r,  String cur, List<String> ans, int n){
        if(l < r || l > n || r > n){
            return;
        }
        if(l == r && l == n){
            ans.add(cur.toString());
            return;
        }
        generateAllParenthesisCombinations(l + 1, r, cur + "(", ans, n);
        generateAllParenthesisCombinations(l, r + 1, cur + ")", ans, n);
    }
}