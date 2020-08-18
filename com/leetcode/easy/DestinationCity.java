package com.leetcode.easy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
 * 链接：https://leetcode-cn.com/problems/destination-city
 */
public class DestinationCity {
    public String destCity(List<List<String>> paths) {
        String dest = null;
        Set<String> startSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();

        for(List<String> path : paths)
        {
            startSet.add(path.get(0));
            endSet.add(path.get(path.size() - 1));
        }

        for(String city : endSet)
        {
            if(startSet.contains(city))
            {
                continue;
            }
            else
            {
                return city;
            }
        }
        return dest;
    }
}