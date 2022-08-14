import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 转化为大于等于 target 的最小下标和小于等于 target 的最大下标
class Solution {
    public static void main(String[] args) {
        System.out.println("new Solution().searchRange(new int[]{5,7,7,8,8,10},8) = " + new Solution().searchRange(new int[]{2, 2}, 3));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] error = {-1, -1};
        if (nums.length == 0) {
            return error;
        }

        // 大于等于 target 的最小下标 min
        int l = 0, r = nums.length - 1, min = -1, max = -1;
        while (r >= l) {
            int mid = l + (r - l) / 2;
            int n = nums[mid];
            if (n >= target) {
                r = mid - 1;
                min = mid;
            } else {
                l = mid + 1;
            }
        }

        // 小于等于 target 的最大下标 max
        l = 0;
        r = nums.length - 1;
        while (r >= l) {
            int mid = l + (r - l) / 2;
            int n = nums[mid];
            if (n <= target) {
                l = mid + 1;
                max = mid;
            } else {
                r = mid - 1;
            }
        }

        // searchRange(new int[]{2, 2}, 3)
        // 这种情况下，min 为 -1
        if (min == -1 || max == -1) {
            return error;
        }

        if (target == nums[min] && nums[min] == nums[max]) {
            return new int[]{min, max};
        }
        return error;
    }
}