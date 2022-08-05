package 模版.sort;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 递推公式：
 * merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))
 * <p>
 * 终止条件：
 * p >= r 不用再继续分解
 * <p>
 * 练习题：
 * 剑指 Offer 51. 数组中的逆序对 https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */

public class MergeSort {
    public static void main(String[] args) {
//        int[] array = {233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004};
        int[] array = {7,5,6,4};
        System.out.println("排序前 array = " + Arrays.toString(array));
        merge_sort(array);
        System.out.println("排序后 array = " + Arrays.toString(array));
    }

    public static void merge_sort(int[] array) {
        merge_sort_c(array, 0, array.length - 1);
    }

    /**
     * @param array 原数组
     * @param l     left
     * @param r     right
     */
    public static void merge_sort_c(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = l + (r - l) / 2;
        merge_sort_c(array, l, m);
        merge_sort_c(array, m + 1, r);
        merge(array, l, m, r);
    }

    /**
     * 将已经有序的array[l...m]和array[m+1...r]合并成一个有序数组，并放入array[l...r]中
     *
     * @param array
     * @param l     left
     * @param m     middle
     * @param r     right
     */
    public static void merge(int[] array, int l, int m, int r) {
        // 左半边第一个元素
        int x = l;
        // 右半边第一个元素
        int y = m + 1;
        // 临时数组的下标
        int z = 0;
        int[] temp = new int[r - l + 1];
        while (x <= m && y <= r) {
            if (array[x] <= array[y]) {
                temp[z] = array[x];
                x++;
            } else {
                temp[z] = array[y];
                y++;
            }
            // 临时数组的下标加 1
            z++;
        }
        // 判断哪个数组有剩余的
        int start = 0;
        int end = 0;
        if (x <= m) {
            // x有剩余
            start = x;
            end = m;
        } else if (y <= r) {
            // y 有剩余
            start = y;
            end = r;
        }
        // 将剩余的数据拷贝到临时数组tmp
        for (int i = start; i <= end; i++) {
            temp[z] = array[i];
            z++;
        }

        // 把临时数组拷贝到原数组中
        if (r - l + 1 >= 0) {
            System.arraycopy(temp, 0, array, l, r - l + 1);
        }
    }
}

