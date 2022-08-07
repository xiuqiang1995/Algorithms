package 模版.排序;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = {5, 2, 3, 1};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * @param a 需要排序的数组
     * @param p 最左
     * @param r 最右
     */
    public void sort(int[] a, int p, int r) {
        if (p >= r) {
            System.out.printf("p >= r, p:%s, r:%s, return \n", p, r);
            System.out.println();
            return;
        }
        // 分区点
        System.out.printf("原地分区前，arr:%s \n", Arrays.toString(a));
        int q = partition(a, p, r);
        System.out.printf("原地分区后，分区点的下标:%s, 分区点的值:%s, arr:%s \n", q, a[q], Arrays.toString(a));
        System.out.println();

        System.out.printf("排序左侧 =======>，%s 到 %s \n", p, q - 1);
        sort(a, p, q - 1);
        System.out.printf("排序右侧 <=======，%s 到 %s \n", q + 1, r);
        sort(a, q + 1, r);
    }

    /**
     * 原地分区操作，并返回分区完成后分区点的下标
     *
     * @param arr
     * @param p
     * @param r
     * @return
     */
    public int partition(int[] arr, int p, int r) {
        // 获取分区点
//        int randomPivot = getRandomPivot(p, r);
        int randomPivot = 1;
        int pivot = arr[randomPivot];
        System.out.printf("分区点的下标为：%s, 分区点的值为：%s \n", randomPivot, pivot);

        // i 是大于分区点的第一个元素的下标
        int i = p;
        for (int j = p; j < r; j++) {
            // 相等也要交换，否则类似于 {3,3,3,3} 这样的数组下标就都是 0 不会往前
            if (arr[j] <= pivot) {
                System.out.printf("%s <= %s, swap, %s\n", arr[j], pivot, Arrays.toString(arr));
                // 交换下标为 i 和 j 的元素的值
                if (i != j) {
                    System.out.printf("交换 %s 和 %s 的值\n", arr[i], arr[j]);
                    swap(arr, i, j);
                    System.out.println("arr:" + Arrays.toString(arr));
                }
                i++;
            } else {
                System.out.printf("%s > %s, no swap, %s\n", arr[i], pivot, Arrays.toString(arr));
            }
        }
        /**
         * 交换下标为 i 和 pivot 的元素的值
         * arr[pivot] 是分区点 pivot 的值
         * arr[i] 是大于分区点的第一个值
         */
//        if (i != randomPivot) {
//            System.out.printf("j >= r, 交换下标为 %s 和 %s 的元素的值\n", i, randomPivot);
//            swap(arr, i, randomPivot);
//        }
        return i;
    }

    public int getRandomPivot(int start, int end) {
        //创建Random类对象
        Random random = new Random();

        //产生随机数
        int number = random.nextInt(end - start + 1) + start;

        //打印随机数
        System.out.println("产生一个 " + start + " 到 " + end + " 之间的随机整数：" + number);
        return number;
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
