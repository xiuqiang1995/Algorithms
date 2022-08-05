package 题解.countSmaller;

import java.util.*;

/**
 * 线段树解法
 * 参考题解：https://leetcode.cn/problems/count-of-smaller-numbers-after-self/solution/c-xian-duan-shu-jie-fa-by-dufre/
 * 如果用数组存储线段树，若 2 个值的差很多，就导致需要很大的数组来存储，造成栈溢出。Exception in thread "main" java.lang.StackOverflowError
 * 例如 [2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647] 这个测试用力，数据范围是 正负 21 亿。
 * 这时候需要先将数据离散化。
 */

class CountSmaller {
    // 保存线段范围内有几个元素，用于后续求逆序对
    int[] count;
    Integer[] temp;
    int[] arr;
    // 线段树根结点左边界
    int l;
    // 线段树根结点右边界
    int r;
    int sum;

    public static void main(String[] args) {
        int[] demo = new int[]{7, 6, 5};
        int result = new CountSmaller().reversePairs(demo);
        System.out.println("result = " + result);
    }

    public int reversePairs(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        build(nums);
        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            insert(v, this.l, r, 1);
            int query = query(this.l, v - 1, this.l, this.r, 1);
            sum += query;
        }
        return sum;
    }

    /**
     * 二分查找
     *
     * @param originVal
     * @return
     */
    public int getIndex(int originVal) {
        int left = 0;
        int right = temp.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (originVal > temp[mid]) {
                left = mid + 1;
            } else if (originVal < temp[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public void build(int[] origin) {
        // 去重
        HashMap<Integer, Integer> hashmap = new HashMap<>(origin.length);
        for (int i = 0; i < origin.length; i++) {
            hashmap.put(origin[i], null);
        }
        Set<Integer> set = hashmap.keySet();

        int size = set.size();
        temp = new Integer[size];
        System.arraycopy(set.toArray(), 0, temp, 0, size);

        // FIXME 手动实现排序算法
        Arrays.sort(temp);

        int newLength = origin.length + 1;
        // 新建数组长度为实际长度加1，后续计算下标从1开始，为了后续方便计算左右子节点的位置。
        arr = new int[newLength];

        // 离散化，将 origin 数组的值映射为 temp 数组中的下标
        for (int i = 0; i < origin.length; i++) {
            // 逆序存储
            arr[newLength - i - 1] = getIndex(origin[i]);
        }

        // 离散化后，线段树根结点值范围为 1~origin.length
        this.l = 0;
        this.r = origin.length - 1;

        // 初始化 count 数组，参数数组的最小二次幂的两倍
        count = new int[tableSizeFor(origin.length) << 1];
    }

    public void insert(int v, int l, int r, int index) {
        if (l == r) {
            count[index]++;
            return;
        }
        int mid = (l + r) >> 1;
        if (v > mid) {
            // 踩坑点：遍历右侧需要从 mid + 1 开始
            insert(v, mid + 1, r, index << 1 | 1);
        } else {
            // 左侧
            insert(v, l, mid, index << 1);
        }
        count[index] = count[index << 1] + count[index << 1 | 1];
    }

    public int query(int L, int R, int l, int r, int index) {
        int n = 0;
        if (R < L) {
            return 0;
        }

        if (L <= l && R >= r) {
            return count[index];
        }

        int mid = (l + r) >> 1;
        if (L == R && L == mid) {
            return count[index << 1];
        }
        if (L < mid) {
            n += query(L, R, l, mid, index << 1);
        }
        if (R > mid) {
            // 踩坑点：遍历右侧需要从 mid + 1 开始
            n += query(L, R, mid + 1, r, index << 1 | 1);
        }
        return n;
    }

    int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

}