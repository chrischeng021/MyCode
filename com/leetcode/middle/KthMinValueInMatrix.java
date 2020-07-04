package com.leetcode.middle;

public class KthMinValueInMatrix {
    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
     * */
    public int kthSmallest(int[][] matrix, int k) {
        int arrNum = matrix.length;

        if(arrNum == 1){
            return matrix[0][k - 1];
        }
        int []mergedArr = matrix[0];

        for(int i = 1; i < arrNum; i++){
            mergedArr = this.mergeSortedArray(mergedArr, matrix[i]);
        }

        return mergedArr[k - 1];
    }

    /**
     * 合并两个有序数组
     * */
    private int[] mergeSortedArray(int[]a, int[]b){
        int[] ret = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while(i < a.length && j < b.length){
            if(a[i] <= b[j]){
                ret[index++] = a[i++];
                continue;
            }
            if(a[i] > b[j]){
                ret[index++] = b[j++];
            }
        }
        while(i < a.length){
            ret[index++] = a[i++];
        }
        while(j < b.length){
            ret[index++] = b[j++];
        }

        return ret;
    }
}
