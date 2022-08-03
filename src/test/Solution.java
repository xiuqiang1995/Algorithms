package test;

/**
 * 归并排序解法
 */
public class Solution {
    // 逆序对
    int count = 0;

    public int reversePairs(int[] nums) {
        merge_sort(nums);
        return count;
    }


    public void merge_sort(int[] array) {
        merge_sort_c(array, 0, array.length - 1);
    }

    /**
     * @param array 原数组
     * @param l     left
     * @param r     right
     */
    public void merge_sort_c(int[] array, int l, int r) {
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
    public void merge(int[] array, int l, int m, int r) {
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
                count += (r - m - 1);
            } else {
                temp[z] = array[y];
                y++;
                count++;
                System.out.println("l = " + l + ", m = " + m + ", r = " + r + ",count = " + count);
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
            count += (end - start) * (r - m);
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
