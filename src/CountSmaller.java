import java.util.Arrays;

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * <p>
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * <p>
 * 示例：
 * 输入：[7,5,6,4]
 * 输出：5
 * <p>
 * 限制：
 * 0 <= 数组长度 <= 50000
 * <p>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * <p>
 * 线段树解法
 * 参考题解：https://leetcode.cn/problems/count-of-smaller-numbers-after-self/solution/c-xian-duan-shu-jie-fa-by-dufre/
 */
public class CountSmaller {
    // 保存线段范围内有几个元素，用于后续求逆序对
    static long[] count;
    long[] arr;
    // 数组中最小值
    static long l;
    // 数组中最大值
    static long r;
    static int sum;

    public static void main(String[] args) {
        long[] demo = new long[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        CountSmaller countSmaller = new CountSmaller(demo);
        for (int i = 1; i < countSmaller.arr.length; i++) {
            long v = countSmaller.arr[i];
            insert(v, l, r, 1);
            // 6 插入时，统计 4-5 的count
            sum += query(l, v - 1, l, r, 1);
            System.out.println("输入 " + v + " 后，sum = " + sum);
        }
        System.out.println("sum = " + sum);
    }

    public CountSmaller(long[] origin) {
        int newLength = origin.length + 1;
        // 新建数组长度为实际长度加1，后续计算下标从1开始，为了后续方便计算左右子节点的位置。
        arr = new long[newLength];
        arr[1] = origin[newLength - 2];
        r = l = arr[1];
        for (int i = 2; i < newLength; i++) {
            // 逆序数组
            arr[i] = origin[newLength - i - 1];
            // 找出数组中最大值和最小值做为根节点的线段范围
            {
                if (arr[i] > r) {
                    r = arr[i];
                }
                if (arr[i] < l) {
                    l = arr[i];
                }
            }
        }
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        // 初始化 count 数组，参数数组的最小二次幂的两倍
        count = new long[tableSizeFor(origin.length) << 1];
        System.out.println("Arrays.toString(count) = " + Arrays.toString(count));
        System.out.println("min = " + l + " ,max = " + r);

    }

    public void build(int[] origin) {
        int newLength = origin.length + 1;
        // 新建数组长度为实际长度加1，后续计算下标从1开始，为了后续方便计算左右子节点的位置。
        arr = new long[newLength];
        arr[1] = origin[newLength - 2];
        r = l = arr[1];
        for (int i = 2; i < newLength; i++) {
            // 逆序数组
            arr[i] = origin[newLength - i - 1];
            // 找出数组中最大值和最小值做为根节点的线段范围
            {
                if (arr[i] > r) {
                    r = arr[i];
                }
                if (arr[i] < l) {
                    l = arr[i];
                }
            }
        }
        // 初始化 count 数组，参数数组的最小二次幂的两倍
        count = new long[tableSizeFor(origin.length) << 1];
    }

    /**
     * @param v     插入线段树的值
     * @param l     数组最小值
     * @param r     数组最大值
     * @param index count 数组的下标
     */
    public static void insert(long v, long l, long r, int index) {
        if (l == r) {
            count[index]++;
            return;
        }
        long mid = (l + r) >> 1;
        if (v > mid) {
            // 右侧
            insert(v, mid + 1, r, index << 1 | 1);
        } else {
            // 左侧
            insert(v, l, mid, index << 1);
        }
        count[index] = count[index << 1] + count[index << 1 | 1];
        System.out.println("count[" + index + "] = count[" + (index << 1) + "] + count[" + (index << 1 | 1) + "]");

    }

    public static long query(long L, long R, long l, long r, int index) {
        int n = 0;
        if (R < L) {
            return 0;
        }

        if (L <= l && R >= r) {
            return count[index];
        }

        long mid = (l + r) >> 1;
        if (L == R && L == mid) {
            return count[index << 1];
        }
        if (L < mid) {
            n += query(L, R, l, mid, index << 1);
        }
        if (R > mid) {
            n += query(L, R, mid, r, index << 1 | 1);
        }
        return n;
    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}