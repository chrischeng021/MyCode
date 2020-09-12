package com.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

// 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
// https://leetcode-cn.com/problems/spiral-matrix/
public class SpiralMatrix {
    public static List<Integer> spiralOrder(int[][] matrix) {
        int size = matrix.length * matrix[0].length;

        List<Integer> res = new ArrayList<Integer>(size);

        if(matrix.length == 0) return res;
        if(matrix.length == 1) {for(int num : matrix[0]) res.add(num); return res;}

        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        while(true){
            for(int i = left; i <= right; i++) res.add(matrix[top][i]);
            if(res.size() == size) break;
            for(int i = top + 1; i <= bottom; i++) res.add(matrix[i][right]);
            if(res.size() == size) break;
            for(int i = right - 1; i >= left; i--) res.add(matrix[bottom][i]);
            if(res.size() == size) break;
            for(int i = bottom - 1; i > top; i--) res.add(matrix[i][left]);
            if(res.size() == size) break;
            left++;
            top++;
            right--;
            bottom--;
        }
        return res;
    }
}