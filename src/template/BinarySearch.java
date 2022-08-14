package template;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int search = binarySearch.search(new int[]{1, 2, 3, 4, 5}, 3);
        System.out.println("search = " + search);
    }
    public int search(int[] nums, int target) {
        int l = -1, r = nums.length;
        while (r - l > 1) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid;
            } else if (nums[mid] < target) {
                l = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
