package com.leetcode;

import java.util.Random;

public class MySortUtils{
    public static int[] generateRandomArray(int size){
        int[] retArr = new int[size];
        Random rand = new Random();
        for(int i = 0; i < size; i++){
            retArr[i] = rand.nextInt(1000);
        }
        return retArr;
    }

    public static void quickSort(int[] arr, String order){
        if(order.toUpperCase().equals("DSC")){
            quickSortDsc(arr, 0, arr.length - 1);
        }
        else{
            quickSortAsc(arr, 0, arr.length - 1);
        }
    }

    private static void quickSortAsc(int[] arr, int begin, int end){
        if(begin >= end){
            return;
        }
        int l = begin;
        int r = end;
        // 在index=l处挖坑
        int base = arr[l];
        while(l < r){
            while(l < r && arr[r] > base){
                r--;
            }
            if(l < r){
                // 将index=r处的数值填坑index=l，此时index=r成为新的坑
                arr[l] = arr[r];
                l++;
            }
            while(l < r && arr[l] < base){
                l++;
            }
            if(l < r){
                // 继续从左边找到新的数去填右侧的坑
                arr[r] = arr[l];
                r--;
            }
        }
        // 当左右游标重合时，将最开始的base拿来填坑
        arr[l] = base;
        quickSortAsc(arr, begin, l - 1);
        quickSortAsc(arr, l + 1, end);
    }

    private static void quickSortDsc(int[] arr, int begin, int end){
        if(begin >= end){
            return;
        }
        int base = arr[begin];
        int l = begin;
        int r = end;
        while(l < r){
            while(l < r && arr[r] < base){
                r--;
            }
            if(l < r){
                arr[l] = arr[r];
                l++;
            }
            while(l < r && arr[l] > base){
                l++;
            }
            if(l < r){
                arr[r] = arr[l];
                r--;
            }
        }
        arr[l] = base;
        quickSortDsc(arr, begin, l - 1);
        quickSortDsc(arr, l + 1, end);
    }
}