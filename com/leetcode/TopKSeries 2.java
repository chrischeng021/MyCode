package com.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKSeries {
    /**********************************************************/
    // 总结：最小堆一般可用来解决最大K的问题
    // 最大堆可用来解决最小X的问题
    /**********************************************************/
    // 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
    // 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    // 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    // 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    // 你可以按任意顺序返回答案。
    // 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
    public static int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        HashMap<Integer, Integer> kv = new HashMap<>();
        for(int num : nums){
            if(kv.containsKey(num)){
                kv.put(num, kv.get(num) + 1);
            }
            else{
                kv.put(num, 1);
            }
        }
        for(Map.Entry<Integer, Integer> entry : kv.entrySet()){
            if(queue.size() < k){
                queue.add(new int[]{entry.getKey(), entry.getValue()});
                continue;
            }
            if(queue.size() == k){
                if(entry.getValue() < queue.peek()[1]){
                    continue;
                }
                else{
                    queue.poll();
                    queue.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }
        int []res = new int[k];
        for(int[] pair : queue){
            res[--k] = pair[0];
        }
        return res;
    }
}
