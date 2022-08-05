package 模版;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Graph { // 无向图
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * visited 是用来记录已经被访问的顶点，用来避免顶点被重复访问。如果顶点 q 被访问，那相应的 visited[q]会被设置为 true。
     * queue 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点。因为广度优先搜索是逐层访问的，也就是说，我们只有把第 k 层的顶点都访问完成之后，才能访问第 k+1 层的顶点。当我们访问到第 k 层的顶点的时候，我们需要把第 k 层的顶点记录下来，稍后才能通过第 k 层的顶点来找第 k+1 层的顶点。所以，我们用这个队列来实现记录的功能。
     * prev 用来记录搜索路径。当我们从顶点 s 开始，广度优先搜索到顶点 t 后，prev 数组中存储的就是搜索的路径。不过，这个路径是反向存储的。prev[w]存储的是，顶点 w 是从哪个前驱顶点遍历过来的。比如，我们通过顶点 2 的邻接表访问到顶点 3，那 prev[3]就等于 2。
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        // visited queue prev 初始化

        // 记录已经被访问的顶点
        boolean[] visited = new boolean[v];
        // 被访问但相邻的顶点还未被访问到的顶点
        Queue<Integer> queue = new LinkedList<>();

        // 初始化搜索路径数组。记录顶点 w 是从哪个前驱顶点遍历过来。
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        // 初始化完成

        visited[s] = true;
        queue.add(s);

        // 打印邻接表
        System.out.println("打印邻接表：");
        for (int i = 0; i < v; i++) {
            LinkedList<Integer> integers = adj[i];
            System.out.println("[" + i + "] = " + integers.toString());
        }

        printAll(visited, queue, prev);

        while (queue.size() != 0) {
            int w = queue.poll();

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(w + " 出队");
            System.out.println("adj[" + w + "] = " + adj[w]);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                System.out.println("adj[" + w + "].get(" + i + ") = " + q);
                System.out.println(q + " 访问过了吗？");
                if (visited[q]) {
                    System.out.println(q + " 访问过了，跳过！");
                }
                if (!visited[q]) {
                    System.out.println(q + " 没访问过。");
                    prev[q] = w;
                    System.out.println(q + " 的前驱节点设置为：" + w);
                    if (q == t) {
                        System.out.println("找到 t 啦，t == q，q：" + q);
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    System.out.println("把 " + q + " 设置为已访问过。");
                    queue.add(q);
                    System.out.println("把 q 加入 queue ：" + q);
                    System.out.println("queue = " + queue.toString());
                    System.out.println("====================================================");
                }
            }
            printAll(visited, queue, prev);
        }
    }

    private void printAll(boolean[] visited, Queue<Integer> queue, int[] prev) {
        System.out.println();
        System.out.println("----------------------------------------------------");
        printQueue(queue);
        System.out.println("visited = " + Arrays.toString(visited));
        System.out.println("prev = " + Arrays.toString(prev));
        System.out.println("----------------------------------------------------");
        System.out.println();
    }

    private void printQueue(Queue<Integer> queue) {
        System.out.print("queue:[");
        for (Integer n : queue) {
            System.out.print(n + ", ");
        }
        System.out.println("]");
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        // 0-7
        Graph graph = new Graph(8);
        int[][] temp = new int[][]{{0, 1}, {0, 3}, {1, 2}, {2, 5}, {1, 4}, {3, 4}, {4, 5}, {4, 6}, {5, 7}, {6, 7}};
        for (int i = 0; i < temp.length; i++) {
            int s = temp[i][0];
            int t = temp[i][1];
            graph.addEdge(s, t);
        }
        graph.bfs(0, 7);
    }

}





