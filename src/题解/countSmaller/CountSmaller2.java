package 题解.countSmaller;

import java.util.Arrays;

/**
 * 归并排序解法
 */
public class CountSmaller2 {
    // 逆序对
    int count = 0;

    public static void main(String[] args) {
//        int[] array = {233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004};
        int[] array = {1, 3, 2, 3, 1};
        System.out.println("排序前 array = " + Arrays.toString(array));
        CountSmaller2 countSmaller2 = new CountSmaller2();
        countSmaller2.merge_sort(array);
        System.out.println("排序后 array = " + Arrays.toString(array));
        System.out.println("count = " + countSmaller2.count);
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
        System.out.println("开始递归左侧：" + l + " - " + m + " ，对应右侧为：" + (m + 1) + " - " + r);
        merge_sort_c(array, l, m);
        System.out.println("开始递归右侧：" + (m + 1) + " - " + r);
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
        System.out.println("开始合并 " + l + " 到 " + r + ", m 为 " + m);
        while (x <= m && y <= r) {
            if (array[x] <= array[y]) {
                System.out.println(array[x] + " <= " + array[y]);
                temp[z] = array[x];
                x++;
                System.out.println("x 下标增加 1, 现在是 " + x);
                // 当前的y的位置 - y的初始位置 m+1
                System.out.println("y 的位置为：" + y + ", 初始位置为：" + (m + 1));

                // x <= m，意味着 x 是左侧最后一个元素，如果 x > m，这时候就不用统计逆序对了。 举个例子： 1 2 3 1 3，最后合并时被分为 1 2 3 和 1 3。
                if (x <= m) {
                    // 因为归并排序比较时，较小的数组的下标会右移。比如 1 2 3 1 3 这题，最后合并时是 1 2 3 和 1 3 合并。当左侧下标为 1 时，即值为 2，右侧下标为 3，即值为 1。然后 2 和 3 比较，2 小于 3，左侧下标右移一位，然后就会直接比较 3 和 3。 3 和 1比较就漏掉了，需要增加增加y - m - 1。
                    // y - m - 1 = y - (m + 1) 意思是要y当前位置减去y的初始位置。
                    count += (y - m - 1);
                    System.out.println("count += (y - m - 1) :" + count);
                }
            } else {
                temp[z] = array[y];
                System.out.println("(" + array[x] + "," + array[y] + ")");
                y++;
                count++;
                System.out.println("count = " + count);
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
