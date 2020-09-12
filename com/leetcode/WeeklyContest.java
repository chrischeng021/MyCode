package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* cSpell:disable */
public class WeeklyContest {
    // 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
    // 返回所有字符都为 1 的子字符串的数目。
    // 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
    // 链接：https://leetcode-cn.com/contest/weekly-contest-197/problems/number-of-substrings-with-only-1s/
    public static int numSub(String s) {
        long ans = 0;
        String[] arr = s.split("0");
        int base = (int)Math.pow(10, 9) + 7;
        for(String str : arr){
            if(str == null || str.length() == 0){
                continue;
            }
            long len = str.length();
            ans += ((len * (len + 1)) >> 1) % base;;
            ans %= base;
        }
        return (int)ans;
    }

    // 给你一个整数数组 nums 。
    // 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
    // 返回好数对的数目。
    // 链接：https://leetcode-cn.com/contest/weekly-contest-197/problems/number-of-good-pairs/
    public static int numIdenticalPairs(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else{
                map.put(num, 1);
            }
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > 1){
                int val = entry.getValue() - 1;
                ans += val * (val + 1) / 2;
            }
        }
        return ans;
    }

    // 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成.
    // 其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
    // 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
    // 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
    // 链接：https://leetcode-cn.com/contest/weekly-contest-197/problems/path-with-maximum-probability/
    // TODO: 本题本质为加权无向图的遍历。待看到图的数据结构再来做。
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double ans = 0;
        return ans;
    }

    public static String thousandSeparator(int n) {
        StringBuilder ans = new StringBuilder();
        while(n > 1000){
            StringBuilder temp = new StringBuilder().append(".");
            int val = n % 1000;
            int zeroCount = val > 99 ? 0 : val > 9 ? 1 : 2;
            while(zeroCount-- > 0){
                temp.append(String.valueOf(0));
            }
            temp.append(String.valueOf(val));
            ans.insert(0, temp.toString());
            n /= 1000;
        }
        ans.insert(0, String.valueOf(n));
        return ans.toString();
    }

    public static int minOperations(int[] nums) {
        int minusCount = 0;
        int zeroCount = 0;
        int multipleCount = 0;
        while(true){
            zeroCount = 0;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] % 2 != 0){
                    nums[i] -= 1;
                    minusCount++;
                }
                nums[i] >>= 1;
                if(nums[i] == 0){
                    zeroCount++;
                }
            }
            if(zeroCount == nums.length){
                break;
            }
            else{
                multipleCount++;
            }
        }
        return minusCount + multipleCount;
    }

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> ans  = new LinkedList<>();
        HashMap<Integer, HashSet<Integer>> fromMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> toMap = new HashMap<>();
        HashSet<Integer> reachedPoint = new HashSet<>();

        for(List<Integer> edge : edges){
            int from = edge.get(0);
            int to = edge.get(1);

            if(!fromMap.containsKey(from)){
                HashSet<Integer> set = new HashSet<>();
                fromMap.put(from, set);
                fromMap.get(from).add(to);
            }
            fromMap.get(from).add(to);

            if(!toMap.containsKey(to)){
                HashSet<Integer> set = new HashSet<>();
                toMap.put(to, set);
                toMap.get(to).add(from);
            }
            toMap.get(to).add(from);
        }

        for(int i = 0; i < n; i++){
            if(reachedPoint.contains(i)){
                continue;
            }
            search(ans, fromMap, toMap, reachedPoint, i);
        }

        return ans;
    }

    private static void search(List<Integer> ans, HashMap<Integer, HashSet<Integer>> fromMap, HashMap<Integer, HashSet<Integer>> toMap, HashSet<Integer> reachedPoint, int target){
        // First, find the most effective point;
        HashSet<Integer> targetSet = toMap.get(Integer.valueOf(target));
        int[] curMostEffective = new int[]{-1, Integer.MIN_VALUE};
        if(targetSet == null){
            curMostEffective[0] = target;
        }
        else{
            for(int i : targetSet){
                if(fromMap.get(i).size() > curMostEffective[1]){
                    curMostEffective[0] = i;
                    curMostEffective[1] = fromMap.get(i).size();
                }
            }
        }
        
        if(curMostEffective[0] > 0){
            ans.add(curMostEffective[0]);
            reachedPoint.add(curMostEffective[0]);
            if(fromMap.containsKey(curMostEffective[0])){
                reachedPoint.addAll(fromMap.get(curMostEffective[0]));
            }
        }

        rebuild(reachedPoint, fromMap, toMap);
    }

    private static void rebuild(HashSet<Integer> reachedPoint,
        HashMap<Integer, HashSet<Integer>> fromMap,
        HashMap<Integer, HashSet<Integer>> toMap){
        for(Map.Entry<Integer, HashSet<Integer>> entry : fromMap.entrySet()){
            HashSet<Integer> set = entry.getValue();
            Iterator<Integer> it = set.iterator();
            while(it.hasNext()){
                if(reachedPoint.contains(it.next())){
                    it.remove();
                }
            }
        }

        for(Map.Entry<Integer, HashSet<Integer>> entry : toMap.entrySet()){
            HashSet<Integer> set = entry.getValue();
            Iterator<Integer> it = set.iterator();
            while(it.hasNext()){
                if(reachedPoint.contains(it.next())){
                    it.remove();
                }
            }
        }
    }
}