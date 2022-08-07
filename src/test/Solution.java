package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] test = {"42", "10", "O", "t", "y", "p", "g", "B", "96", "H", "5", "v", "P", "52", "25", "96", "b", "L", "Y", "z", "d", "52", "3", "v", "71", "J", "A", "0", "v", "51", "E", "k", "H", "96", "21", "W", "59", "I", "V", "s", "59", "w", "X", "33", "29", "H", "32", "51", "f", "i", "58", "56", "66", "90", "F", "10", "93", "53", "85", "28", "78", "d", "67", "81", "T", "K", "S", "l", "L", "Z", "j", "5", "R", "b", "44", "R", "h", "B", "30", "63", "z", "75", "60", "m", "61", "a", "5", "S", "Z", "D", "2", "A", "W", "k", "84", "44", "96", "96", "y", "M"};
        System.out.println(Arrays.toString(solution.findLongestSubarray(test)));
    }

    public String[] findLongestSubarray(String[] array) {
        // 前缀和
        int s = 0;
        // 保存第一次
        Map<Integer, Integer> m = new HashMap<>();
        // 前缀和数组
        int[] sum = new int[array.length];
        int start = 0;
        int end = 0;
        int res = 0;
        // transform
        for (int i = 0; i < array.length; i++) {
            char c = array[i].charAt(0);
            if (c >= '0' && c <= '9') {
                // 数字 -1
                s--;
            } else {
                // 字母 + 1
                s++;
            }
            // 前缀和等于0的情况，[0,i] 这段长度数字和字母相同
            if (s == 0 && i + 1 > res) {
                start = 0;
                end = i + 1;
                res = i + 1;
            }
            sum[i] = s;
            // 判断这个前缀和是否已经出现过
            Integer integer = m.get(s);
            if (integer != null) {
                // 最长子数组
                if (i - integer > res) {
                    start = integer + 1;
                    end = i + 1;
                    res = i - integer;
                }
            } else {
                m.put(s, i);
            }
        }
        // 返回最长子数组
        return Arrays.copyOfRange(array, start, end);
    }
}