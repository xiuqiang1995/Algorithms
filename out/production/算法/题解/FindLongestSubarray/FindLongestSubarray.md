## 面试题 17.05.  字母与数字 
#前缀和 #哈希表
  
给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。  
  
返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。  
  
示例 1:  
输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]  
输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]  
  
示例 2:  
输入: ["A","A"]  
输出: []  
  
提示：  
array.length <= 100000  
  
来源：力扣（LeetCode）  
链接：https://leetcode.cn/problems/find-longest-subarray-lcci

## Ver1
```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 * 数字看成-1，字母看成1，再计算前缀和。
 * 哈希表记录第一次出现前缀和的下标。
 * 前缀和相同则计算下标的差值。
 */
public class Solution {

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
```