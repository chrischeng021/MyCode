package com.leetcode;

import java.util.*;

public class ArrayUtils {
    // ����һ�����г̳��ȱ���ѹ���������б�?nums?��
    // ����ÿ�����ڵ�����Ԫ�� [freq, val] = [nums[2*i], nums[2*i+1]]?������?i >=
    // 0?����ÿһ�Զ���ʾ��ѹ�����б����� freq?��ֵΪ?val?��Ԫ�أ�����Ҫ�����������������б������ɽ�ѹ����б�
    // ���㷵�ؽ�ѹ����б�
    // ���ӣ�https://leetcode-cn.com/problems/decompress-run-length-encoded-list
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

    // ���������������� startTime����ʼʱ�䣩�� endTime������ʱ�䣩����ָ��һ������ queryTime ��Ϊ��ѯʱ�䡣
    // ��֪���� i ��ѧ���� startTime[i] ʱ��ʼд��ҵ���� endTime[i] ʱ�����ҵ��
    // �뷵���ڲ�ѯʱ�� queryTime ʱ��������ҵ��ѧ����������ʽ�ϣ������ܹ�ʹ queryTime �������� [startTime[i],
    // endTime[i]]��������ѧ��������
    // ���ӣ�https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int ret = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                ret++;
            }
        }
        return ret;
    }

    // ��������������n �� start ��
    // ���� nums ����Ϊ��nums[i] = start + 2*i���±�� 0 ��ʼ���� n == nums.length ��
    // �뷵�� nums ������Ԫ�ذ�λ���XOR����õ��Ľ����
    // ���ӣ�https://leetcode-cn.com/problems/xor-operation-in-an-array
    public int xorOperation(int n, int start) {
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret ^= start + 2 * i;
        }
        return ret;
    }

    // ������ n �����۱ң�ÿ�ѵ��������������� coins �С�����ÿ�ο���ѡ������һ�ѣ��������е�һö������ö���������������۱ҵ����ٴ�����
    // ���ӣ�https://leetcode-cn.com/problems/na-ying-bi/
    public int minCount(int[] coins) {
        int res = 0;
        for (int i = 0; i < coins.length; i++) {
            res += (coins[i] + 1) / 2;
        }
        return res;
    }

    // һ������Ϊn-1�ĵ������������е��������ֶ���Ψһ�ģ�����ÿ�����ֶ��ڷ�Χ0��n-1֮�ڡ��ڷ�Χ0��n-1�ڵ�n������������ֻ��һ�����ֲ��ڸ������У����ҳ�������֡�
    // ���ӣ�https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
    public int missingNumber(int[] nums) {
        int shouldSum = (nums.length + 1) * nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            shouldSum -= nums[i];
        }

        return shouldSum;
    }

    // ����һ��δ��������������飬�ҵ���������ĵĵ������У������ظ����еĳ��ȡ�
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

    // ����һ������ arr �����㽫ÿ��Ԫ�������ұ�����Ԫ���滻����������һ��Ԫ�أ��� -1 �滻��
    // ��������滻���������㷵��������顣
    // ���ӣ�https://leetcode-cn.com/problems/replace-elements-with-greatest-element-on-right-side/
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
     * �ڸ����б��У��� i �׸����ĳ���ʱ��Ϊ time[i] �� �������ܳ���ʱ�䣨����Ϊ��λ���ɱ� 60 �����ĸ����Ե���������ʽ�ϣ�����ϣ������������ i
     * �� j ����? i < j ����?(time[i] + time[j]) % 60 == 0��
     * ���ӣ�https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-
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
     * ����һ�����ȹ̶�����������?arr�����㽫�������г��ֵ�ÿ���㶼��дһ�飬���������Ԫ������ƽ�ơ� ע�⣺�벻Ҫ�ڳ��������鳤�ȵ�λ��д��Ԫ�ء�
     * Ҫ��������������?�͵�?���������޸ģ���Ҫ�Ӻ��������κζ�����
     * ���ӣ�https://leetcode-cn.com/problems/duplicate-zeros
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
     * ���ι���·������?n?��վ���������?0?��?n - 1?���б�š�������֪ÿһ�����ڹ���վ֮��ľ��룬
     * distance[i]?��ʾ���Ϊ?i?�ĳ�վ�ͱ��Ϊ?(i + 1) % n?�ĳ�վ֮��ľ��롣 �����ϵĹ����������԰�˳ʱ�����ʱ��ķ�����ʻ��
     * ���س˿ʹӳ�����?start?��Ŀ�ĵ�?destination?֮�����̾��롣
     * ���ӣ�https://leetcode-cn.com/problems/distance-between-bus-stops
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
     * ����һ�����������һ������?k���ж��������Ƿ����������ͬ������?i?��?j��ʹ��?nums [i] = nums [j]������ i �� j?�Ĳ��
     * ����ֵ ����Ϊ k�� ���ӣ�https://leetcode-cn.com/problems/contains-duplicate-ii
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
     * ���������ַ��� s1 �� s2�����дһ������ȷ������һ���ַ������ַ��������к��ܷ�����һ���ַ�����
     * ���ӣ�https://leetcode-cn.com/problems/check-permutation-lcci/
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
     * ����һ������Ϊ n ���������飬�����ж��� ��� �ı� 1 ��Ԫ�ص�����£��������ܷ���һ���ǵݼ����С�
     * ���ӣ�https://leetcode-cn.com/problems/non-decreasing-array/
     * */
    public boolean checkPossibility(int[] nums) {
        if(nums.length <= 2){
            return true;
        }

        int decreasePair = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] > nums[i]){
                decreasePair++;
                if(((i + 1) < nums.length && (i - 2) >= 0) && 
                // ���������� 3��4��2��3���� i = 2 ʱ 4 > 2
                // ��ʱ������ѡ�� ��2�Ŵ���߽�4��С
                // ����2�Ŵ����������nump[i] >= nums[i - 1],������nums[3] < nums[1](3 < 4)����ʱֻ��ѡ��4��С
                // ���ǽ�4��С����ʱ������Ϊnums[0] > nums[2](3 > 2), ��Ȼ����������
                // ��ˣ��� nums[i - 2] > nums[i]) && (nums[i + 1] < nums[i - 1]) �����£����۷Ŵ�����С�����������
                // ��ʱֱ�ӷ���false
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

    /** ������ռ�ȳ���һ���Ԫ�س�֮Ϊ��ҪԪ�ء�����һ���������飬�ҵ�������ҪԪ�ء���û�У�����-1��
     ** ���ӣ�https://leetcode-cn.com/problems/find-majority-element-lcci/
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
     * ����������?0?��?1?��ɵ����� A�����Ƕ���?N_i����?A[0] ��?A[i]?�ĵ� i?�������鱻����Ϊһ�������������������Чλ�������Чλ����
     * ���ز���ֵ�б�?answer��ֻ�е�?N_i?���Ա� 5?����ʱ����?answer[i] Ϊ?true������Ϊ false��
     * ���ӣ�https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
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
     * ����һ�������б�����ɾ���б��б��������������ǵ����䡣
     * ֻ�е�?c <= a?��?b <= d?ʱ�����ǲ���Ϊ����?[a,b) ������?[c,d) ���ǡ�
     * ���������ɾ�����������㷵���б���ʣ���������Ŀ��
     * ���ӣ�https://leetcode-cn.com/problems/remove-covered-intervals
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
     * �����ַ���?S ��?T��ֻ���� S = T + ... + T��T?���������� 1 �λ��Σ�ʱ�����ǲ��϶�?��T �ܳ��� S����
     * ������ַ���?X��Ҫ������?X �ܳ��� str1 ��?X �ܳ��� str2��
     * ���ӣ�https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
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
     * ��������Сд��ĸ��ɵ��ַ������� A�������б��е�ÿ���ַ����ж���ʾ��ȫ���ַ��������ظ��ַ�����ɵ��б����磬���һ���ַ���ÿ���ַ����г��� 3 �Σ������� 4 �Σ�����Ҫ�����մ��а������ַ� 3 �Ρ�
     * ���ӣ�https://leetcode-cn.com/problems/find-common-characters
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

    /**
     * ��һ�� n * m �Ķ�ά�����У�ÿһ�ж����մ����ҵ�����˳������ÿһ�ж����մ��ϵ��µ�����˳�����������һ������������������һ����ά�����һ���������ж��������Ƿ��и�������
     * ���ӣ�https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     **/
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

    /**
     * �ҳ��������ظ������֡�
     * ��һ������Ϊ n ������ nums ����������ֶ��� 0��n-1 �ķ�Χ�ڡ�������ĳЩ�������ظ��ģ�����֪���м��������ظ��ˣ�Ҳ��֪��ÿ�������ظ��˼��Ρ����ҳ�����������һ���ظ������֡�
     * ���ӣ�https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
    **/
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
    // ����һ�� m * n �ľ��󣬾����е����� ������ͬ �����㰴 ���� ˳�򷵻ؾ����е�������������
    // ��������ָ����������ͬʱ��������������Ԫ�أ�
    // ��ͬһ�е�����Ԫ������С
    // ��ͬһ�е�����Ԫ�������
    // ���ӣ�https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
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

    // ����һ���ǿ���ֻ�����Ǹ�������������?nums, ����ĶȵĶ�����ָ��������һԪ�س���Ƶ�������ֵ��
    // ����������ҵ���?nums?ӵ����ͬ��С�Ķȵ�������������飬�����䳤�ȡ�
    // ���ӣ�https://leetcode-cn.com/problems/degree-of-an-array
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

    // ������������������ A �� B������ A ��ĩ�����㹻�Ļ���ռ����� B�� ��дһ���������� B �ϲ��� A ������
    // ��ʼ��?A �� B ��Ԫ�������ֱ�Ϊ?m �� n��
    // ���ӣ�https://leetcode-cn.com/problems/sorted-merge-lcci
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

    // ����������Ϸ��the game of master mind�����淨���¡�
    // �������4���ۣ�ÿ���۷�һ������ɫ�����Ǻ�ɫ��R������ɫ��Y������ɫ��G������ɫ��B�������磬�����������RGGB 4�֣���1Ϊ��ɫ����2��3Ϊ��ɫ����4Ϊ��ɫ����
    // ��Ϊ�û�������ͼ�³���ɫ��ϡ�����ȷ�������ܻ��YRGB��Ҫ�ǲ¶�ĳ���۵���ɫ������һ�Ρ����С���Ҫ��ֻ�¶���ɫ����λ�´��ˣ�����һ�Ρ�α���С���
    // ע�⣬�����С��������롰α���С���
    // ����һ����ɫ���solution��һ���²�guess����дһ�����������ز��к�α���еĴ���answer������answer[0]Ϊ���еĴ�����answer[1]Ϊα���еĴ�����
    // ���ӣ�https://leetcode-cn.com/problems/master-mind-lcci
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

    // �����ÿ��������Ϊһ�����ݣ���?i�����ݶ�Ӧ��һ���Ǹ�������������ֵ?cost[i](������0��ʼ)��
    // ÿ��������һ�������㶼Ҫ���Ѷ�Ӧ����������ֵ��Ȼ�������ѡ�������һ�����ݻ������������ݡ�
    // ����Ҫ�ҵ��ﵽ¥�㶥������ͻ��ѡ��ڿ�ʼʱ�������ѡ�������Ϊ 0 �� 1 ��Ԫ����Ϊ��ʼ���ݡ�
    // ���ӣ�https://leetcode-cn.com/problems/min-cost-climbing-stairs
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

    // ����һ���ǵݼ��� ���� �������飬��֪���������ǡ����һ�����������ĳ��ִ�����������Ԫ�������� 25%��
    // �����ҵ��������������
    // ���ӣ�https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/
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

    // ���ڷǸ�����?X?���ԣ�X?��������ʽ��ÿλ���ְ������ҵ�˳���γɵ����顣���磬���?X = 1231����ô��������ʽΪ?[1,2,3,1]��
    // �����Ǹ����� X ��������ʽ?A����������?X+K?��������ʽ��
    // ���ӣ�https://leetcode-cn.com/problems/add-to-array-form-of-integer
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

    // ����һ����������?arr�� ����Ԫ�� x ��ֻ�е� x + 1 Ҳ������?arr ��ʱ�����ܼ�Ϊ 1 ������
    // �������?arr �����ظ�������ÿ���ظ������������㡣
    // ���ӣ�https://leetcode-cn.com/problems/counting-elements
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

    // ����һ������ n?������������� 1?�� n ��ÿ������ 10 ���Ʊ�ʾ�µ���λ�ͣ�ÿһλ�ϵ�������ӣ���Ȼ�����λ����ȵ����ַŵ�ͬһ�����С�
    // ����ͳ��ÿ�����е�������Ŀ��������������Ŀ�����������ж��ٸ���
    // ���ӣ�https://leetcode-cn.com/problems/count-largest-group
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
}