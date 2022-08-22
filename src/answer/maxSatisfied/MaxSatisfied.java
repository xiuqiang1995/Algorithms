package answer.maxSatisfied;

/**
 * 思路：
 * 滑动窗口
 * 从第一分钟开始发动技能滑到最后
 */
public class MaxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // 老板不用技能时，感到满足的顾客的数量
        int n = 0;
        int length = customers.length;

        for (int i = 0; i < length; i++) {
            int p = customers[i];
            // 老板不生气
            if (grumpy[i] == 0) {
                n += p;
            }
            // 老板一开始就发动技能，比如 minutes 是 3，那么 0 1 2 分钟的顾客都会满足（因为老板用了技能，不会生气）
            if (i < minutes && grumpy[i] == 1) {
                n += p;
            }
        }
        // max 为最多能满足的顾客的数量，初始值为老板在一开始就发动技能
        int max = n;
        // 滑动窗口，从第一次发动技能之后开始到最后
        for (int i = minutes; i < length; i++) {
            // 每次长度为 minutes 的滑动窗口每次向右移动时，最左侧从窗口中滑出的那一分钟，如果老板是生气的，就要把满意的顾客数量减去那一分钟的顾客数量。

            // 从窗口滑入
            if (grumpy[i] == 1) {
                n += customers[i];
            }
            // 从窗口滑出
            if (grumpy[i - minutes] == 1) {
                n -= customers[i - minutes];
            }
            max = n > max ? n : max;
        }
        return max;
    }
}
