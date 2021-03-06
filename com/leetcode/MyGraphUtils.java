package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// 主要是图相关的题目
public class MyGraphUtils {
    // 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
    // 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
    // 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
    // 链接：https://leetcode-cn.com/problems/course-schedule

    // 第一种做法是使用入度表
    // BFS遍历
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        // 初始化并生成入度表
        HashMap<Integer, HashSet<Integer>> inDegreeMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> outDegreeMap = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            outDegreeMap.put(i, new HashSet<>());
            inDegreeMap.put(i, new HashSet<>());
        }

        for(int[] edge : prerequisites){
            outDegreeMap.get(edge[0]).add(edge[1]);
            inDegreeMap.get(edge[1]).add(edge[0]);
        }
        int availableCount = 0;

        Queue<Integer> free = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(inDegreeMap.get(i).size() == 0) {
                free.add(i);
                availableCount++;
            }
        }

        while(!free.isEmpty()){
            int from = free.poll();
            for(int to : outDegreeMap.get(from)){
                if(inDegreeMap.get(to).contains(from)) inDegreeMap.get(to).remove(from);
                if(inDegreeMap.get(to).size() == 0) {
                    free.add(to);
                    availableCount++;
                }
            }
        }

        return availableCount == numCourses;
    }
    // DFS遍历
    boolean ans = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();
        for(int[] edge : prerequisites){
            if(!edges.containsKey(edge[1])) edges.put(edge[1], new HashSet<>());
            edges.get(edge[1]).add(edge[0]);
        }
        
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses && ans; i++){
            if(visited[i] == 0){
                dfs(visited, i, edges);
            }
        }
        return ans;
    }
    public void dfs(int[] visited, int cur, HashMap<Integer, HashSet<Integer>> edges){
        visited[cur] = 1;
        if(edges.containsKey(cur)) {
            for(int to : edges.get(cur)){
                if(visited[to] == 1) {
                    ans = false;
                    return;
                }
                if(visited[to] == 0){
                    dfs(visited, to, edges);
                    if(!ans) return;
                }
            }
        }
        visited[cur] = 2;
    }

    // 现在你总共有 n 门课需要选，记为 0 到 n-1。
    // 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
    // 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
    // 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
    // 链接：https://leetcode-cn.com/problems/course-schedule-ii
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        // 初始化并生成入度表
        HashMap<Integer, HashSet<Integer>> inDegreeMap = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> outDegreeMap = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            outDegreeMap.put(i, new HashSet<>());
            inDegreeMap.put(i, new HashSet<>());
        }

        for(int[] edge : prerequisites){
            outDegreeMap.get(edge[1]).add(edge[0]);
            inDegreeMap.get(edge[0]).add(edge[1]);
        }
        int availableCount = 0;

        Queue<Integer> free = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(inDegreeMap.get(i).size() == 0) {
                free.add(i);
                availableCount++;
            }
        }
        int[] ans = new int[numCourses];
        int index = 0;

        while(!free.isEmpty()){
            int from = free.poll();
            ans[index++] = from;
            for(int to : outDegreeMap.get(from)){
                if(inDegreeMap.get(to).contains(from)) inDegreeMap.get(to).remove(from);
                if(inDegreeMap.get(to).size() == 0) {
                    free.add(to);
                    availableCount++;
                }
            }
        }

        if(availableCount != numCourses){
            return new int[0];
        }
        else{
            return ans;
        }
    }

    // DFS Solution
    boolean flag = true;
    int index = 0;
    int[] res;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        index = numCourses - 1;
        res = new int[numCourses];
        HashMap<Integer, HashSet<Integer>> edges = new HashMap<>();
        for(int[] edge : prerequisites){
            if(!edges.containsKey(edge[1])) edges.put(edge[1], new HashSet<>());
            edges.get(edge[1]).add(edge[0]);
        }
        
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses && flag; i++){
            if(visited[i] == 0){
                dfsFindOrder(visited, i, edges);
            }
        }

        if(!flag) return new int[0];
        return res;

    }
    public void dfsFindOrder(int[] visited, int cur, HashMap<Integer, HashSet<Integer>> edges){
        visited[cur] = 1;
        if(edges.containsKey(cur)) {
            for(int to : edges.get(cur)){
                if(visited[to] == 1) {
                    flag = false;
                    return;
                }
                if(visited[to] == 0){
                    dfsFindOrder(visited, to, edges);
                    if(!flag) return;
                }
            }
        }
        res[index--] = cur;
        visited[cur] = 2;
    }
}
