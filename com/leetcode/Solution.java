package com.leetcode;
import java.util.List;

<<<<<<< HEAD
import com.leetcode.easy.LargestTimeByGivenDigit;
import com.leetcode.easy.MaxStack;
import com.leetcode.middle.DivideTwoIntegers;
import com.leetcode.middle.MultiplyStrings;
import com.leetcode.middle.SortNumberByDicSequence;
=======
>>>>>>> f0bed0a645909513999bba8ecdd8911374d0c279
public class Solution {
    public static void printArray(Object[] arr){
        for(Object val : arr){
            System.out.print(val.toString() + " ");
        }
        System.out.println();
    }

    public static void printIntArray(int[] arr){
        for(int val : arr){
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void printList(List<Integer> list){
        for(int num : list){
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
<<<<<<< HEAD
        System.out.println(DivideTwoIntegers.divide(-2147483648, 1));
=======
        System.out.println(ArrayUtils.longestWord(new String[]{"rac","rs","ra","on","r","otif","o","onpdu","rsf","rs","ot","oti","racy","onpd"}));
>>>>>>> f0bed0a645909513999bba8ecdd8911374d0c279
    }
}