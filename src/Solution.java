class Solution {
    int[] arr;
    int l;
    int r;
    int[] count;
    int sum;

    public int reversePairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        build(nums);
        for (int i = 1; i < arr.length; i++) {
            insert(arr[i], l, r, 1);
        }
        return sum;
    }

    public void insert(int v, int l, int r, int index) {
        if (l == r) {
            count[index]++;
            return;
        }
        int mid = (l + r) >> 1;
        if (v > mid) {
            // 右侧
            insert(v, mid + 1, r, index << 1 | 1);
        } else {
            // 左侧
            insert(v, l, mid, index << 1);
        }
        count[index] = count[index << 1] + count[index << 1 | 1];
        sum += query(l, v - 1, l, r, index);
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
        if (L < mid) {
            n += query(L, R, l, mid, index << 1);
        }
        if (R > mid) {
            n += query(L, R, mid, r, index << 1 | 1);
        }
        return n;
    }

    public void build(int[] origin) {
        int newLength = origin.length + 1;
        // 新建数组长度为实际长度加1，后续计算下标从1开始，为了后续方便计算左右子节点的位置。
        arr = new int[newLength];
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
        count = new int[tableSizeFor(origin.length) << 1];
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