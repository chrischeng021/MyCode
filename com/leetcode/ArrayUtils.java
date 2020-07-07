package com.leetcode;

import java.util.*;

public class ArrayUtils {
    // 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
    // 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
    // 链接：https://leetcode-cn.com/problems/non-decreasing-array
    public boolean checkPossibility(int[] nums) {
        if(nums.length <= 2){
            return true;
        }

        int decreasePair = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] > nums[i]){
                decreasePair++;
                if(((i + 1) < nums.length && (i - 2) >= 0) && 
                    (nums[i - 2] > nums[i]) && (nums[i + 1] < nums[i - 1])){
                    return false;
                }
            }
            if(decreasePair > 1){
                return false;
            }
        }
        return true;
    }

    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> maxK = new PriorityQueue<>(k);
        for(int num : nums)
        {
            if(maxK.size() == k && num > maxK.peek()){
                maxK.poll();
                maxK.add(num);
            }
            else if(maxK.size() < k) {
                maxK.add(num);
            }
        }

        return maxK.peek();
    }

    // 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
    // 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
    // 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
    public static List<Boolean> prefixesDivBy5(int[] A) {
        Boolean[] arr = new Boolean[A.length];
        int remainder = 0;

        for(int i = 0; i < A.length; i++){
            remainder = ((remainder << 1) + A[i]) % 5;
            arr[i] = remainder == 0;
        }

        return Arrays.asList(arr);
    }

    public String dayOfTheWeek(int day, int month, int year) {
        return "";
    }

    // 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
    // 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
    // 在完成所有删除操作后，请你返回列表中剩余区间的数目。
    // 链接：https://leetcode-cn.com/problems/remove-covered-intervals
    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] == t2[0] ? t2[1] - t1[1] : t1[0] - t2[0];
            }
        });

        int begin = intervals[0][0];
        int end = intervals[0][1];

        int num = 1;

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= begin && intervals[i][1] <= end){
                continue;
            }
            num++;
            begin = intervals[i][0];
            end = intervals[i][1];
        }

        return num;
    }

    // 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
    // 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
    // 链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
    private int gcd(int m, int n){
        if(n == 0) {
            return m;
        }
        else{
            return gcd(n, m%n);
        }
    }
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }
        return str1.substring(0, this.gcd(str1.length(), str2.length()));
    }


    // 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    // 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    // 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int targetRowIndex = 0;

        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        
        for(; targetRowIndex < matrix.length; targetRowIndex++){
            if(matrix[targetRowIndex][0] > target){
                break;
            }
        }

        if(targetRowIndex == 0){
            return false;
        }

        targetRowIndex--;

        if(matrix[targetRowIndex][0] == target){
            return true;
        }

        for(; targetRowIndex >= 0; targetRowIndex--){
            int targetIndex = Arrays.binarySearch(matrix[targetRowIndex], target);
            if(targetIndex >= 0){
                return true;
            }
        }

        return false;
    }

    // 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
    // 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    // 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
    public int findRepeatNumber(int[] nums) {
        byte[] bitArray = new byte[10000];
        for(int num : nums){
            if(bitArray[num] == 0x1){
                return num;
            }
            else{
                bitArray[num] = 0x1;
            }
        }
        return -1;
    }

    // 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
    // 幸运数是指矩阵中满足同时下列两个条件的元素：
    // 在同一行的所有元素中最小
    // 在同一列的所有元素中最大
    // 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
    public static List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();

        HashSet<Integer> maxNumberInRowSet = new HashSet<>(matrix[0].length);
        for(int i = 0; i < matrix.length; i++){
            maxNumberInRowSet.add(getMinNumberFromArray(matrix[i]));
        }

        HashSet<Integer> maxNumberInColumnSet = new HashSet<>(matrix.length);
        for(int i = 0; i < matrix[0].length; i++){
            int max = Integer.MIN_VALUE;
            for(int j = 0; j < matrix.length; j++){
                if(matrix[j][i] > max){
                    max = matrix[j][i];
                }
            }
            maxNumberInColumnSet.add(max);
        }
        for(int num : maxNumberInRowSet){
            if(maxNumberInColumnSet.contains(num)){
                list.add(num);
            }
        }
        return list;
    }

    private static int getMinNumberFromArray(int[]arr){
        int min = Integer.MAX_VALUE;
        for(int num : arr){
            min = Math.min(min, num);
        }
        return min;
    }

    // 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
    // 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
    // 链接：https://leetcode-cn.com/problems/degree-of-an-array
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int maxDegree = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.get(nums[i]).add(i);
                maxDegree = Math.max(maxDegree, map.get(nums[i]).size());
            }
            else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        int maxSubArrayLength = Integer.MAX_VALUE;

        for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
            if(entry.getValue().size() < maxDegree){
                continue;
            }
            ArrayList<Integer> list = entry.getValue();
            maxSubArrayLength = Math.min(maxSubArrayLength, list.get(list.size() - 1) - list.get(0));
        }

        return maxSubArrayLength;
    }

    // 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
    // 初始化 A 和 B 的元素数量分别为 m 和 n。
    // 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
    public static void merge(int[] A, int m, int[] B, int n) {
        if(n == 0){
            return;
        }
        int indexA = m - 1;
        int indexB = n - 1;

        int cursor = A.length - 1;

        while(indexA >= 0 && indexB >= 0){
            if(A[indexA] >= B[indexB]){
                swap(A, indexA--, cursor--);
                continue;
            }
            if(B[indexB] > A[indexA]){
                A[cursor--] = B[indexB--];
            }
        }
        while(indexB >= 0){
            A[cursor--] = B[indexB--];
        }
    }

    private static void swap(int[] arr, int x, int y){
        arr[x] = arr[x] + arr[y];
        arr[y] = arr[x] - arr[y];
        arr[x] = arr[x] - arr[y];
    }

    // 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；
    // 要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
    // 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，
    // 其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
    // 链接：https://leetcode-cn.com/problems/master-mind-lcci
    public static int[] masterMind(String solution, String guess) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int[] ret = new int[2];

        for(int i = 0; i < solution.length(); i++){
            char ch = solution.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            }
            else{
                map.put(ch, 1);
            }
        }

        for(int i = 0; i < guess.length(); i++){
            char ch = guess.charAt(i);
            if(solution.charAt(i) == ch){
                ret[0]++;
                map.put(ch, map.get(ch) - 1);
                continue;
            }
        }

        for(int i = 0; i < guess.length(); i++){
            char ch = guess.charAt(i);
            if(solution.charAt(i) != ch && map.containsKey(ch) && map.get(ch) > 0){
                ret[1]++;
                map.put(ch, map.get(ch) - 1);
            }
        }

        return ret;
    }

    // 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
    // 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
    // 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
    // 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
    public static int minCostClimbingStairs(int[] cost) {
        int size = cost.length;
        int[] minCost = new int[size];
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < size; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }
        return minCost[size - 1];
    }

    // 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
    // 请你找到并返回这个整数
    // 链接：https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/
    public int findSpecialInteger(int[] arr) {
        int gap = arr.length/4;

        for(int i = 0; i < arr.length; i++){
            if(arr[i + gap] == arr[i]){
                return arr[i];
            }
        }

        return arr[0];
    }

    public boolean hasGroupsSizeX(int[] deck) {
        if(deck.length == 1 || (deck.length == 2 && deck[0] != deck[1])){
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : deck){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else{
                map.put(num, 1);
            }
        }
        
        int minX = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(minX == Integer.MAX_VALUE){
                minX = entry.getValue();
                if(minX < 2){
                    return false;
                }
            }
            else{
                minX = gcd(minX, entry.getValue());
                if(minX < 2){
                    return false;
                }
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() % minX != 0){
                return false;
            }
        }

        return true;
    }

    // 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
    // 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
    // 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
    // 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>(A.length);
        int cap = 0;
        for(int i = A.length - 1; i >= 0; i--){
            int a = A[i];
            int b =  K > 0 ? K%10 : 0;
            int sum = a + b + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            list.add(0, sum);
            K /= 10;
        }

        while(K > 0){
            int sum = K % 10 + cap;
            cap = sum > 9 ? 1 : 0;
            sum = sum > 9 ? sum - 10 : sum;
            list.add(0, sum);
            K /= 10;
        }

        if(cap == 1){
            list.add(0, cap);
        }

        return list;
    }

    public boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : target){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else{
                map.put(num, 1);
            }
        }

        for(int num : arr){
            if(!map.containsKey(num)){
                return false;
            }
            else{
                map.put(num, map.get(num) - 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() != 0){
                return false;
            }
        }

        return true;
    }

    // 给你一个整数数组 arr， 对于元素 x ，只有当 x + 1 也在数组 arr 里时，才能记为 1 个数。
    // 如果数组 arr 里有重复的数，每个重复的数单独计算。
    // 链接：https://leetcode-cn.com/problems/counting-elements
    public int countElements(int[] arr) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : arr){
            if(map.containsKey(num)){
                map.put(num, map.get(num) + 1);
            }
            else{
                map.put(num, 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(map.containsKey(entry.getKey() + 1)){
                res += entry.getValue();
            }
        }

        return res;
    }

    // 给你一个整数 n 。请你先求出从 1 到 n 的每个整数 10 进制表示下的数位和（每一位上的数字相加），然后把数位和相等的数字放到同一个组中。
    // 请你统计每个组中的数字数目，并返回数字数目并列最多的组有多少个。
    // 链接：https://leetcode-cn.com/problems/count-largest-group
    public static int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int curMaxGroupSize = 1;
        int res = 0;

        for(int i = 1;i <= n; i++){
            int sum = 0;
            int temp = i;
            while(temp > 0){
                sum += temp % 10;
                temp /= 10;
            }
            if(map.containsKey(sum)){
                int updateSum = map.get(sum) + 1;
                map.put(sum, updateSum);
                curMaxGroupSize = Math.max(curMaxGroupSize, updateSum);
            }
            else{
                map.put(sum, 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == curMaxGroupSize){
                res++;
            }
        }

        return res;
    }

    // 在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
    // 返回重复了 N 次的那个元素。
    // 链接：https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/
    public int repeatedNTimes(int[] nums) {
        int cnt = 1;
        int res = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i] == res){
                cnt++;
            }
            else{
                if(--cnt == 0){
                    res = nums[i];
                    cnt = 1;
                }
            }
            if(cnt > 1){
                return res;
            }
        }

        cnt = 0;
        for(int num : nums){
            if(num == res){
                cnt++;
            }
        }
        return cnt > 1 ? res : nums[0];
    }

    // 给你一个以行程长度编码压缩的整数列表 nums 。
    // 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >=
    // 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
    // 请你返回解压后的列表。
    // 链接：https://leetcode-cn.com/problems/decompress-run-length-encoded-list
    public int[] decompressRLElist(int[] nums) {
        int resSize = 0;

        for (int i = 0; (i * 2 + 1) < nums.length; i++) {
            resSize += nums[2 * i];
        }

        int[] res = new int[resSize];
        int index = 0;
        for (int i = 0; (i * 2 + 1) < nums.length; i++) {
            int frequency = nums[2 * i];
            int val = nums[2 * i + 1];
            for (; frequency > 0; index++, frequency--) {
                res[index] = val;
            }
        }

        return res;
    }

    // 给你两个整数数组 startTime（开始时间）和 endTime（结束时间），并指定一个整数 queryTime 作为查询时间。
    // 已知，第 i 名学生在 startTime[i] 时开始写作业并于 endTime[i] 时完成作业。
    // 请返回在查询时间 queryTime 时正在做作业的学生人数。形式上，返回能够使 queryTime 处于区间 [startTime[i],
    // endTime[i]]（含）的学生人数。
    // 链接：https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ret = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ret++;
            }
        }
        return ret;
    }

    // 给你两个整数，n 和 start 。
    // 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
    // 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
    // 链接：https://leetcode-cn.com/problems/xor-operation-in-an-array
    public int xorOperation(int n, int start) {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret ^= start + 2 * i;
        }
        return ret;
    }

    // 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
    // 链接：https://leetcode-cn.com/problems/na-ying-bi/
    public int minCount(int[] coins) {
        int res = 0;
        for (int i = 0; i < coins.length; i++) {
            res += (coins[i] + 1) / 2;
        }
        return res;
    }

    // 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
    // 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
    public int missingNumber(int[] nums) {
        int shouldSum = (nums.length + 1) * nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            shouldSum -= nums[i];
        }

        return shouldSum;
    }

    // 给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
    public int findLengthOfLCIS(int[] nums) {
        int globalMaxLength = -1;
        int curMaxLength = 1;
        if (nums.length < 2) {
            return nums.length;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1]) {
                curMaxLength++;
            } else {
                globalMaxLength = globalMaxLength < curMaxLength ? curMaxLength : globalMaxLength;
                curMaxLength = 1;
            }
        }
        return Math.max(globalMaxLength, curMaxLength);
    }

    // 给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
    // 完成所有替换操作后，请你返回这个数组。
    // 链接：https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/
    public static int[] replaceElements(int[] arr) {
        if (arr.length == 1) {
            return new int[] { -1 };
        }

        if (arr.length == 0) {
            return arr;
        }

        int maxRight = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            int curRightMax = maxRight;
            if (arr[i] > maxRight) {
                maxRight = arr[i];
            }
            arr[i] = curRightMax;
        }

        return arr;
    }

    public int countCharacters(String[] words, String chars) {
        int[] letterMap = new int[26];
        int res = 0;

        for (int i = 0; i < chars.length(); i++) {
            letterMap[chars.charAt(i) - 'a'] += 1;
        }

        for (String str : words) {
            int[] wordMap = new int[26];
            for (int i = 0; i < str.length(); i++) {
                wordMap[str.charAt(i) - 'a'] += 1;
            }

            if (this.isContained(letterMap, wordMap)) {
                res += str.length();
            }
        }

        return res;
    }

    private boolean isContained(int[] source, int[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] < target[i]) {
                return false;
            }
        }
        return true;
    }

    /*
     * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i
     * 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
     * 链接：https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-
     * divisible-by-60
     */
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;
        int[] remainders = new int[60];

        for (int i : time) {
            remainders[i % 60]++;
        }

        res += remainders[0] * (remainders[0] - 1) / 2;
        res += remainders[30] * (remainders[30] - 1) / 2;

        for (int i = 1, j = 59; i < j; i++, j--) {
            res += remainders[i] * remainders[j];
        }

        return res;
    }

    public static int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[m - 1].length;
        int sum = 0;
        int curPos = n - 1;

        for (int i = 0; i < m; i++) {
            boolean isPositiveNumExist = false;
            for (int j = Math.min(curPos, n - 1); j >= 0; j--) {
                if (grid[i][j] >= 0) {
                    curPos = j + 1;
                    isPositiveNumExist = true;
                    break;
                }
            }
            sum += isPositiveNumExist ? n - curPos : n;

            if (!isPositiveNumExist) {
                sum += (m - 1 - i) * n;
                break;
            }
        }
        return sum;
    }

    /*
     * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。 注意：请不要在超过该数组长度的位置写入元素。
     * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     * 链接：https://leetcode-cn.com/problems/duplicate-zeros
     */
    public static void duplicateZeros(int[] arr) {
        int lastKeepIndex = 0;
        int duplicateZeroIndex = 0;
        int length = arr.length;
        while (duplicateZeroIndex < length) {
            if (arr[lastKeepIndex] == 0) {
                duplicateZeroIndex++;
            }
            duplicateZeroIndex++;
            lastKeepIndex++;
        }
        // Revert these two index to the last valid position.
        lastKeepIndex--;
        duplicateZeroIndex--;
        while (lastKeepIndex >= 0) {
            if (duplicateZeroIndex < length) {
                arr[duplicateZeroIndex] = arr[lastKeepIndex];
            }
            if (arr[lastKeepIndex] == 0) {
                arr[--duplicateZeroIndex] = arr[lastKeepIndex];
            }
            lastKeepIndex--;
            duplicateZeroIndex--;
        }
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int supportedMax = 0;

        if (n == 0) {
            return true;
        }

        if (flowerbed.length == 0 || (flowerbed.length < 3 && flowerbed[0] == 1)) {
            return false;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            if (this.isValidPosition(flowerbed, i)) {
                supportedMax++;
                flowerbed[i] = 1;
            }
        }
        return supportedMax >= n;
    }

    private boolean isValidPosition(int[] flowerbed, int targetIndex) {
        if (flowerbed[targetIndex] == 1) {
            return false;
        }
        if (targetIndex - 1 >= 0 && flowerbed[targetIndex - 1] == 1) {
            return false;
        }
        if (targetIndex + 1 < flowerbed.length && flowerbed[targetIndex + 1] == 1) {
            return false;
        }
        return true;
    }

    public boolean checkIfExist(int[] arr) {
        Set<Integer> numsSet = new HashSet<Integer>();
        int zeroCount = 0;

        for (int i = 0; i < arr.length; i++) {
            numsSet.add(arr[i]);
            if (arr[i] == 0) {
                zeroCount++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (numsSet.contains(arr[i] * 2) && ((arr[i] != 0 || zeroCount > 1))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，
     * distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。 环线上的公交车都可以按顺时针和逆时针的方向行驶。
     * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
     * 链接：https://leetcode-cn.com/problems/distance-between-bus-stops
     **/
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int sumDistance = 0;
        for (int i = 0; i < distance.length; i++) {
            sumDistance += distance[i];
        }
        int min = Math.min(start, destination);
        int max = Math.max(start, destination);

        int clockDistance = 0;
        for (int i = min; i < max; i++) {
            clockDistance += distance[i];
        }

        return Math.min(clockDistance, sumDistance - clockDistance);
    }

    /**
     * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的
     * 绝对值 至多为 k。 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, HashMap<String, Integer>> map = new HashMap<Integer, HashMap<String, Integer>>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (map.get(nums[i]).get("ceil") >= i && map.get(nums[i]).get("floor") <= i) {
                    return true;
                }
                int curFloor = Math.min(Math.abs(i - k), 0);
                int curCeil = i + k;

                if (map.get(nums[i]).get("ceil") < curCeil) {
                    map.get(nums[i]).put("ceil", curCeil);
                }

                if (map.get(nums[i]).get("floor") > curFloor) {
                    map.get(nums[i]).put("floor", curFloor);
                }
            } else {
                int floor = Math.min(Math.abs(i - k), 0);
                int ceil = i + k;
                map.put(nums[i], new HashMap<String, Integer>() {
                    {
                        put("floor", floor);
                        put("ceil", ceil);
                    }
                });
            }
        }
        return false;
    }

    /**
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     * 链接：https://leetcode-cn.com/problems/check-permutation-lcci/
     */
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length() != s2.length()){
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            if(map.containsKey(s1.charAt(i))){
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            }
            else{
                map.put(s1.charAt(i), 1);
            }

            if(map.containsKey(s2.charAt(i))){
                map.put(s2.charAt(i), map.get(s2.charAt(i)) - 1);
            }
            else{
                map.put(s2.charAt(i), -1);
            }
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() != 0){
                return false;
            }
        }

        return true;
    }

    /** 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     ** 链接：https://leetcode-cn.com/problems/find-majority-element-lcci/
     **/
    public int majorityElement(int[] nums) {
        if(nums.length == 0){
            return -1;
        }

        int count = 1;
        int curMain = nums[0];

        if(nums.length == 1){
            return curMain;
        }

        for(int i = 1; i < nums.length; i++){
            if(nums[i] == curMain){
                count++;
                continue;
            }
            if(count == 0){
                curMain = nums[i];
                count = 1;
            }
            else{
                count--;
            }
        }

        count = 0;
        for(int num : nums){
            if(num == curMain){
                count++;
            }
        }

        return count*2 >= nums.length ? curMain : -1;
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 链接：https://leetcode-cn.com/problems/find-common-characters
    */
    public static List<String> commonChars(String[] A) {
        int[] wordMinFrequency = new int[26];
        int[] wordFrequency = new int[26];

        for(String word : A){
            boolean[] isWordFrequencyUpdated = new boolean[26];
            HashMap<Character, Integer> curLetterMap = new HashMap<Character, Integer>();
            for(int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                if(!isWordFrequencyUpdated[index]){
                    isWordFrequencyUpdated[index] = true;
                    wordFrequency[index]++;
                }
                if(curLetterMap.containsKey(word.charAt(i))){
                    curLetterMap.put(word.charAt(i), curLetterMap.get(word.charAt(i)) + 1);
                }
                else{
                    curLetterMap.put(word.charAt(i), 1);
                }
            }
            for(int i = 0; i < 26; i++){
                if(curLetterMap.containsKey((char)('a' + i))){
                    if(wordMinFrequency[i] == 0){
                        wordMinFrequency[i] = curLetterMap.get((char)('a' + i));
                    }
                    else{
                        wordMinFrequency[i] = Math.min(curLetterMap.get((char)('a' + i)), wordMinFrequency[i]);
                    }
                }
            }
        }

        List<String> retList = new ArrayList<String>();
        for(int i = 0; i < 26; i++){
            if(wordFrequency[i] == A.length){
                for(int j = 0 ; j < wordMinFrequency[i]; j++){
                    retList.add(String.valueOf((char)('a' + i)));
                }
            }
        }
        return retList;
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 
    */
    public int binarySearch(int[] sortedArray, int target){
        if(sortedArray.length == 0 || (sortedArray.length == 1 && sortedArray[0] != target)){
            return -1;
        }

        int l = 0;
        int r = sortedArray.length - 1;
        int mid;

        while(l <= r){
            mid = (l + r)/2;
            if(sortedArray[mid] ==  target){
                return mid;
            }
            else if(sortedArray[mid] < target){
                l = mid + 1;
                continue;
            }
            else{
                r = mid - 1;
            }
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        int targetIndex = this.binarySearch(nums, target);
        int[] ret = new int[]{-1, -1};

        if(targetIndex == -1){
            return ret;
        }

        int right = targetIndex;
        int left = targetIndex;

        while(right < nums.length && nums[right] == target){
            ret[1] = right++;
        }
        while(left >= 0 && nums[left] == target){
            ret[0] = left--;
        }
        
        return ret;
    }

    public static int calculateMinLetterFrequency(String word){
        int[] arr = new int[26];
        for(int i = 0; i < word.length(); i++){
            arr[word.charAt(i) - 'a'] += 1;
        }

        for(int i = 0; i < 26; i++){
            if(arr[i] != 0){
                return arr[i];
            }
        }

        return 0;
    }

    public static int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wordValArr = new int[words.length];

        int[] ret = new int[queries.length];
        
        for(int i = 0; i < words.length; i++){
            wordValArr[i] = calculateMinLetterFrequency(words[i]);
        }

        Arrays.sort(wordValArr);
        HashMap<Integer, Integer> cache = new HashMap<>();

        for(int i = 0; i < queries.length; i++){
            int queryVal = calculateMinLetterFrequency(queries[i]);

            if(cache.containsKey(queryVal)){
                ret[i] = cache.get(queryVal);
            }
            else{
                int j = wordValArr.length - 1;
                while(j >= 0 && wordValArr[j] > queryVal){
                    j--;
                }
                ret[i] = wordValArr.length - j - 1;
                cache.put(queryVal, ret[i]);
            }
        }

        return ret;
    }

    // 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    // 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    // 链接：https://leetcode-cn.com/problems/house-robber
    // 注：递归解法会超时/爆栈，提交时还是只能用动态规划
    // public int rob(int[] nums) {
    //     return rob(nums, nums.length);
    // }
    // private int rob(int[]nums, int len){
    //     if(len == 0){
    //         return 0;
    //     }
    //     else if(len == 1){
    //         return nums[0];
    //     }
    //     else if(len == 2){
    //         return Math.max(nums[0], nums[1]);
    //     }
    //     else{
    //         return Math.max(rob(nums, len - 1), rob(nums, len - 2) +  nums[len - 1]);
    //     }
    // }
    public static int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);
        for(int i = 3; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    /*
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     * Link: https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
    */
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

    // 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
    // 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
    public static int[] exchange(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r && l < nums.length && r >= 0){
            while(l < nums.length && nums[l] % 2 == 1){
                l++;
            }
            while(r >= 0 && nums[r] % 2 == 0){
                r--;
            }
            if(l >= nums.length || r < 0){
                break;
            }
            if(l < r){
                swap(nums, l++, r--);
            }
        }
        return nums;
    }

    // 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
    // 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
    // TODO: 本题可用快排优化 等算法刷到排序了再来
    public static int[] getLeastNumbers(int[] arr, int k) {
        Queue<Integer> minQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int num : arr){
            minQueue.add(num);
        }
        int[] res = new int[k];
        while(k > 0){
            res[res.length - k] = minQueue.poll();
            k--;
        }

        return res;
    }

    // 给出一个区间的集合，请合并所有重叠的区间。
    // 链接：https://leetcode-cn.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] == t2[0] ? t1[1] - t2[1] : t1[0] - t2[0];
            }
        });
        
        int l= intervals[0][0];
        int r= intervals[0][1];

        for (int i = 1; i < intervals.length; i++){
            if(l <= intervals[i][0] && r >= intervals[i][0]){
                r = Math.max(intervals[i][1], r);
                continue;
            }
            else{
                list.add(new int[]{l, r});
                l = intervals[i][0];
                r = intervals[i][1];
            }
        }

        int[][] res = new int[list.size() + 1][];
        int index = 0;
        for(int[] sub : list){
            res[index++] = sub;
        }
        res[index] = new int[]{l, r};

        return res;
    }

    // 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    // 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    // 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
    // 公式： 2x = (2m + n)(n + 1), n为长度，m为起始值 n>= 1, m >= 1
    public static int[][] findContinuousSequence(int target) {
        int val = target * 2;
        List<int[]> list = new ArrayList<int[]>();
        for(int n = 1; n < Math.sqrt(val); n++){
            if(val % (n + 1) != 0 || ((val / (n + 1) - n) % 2 != 0)){
                continue;
            }
            int m = (val / (n + 1) - n) / 2;
            if(m == 0){
                break;
            }
            int [] sub = new int[n + 1];
            for(int j = 0; j <= n; j++){
                sub[j] = m + j;
            }
            list.add(0, sub);
        }

        int[][] res = new int[list.size()][];
        int index = 0;
        for(int[] sub : list){
            res[index++] = sub;
        }
        return res;
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

    private static Comparator<Integer> cmp = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            if(a == b){
                return 0;
            }
            return String.valueOf(a).compareTo(String.valueOf(b));
        }
    };
}