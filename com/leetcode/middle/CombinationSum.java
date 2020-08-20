package com.leetcode.middle;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
// 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
// candidates 中的数字可以无限制重复被选取。
// 说明：
// 1. 所有数字（包括 target）都是正整数。
// 2. 解集不能包含重复的组合。 
// 链接：https://leetcode-cn.com/problems/combination-sum
public class CombinationSum {
    // 暴力法 明显超时
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, List<List<Integer>>> dp = new HashMap<>();
        for(int num : candidates){
            set.add(num);
            if(num <= target){
                List<List<Integer>> temp = new LinkedList<>();
                temp.add(new LinkedList<>());
                temp.get(0).add(num);
                dp.put(num, temp);
            }
        }
        
        if(set.contains(1)){
            dp.put(0, new LinkedList<>(){
				private static final long serialVersionUID = 1L;{
                    add(new LinkedList<>(){
                        private static final long serialVersionUID = 2L;{
                        add(1);}});}});
        }
        else{
            dp.put(1, new LinkedList<>());
        }

        for(int i = 2; i <= target; i++){
            List<List<Integer>> initList = dp.get(i);
            if(initList == null){
                initList = new LinkedList<>();
            }
            for(int x = 1; x * 2 <= i; x++){
                int y = i - x;
                if(dp.get(x) != null && dp.get(x).size() > 0 && dp.get(y) != null && dp.get(y).size() > 0){
                    List<List<Integer>> combine = combine(dp.get(x), dp.get(y));
                    if(combine.size() > 0){
                        initList.addAll(combine);
                    }
                }
            }
            dp.put(i, getDistinct(initList));
        }

        return dp.get(target);
    }

    private static List<List<Integer>> getDistinct(List<List<Integer>> lists){
        List<List<Integer>> res = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        for(List<Integer> list : lists){
            Collections.sort(list);
            StringBuilder sb = new StringBuilder();
            for(int i : list){
                sb.append(i);
            }
            if(!set.contains(sb.toString())){
                res.add(list);
                set.add(sb.toString());
            }
        }
        return res;
    }

    private static List<List<Integer>> combine(List<List<Integer>> l1, List<List<Integer>> l2){
        List<List<Integer>> res = new LinkedList<>();

        if((l1 == null || l1.size() == 0) && (l2 == null || l2.size() == 0)){
            return res;
        }
        if(l1 == null || l1.size() == 0){
            return l2;
        }
        if(l2 == null || l2.size() == 0){
            return l1;
        }

        HashSet<String> set = new HashSet<>();
        for(List<Integer> x : l1){
            for(List<Integer> y : l2){
                List<Integer> list = new LinkedList<>(x);
                list.addAll(y);
                Collections.sort(list);
                StringBuilder sb = new StringBuilder();
                for(int i : list){
                    sb.append(i);
                }
                if(!set.contains(sb.toString())){
                    res.add(list);
                    set.add(sb.toString());
                }
            }
        }
        return res;
    }

    // TODO: 以下解法为参考题解写出的 需要Review
    public static List<List<Integer>> combinationSumII(int[] candidates, int target){
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates);
        traverse(candidates, res, new LinkedList<>(), target, 0);
        return res;
    }

    private static void traverse(int[] candidates, List<List<Integer>> res, List<Integer> curList, int target, int index){
        if(target == 0){
            // Java中List传递的为引用，因此需要使用一份拷贝加入res，否则curList的值会在后续遍历中被修改
            res.add(new LinkedList<>(curList));
            return;
        }
        for(int i = index; i < candidates.length; i++){
            if(candidates[i] > target){
                break;
            }
            curList.add(candidates[i]);
            traverse(candidates, res, curList, target - candidates[i], i);
            // 因为Java中List传递的是引用，当前分支遍历完 回溯前删掉追加结点
            curList.remove(curList.size() - 1);
        }
    }
}