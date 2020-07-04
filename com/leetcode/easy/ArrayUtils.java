package com.leetcode.easy;

import java.util.*;

public class ArrayUtils {
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

    /**
     * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     * 链接：https://leetcode-cn.com/problems/non-decreasing-array/
     * */
    public boolean checkPossibility(int[] nums) {
        return false;
    }

    /** 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     ** 链接：https://leetcode-cn.com/problems/find-majority-element-lcci/
     **/
    public int majorityElement(int[] nums) {
        return 0;
    }

    static Comparator<Integer> cmp = new Comparator<Integer>() {
        public int compare(Integer e1, Integer e2) {
            return e1 - e2;
        }
    };

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

    /**
     * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
     * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
     * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
     * */
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

    /**
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     * 链接：https://leetcode-cn.com/problems/remove-covered-intervals
     * */
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


    /**
     * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
     * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
     * 链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
     * */
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

//    public int lastRemaining(int n, int m) {
//        int removedCount = 0;
//        int curLength = n;
//        while(removedCount < n  -1){
//
//        }
//    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 链接：https://leetcode-cn.com/problems/find-common-characters
     * */
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
}