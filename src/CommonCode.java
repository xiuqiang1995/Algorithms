import java.util.Arrays;

public class CommonCode {
    public static void main(String[] args) {
        int[] demo = {1, 2, 3, 4};

        // 打印数组
        System.out.println("demo = " + demo);
        System.out.println("Arrays.toString(demo) = " + Arrays.toString(demo));

        // System.arraycopy 拷贝数组，包括 srcPos
        int[] copy = new int[3];
        System.arraycopy(demo, 1, copy, 0, 3);
        System.out.println("Arrays.toString(copy) = " + Arrays.toString(copy));
    }
}
