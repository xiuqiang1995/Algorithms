import java.util.Arrays;

/**
 * 线段树
 * 适用于区间求和
 * 核心思想是将对区间的操作缓存起来，需要查询的时候再统一结算
 */
public class SegmentTree {
    int[] arr;
    // 某个范围累加和信息
    int[] sum;
    // 某一个范围没有往下传递的累加和
    int[] lazy;
    int[] change;
    boolean[] update;

    public static void main(String[] args) {

        int[] demo = new int[]{1, 2, 3, 4};
        SegmentTree segmentTree = new SegmentTree(demo);
        printAll(segmentTree);

        // 全体加3
        for (int i = 0; i < 4; i++) {
            demo[i] += 3;
        }
        System.out.println("demo = " + Arrays.toString(demo));
        segmentTree.add(1, 10, 3, 1, 4, 1);
        printAll(segmentTree);


        // 部分加3
        for (int i = 0; i < 3; i++) {
            demo[i] += 3;
        }
        System.out.println("demo = " + Arrays.toString(demo));
        segmentTree.add(1, 3, 3, 1, 4, 1);
        printAll(segmentTree);


        // 求和
        long query = segmentTree.query(1, 10, 1, 4, 1);
        System.out.println("query = " + query);
        int demoSum = 0;
        for (int i = 0; i < 4; i++) {
            demoSum += demo[i];
        }
        System.out.println("demoSum = " + demoSum);
    }

    /**
     * 计算最小二次幂
     * @param cap
     * @return
     */
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static void printAll(SegmentTree segmentTree) {
        System.out.println("arr = " + Arrays.toString(segmentTree.arr));
        System.out.println("sum = " + Arrays.toString(segmentTree.sum));
        System.out.println("lazy = " + Arrays.toString(segmentTree.lazy));
        System.out.println("change = " + Arrays.toString(segmentTree.change));
        System.out.println("update = " + Arrays.toString(segmentTree.update));
        System.out.println();
    }

    public SegmentTree(int[] origin) {
        int newLength = origin.length + 1;
        // 新建数组长度为实际长度加1，后续计算下标从1开始，为了后续方便计算左右子节点的位置。
        arr = new int[newLength];
        for (int i = 1; i < newLength; i++) {
            arr[i] = origin[i - 1];
        }
        // << 2 左移2位，其实就是乘以4。这边应该改成大于 newLength 的最小二次幂的两倍
        int n2 = newLength << 2;
        sum = new int[n2];
        lazy = new int[n2];
        change = new int[n2];
        update = new boolean[n2];
        // 初始化 sum 数组
        initSum(1, origin.length, 1);
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    /**
     * 父节点接收新的任务之前需要将缓存的任务下发给左右孩子
     * 该方法就是将父节点的任务下发给左右孩子。若父节点无任务直接返回。
     *
     * @param rt
     * @param ln 左子树节点个数
     * @param rn 右子树节点个数
     */
    private void pushDown(int rt, int ln, int rn) {
        int leftChild = rt << 1;
        int rightChild = leftChild | 1;
        if (update[rt]) {
            update[leftChild] = true;
            update[rightChild] = true;
            change[leftChild] = change[rt];
            change[rightChild] = change[rt];
            lazy[leftChild] = 0;
            lazy[rightChild] = 0;
            sum[leftChild] = change[rt] * ln;
            sum[rightChild] = change[rt] * rn;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            // 左孩子缓存的增加了父节点缓存的任务
            lazy[leftChild] += lazy[rt];
            // 左孩子的累加和增加 左孩子个数 * 父节点缓存的任务
            sum[leftChild] += lazy[rt] * ln;
            // 右孩子同理
            lazy[rightChild] += lazy[rt];
            sum[rightChild] += lazy[rt] * rn;
            // 父节点缓存的任务都下发到左右孩子，父节点的任务清空
            lazy[rt] = 0;
        }
    }

    /**
     * 递归初始化 sum 数组
     * 因为 0 位置弃用了，所以左孩子可以用 2n，右孩子用 2n+1 来表示。
     */
    public void initSum(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        // (l + r) / 2
        int mid = (l + r) >> 1;
        // rt * 2 代表 rt 的左孩子
        initSum(l, mid, rt << 1);
        // rt * 2 + 1 代表 rt 的右孩子
        initSum(mid + 1, r, rt << 1 | 1);
        // 左右孩子都计算完毕后更新父节点的 sum
        pushUp(rt);
    }

    public void update(int L, int R, int C, int l, int r, int rt) {
        if (L <= l && r <= R) {
            update[rt] = true;
            change[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            return;
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    /**
     * 假设此时 arr 是 1～1024。此时 l=1，r=1024。
     * 假设现在有一个任务，将 1～1000 的区间的值加上3。 此时 L=1，R=1000，C=3。
     *
     * @param L
     * @param R
     * @param C
     * @param l
     * @param r
     * @param rt
     */
    public void add(int L, int R, int C, int l, int r, int rt) {
        // 任务的范围比 arr 数组的范围要大，如：L...l...r...R
        if (L <= l && r <= R) {
            // 只更新 rt 节点的 sum 值。
            sum[rt] += C * (r - l + 1);
            // 其他节点缓存在 lazy 数组中
            lazy[rt] += C;
            return;
        }
        // 下发任务
        // mid = (l + r) / 2;
        int mid = (r + l) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (L < mid) {
            ans += query(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }

}