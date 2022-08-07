package 题解.FindLongestSubarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 * 数字看成-1，字母看成1，再计算前缀和。
 * 前缀和 相同则计算下标的差值。
 */
public class FindLongestSubarray {
    public static void main(String[] args) {
        FindLongestSubarray solution = new FindLongestSubarray();
        String[] test = {"42", "10", "O", "t", "y", "p", "g", "B", "96", "H", "5", "v", "P", "52", "25", "96", "b", "L", "Y", "z", "d", "52", "3", "v", "71", "J", "A", "0", "v", "51", "E", "k", "H", "96", "21", "W", "59", "I", "V", "s", "59", "w", "X", "33", "29", "H", "32", "51", "f", "i", "58", "56", "66", "90", "F", "10", "93", "53", "85", "28", "78", "d", "67", "81", "T", "K", "S", "l", "L", "Z", "j", "5", "R", "b", "44", "R", "h", "B", "30", "63", "z", "75", "60", "m", "61", "a", "5", "S", "Z", "D", "2", "A", "W", "k", "84", "44", "96", "96", "y", "M"};
        System.out.println(Arrays.toString(solution.findLongestSubarray(test)));
    }

    public String[] findLongestSubarray(String[] array) {
        // 前缀和
        int s = 0;
        // 保存第一次
        Map<Integer, Integer> m = new HashMap<>();
        // 前缀和数组，保存该前缀和第一次出现时，数组的下标
        int[] sum = new int[array.length];
        // 最长的字母和数字个数相同的子数组的长度
        int max = 0;
        // 子数组起始下标
        int start = 0;
        // 题目中只要求数字和字母个数相同，跟数字和字母具体是多少没关系。所以将数字看成 -1，字母看成 1，类似于线段树中离散化的思想。
        for (int i = 0; i < array.length; i++) {
            char c = array[i].charAt(0);
            if (c >= '0' && c <= '9') {
                // 数字 -1
                s--;
            } else {
                // 字母 + 1
                s++;
            }
            // 前缀和等于0的情况，[0,i] 区间内所有元素的和为 0，即数字和字母个数相同。区间长度为 i+1
            if (s == 0 && i + 1 > max) {
                start = 0;
                max = i + 1;
            }
            // 填充前缀和数组
            sum[i] = s;
            // 该前缀和第一次出现时的下标
            Integer prefixFist = m.get(s);
            if (prefixFist != null) {
                // 当前下标 i 减去第一次出现该前缀和的下标 prefixFist，计算出区间长度
                if (i - prefixFist > max) {
                    // 举个例子 前缀和为 1 0 1，下标分别为 0 1 2，第一次出现前缀和为 1 的下标为 0，第二次出现前缀和的下标为 2。区间 0 1 的长度为 2 - 0 = 2
                    start = prefixFist + 1;
                    max = i - prefixFist;
                }
            } else {
                m.put(s, i);
            }
        }
        // 返回最长子数组
        String[] copy = new String[max];
        System.arraycopy(array, start, copy, 0, max);
        return copy;
    }
}
