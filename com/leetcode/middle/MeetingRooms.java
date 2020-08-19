package com.leetcode.middle;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    // 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)
    // 请你判断一个人是否能够参加这里面的全部会议。
    // 链接：https://leetcode-cn.com/problems/meeting-rooms
    public static boolean canAttendMeetings(int[][] intervals) {
        boolean ans = true;
        if(intervals.length > 1){
            Arrays.sort(intervals, new Comparator<int[]>(){
                @Override
                public int compare(int[]o1, int[]o2){
                    return o1[0] - o2[0];
                }
            });
            int curEnd = intervals[0][1];
            for(int i = 1; i < intervals.length; i++){
                if(intervals[i][0] < curEnd){
                    ans = false;
                    break;
                }
                else{
                    curEnd = intervals[i][1];
                }
            }
        }
        return ans;
    }

    // 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)
    // 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
    // 链接：https://leetcode-cn.com/problems/meeting-rooms-ii
    // 思路：题目等价于给定公交车上所有乘客的上下车时间，求全程最多的同时乘车的人数
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length < 2){
            return intervals.length;
        }
        int ans = 0;
        int cur = 0;
        int[][] timeline = new int[intervals.length * 2][2];
        for(int i = 0, j = 0; i < intervals.length; i++){
            timeline[j++] = new int[]{intervals[i][0], 1};
            timeline[j++] = new int[]{intervals[i][1], -1};
        }
        Arrays.sort(timeline, new Comparator<int[]>(){
            @Override
            public int compare(int[]o1, int[]o2){
                // Cover同时有人上下车的场景
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });
        for(int i = 0; i < timeline.length; i++){
            int flag = timeline[i][1];
            cur += flag;
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}