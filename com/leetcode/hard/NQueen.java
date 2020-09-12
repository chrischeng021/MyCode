package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NQueen {
    // n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
    // 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
    // 链接：https://leetcode-cn.com/problems/n-queens/
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new LinkedList<>();
        if(n == 0){
            ans.add(new LinkedList<>());
            return ans;
        }
        HashSet<Integer> availableNumSet = new HashSet<>();
        for(int i = 0; i < n; i++) availableNumSet.add(i);
        List<int[]> possibileAnsList = new LinkedList<>();
        generateAllPossibleArrangement(n, possibileAnsList, availableNumSet, 0, new int[n]);
        generateFinalBoard(ans, possibileAnsList, n);
        return ans;
    }

    public static void generateAllPossibleArrangement(int n, List<int[]> res, HashSet<Integer> availableNumSet, int index, int[] curArrangement){
        if(index == n){
            if(isValidNQueenArrangement(curArrangement)){
                res.add(curArrangement);
            }
            return;
        }
        for(int num : availableNumSet){
            curArrangement[index] = num;
            HashSet<Integer> curAvailableNumSet = new HashSet<Integer>(availableNumSet);
            curAvailableNumSet.remove(num);
            generateAllPossibleArrangement(n, res, curAvailableNumSet, index + 1, Arrays.copyOf(curArrangement, n));
        }
    }

    private static boolean isValidNQueenArrangement(int[] arr){
        HashMap<Integer, Set<Integer>> attackedMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(!attackedMap.containsKey(i) || !attackedMap.get(i).contains(arr[i])){
                for(int x = i, y = arr[i]; x < arr.length && y < arr.length && x >= 0 && y >= 0; x++, y++){
                    tryPutKvIntoMap(attackedMap, x, y);
                }
                for(int x = i, y = arr[i]; x < arr.length && y < arr.length && x >= 0 && y >= 0; x--, y--){
                    tryPutKvIntoMap(attackedMap, x, y);
                }
                for(int x = i, y = arr[i]; x < arr.length && y < arr.length && x >= 0 && y >= 0; x++, y--){
                    tryPutKvIntoMap(attackedMap, x, y);
                }
                for(int x = i, y = arr[i]; x < arr.length && y < arr.length && x >= 0 && y >= 0; x--, y++){
                    tryPutKvIntoMap(attackedMap, x, y);
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static void tryPutKvIntoMap(HashMap<Integer, Set<Integer>> map, int x, int y){
        if(map.containsKey(x)){
            map.get(x).add(y);
        }
        else{
            Set<Integer> newSet = new HashSet<>();
            newSet.add(y);
            map.put(x, newSet);
        }
    }

    private static void generateFinalBoard(List<List<String>> res, List<int[]> possibileAnsList, int n){
        for(int[] arr : possibileAnsList){
            List<String> curBoard = new ArrayList<>(n);
            char[] line = new char[n];
            for(int i = 0; i < n; i++){
                Arrays.fill(line, '.');
                line[arr[i]] = 'Q';
                curBoard.add(String.valueOf(line));
            }
            res.add(curBoard);
        }
    }
    /**********************************************************************************/
    /*  以上是第一种解法 穷举出所有可能的排列 之后逐个判断当前排列是否为合法排列 能AC 但是效率较低  */
    /**********************************************************************************/
}