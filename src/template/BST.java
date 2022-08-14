package template;

/**
 * 二叉搜索树
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 */
public class BST {
    int sum = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node0 = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        root.left = node1;
        node1.left = node0;
        node1.right = node2;
        node2.right = node3;
        root.right = node6;
        node6.left = node5;
        node6.right = node7;
        node7.right = node8;
        TreeNode node = new BST().convertBST(root);
        System.out.println("node = " + node);
    }

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
//            printTree(root);
            // 逆序中序
            convertInOrder(root);
            System.out.println();
            System.out.println("打印根结点：" + root.val);
            printChild(root, root.val, root.right, " 的右节点 ", " 无右节点，跳过～");
            convertBST(root.right);
            System.out.println(root.val + " 右节点遍历完毕，开始更新节点的值。");
            sum += root.val;
            System.out.println("sum = " + sum);
            int temp = root.val;
            root.val = sum;
            System.out.println("节点 " + temp + " 的值更新为 " + sum);
            printChild(root, temp, root.left, " 的左节点 ", " 无左节点,跳过～");
            convertBST(root.left);
        }
        return root;
    }

    private void printChild(TreeNode root, int rootVal, TreeNode right2, String s, String s2) {
        if (right2 != null) {
            System.out.println("遍历结点 " + rootVal + s + right2.val);
        } else {
            System.out.println("节点 " + rootVal + s2);
        }
    }

    /**
     * 前中后序遍历中的序，就是当前父节点和左右节点的顺序。
     * 前序就是 父 左 右
     * 中序就是 左 父 右
     * 后序就是 左 右 父
     * @param root
     */
    private void printTree(TreeNode root) {
        System.out.print("前序遍历： ");
        preOrder(root);
        System.out.println();

        System.out.print("中序遍历： ");
        inOrder(root);
        System.out.println();

        System.out.print("后序遍历： ");
        postOrder(root);
        System.out.println();
    }

    /**
     * 前序遍历
     * 前序遍历是指，对于树中的任意节点来说，先打印这个节点，然后再打印它的左子树，最后打印它的右子树。
     *
     * @param root
     */
    void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     * 中序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它本身，最后打印它的右子树。
     *
     * @param root
     */
    void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    /**
     * 逆序中序
     * @param root
     */
    void convertInOrder(TreeNode root){
        if(root == null) return;
        convertInOrder(root.right);
        System.out.print(root.val + " ");
        convertInOrder(root.left);
    }

    /**
     * 中序遍历
     * 后序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它的右子树，最后打印这个节点本身。
     *
     * @param root
     */
    void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
