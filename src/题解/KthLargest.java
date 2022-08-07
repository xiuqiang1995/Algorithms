package 题解;

/**
 * O(n) 时间复杂度内求无序数组中的第 K 大元素
 * 思路：快排
 */
class KthLargest {

    public static void main(String[] args) {
        int k = 1;
//        int[] array = {11, 8, 3, 9, 7, 1, 2, 5, 10};
//        int[] array = {99, 99};
        int[] array = {3, 3, 3, 3, 3, 3, 3, 3, 3};
        int kth = findKthLargest(array, k);
        System.out.println("第 " + k + " 大的元素为：" + kth);
    }

    public static int findKthLargest(int[] nums, int k) {
        // 第 k 大是从前往后第 length - k + 1 个元素
        return find(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    public static int find(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return arr[l];
        }
        int m = partition(arr, 0, r);
        if (m + 1 == k) {
            return arr[m];
        }
        if (m + 1 > k) {
            // 在前半段里找
            return find(arr, 0, m - 1, k);
        } else {
            // 在后半段里找
            return find(arr, m + 1, r, k);
        }
    }

    /**
     * 原地分区操作，把小于*等于*分区点的都移到分区点左边，反之则移到右边
     *
     * @param arr 原数组
     * @param l   left
     * @param r   right
     * @return
     */
    private static int partition(int[] arr, int l, int r) {
        // 取 l 到 r 最后一个元素做为分区点
        int pivot = arr[r];
        // i 左边都是小于等于 pivot 的元素，右边都是大于 pivot 的元素。 i 是未处理区间第一个值的下标
        int i = l;
        // 初始状态，i 和 j 都指向 l。j 依次向后移,若 arr[j] <= pivot,则交换下标 i 和 j 的元素的值，并且将 i 的下标有移一位。
        for (int j = l; j < r; j++) {
            // 相等也要交换，否则类似于 {3,3,3,3} 这样的数组下标就都是 0 不会往前
            if (arr[j] <= pivot) {
                // 交换下标为 i 和 j 的元素的值
                swap(arr, i, j);
                i++;
            }

        }
        /**
         * 交换下标为 i 和 r 的元素的值
         * arr[r] 是分区点 pivot
         * arr[i] 是大于分区点的第一个值
         */
        swap(arr, i, r);
        return i;
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
