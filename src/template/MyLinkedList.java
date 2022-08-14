package template;

// 单向链表
class MyLinkedList {

    /**
     * ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
     * [[],             [2],        [1],                [2],        [7],        [3],        [2],        [5],        [5],    [5],    [6],            [4]]
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
         * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
         * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
         * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
         *
         * 链接：https://leetcode.cn/problems/design-linked-list
         */
        MyLinkedList l = new MyLinkedList();
        l.addAtHead(2);
        l.deleteAtIndex(1);
        l.addAtHead(2);
        l.addAtHead(7);
        l.addAtHead(3);
        l.addAtHead(2);
        l.addAtHead(5);
        l.addAtTail(5);
        l.get(5);
        l.deleteAtIndex(6);
        l.deleteAtIndex(4);
    }

    // 哨兵节点
    Node sentry;
    // 尾节点
    Node tail;
    // 链表长度
    int len;

    public void print() {
        Node next = sentry.next;
        System.out.print(next.val);
        while (next.next != null) {
            next = next.next;
            System.out.print("->" + next.val);
        }
        System.out.println();
    }

    public MyLinkedList() {
        // 头节点和尾节点都指向同一个空的节点
        Node temp = new Node();
        sentry = temp;
        tail = temp;
        sentry.next = null;
    }

    public int get(int index) {
        if (index < 0 || index > len - 1) {
            return -1;
        }
        Node head = sentry.next;
        if (index == 0) {
            return head.val;
        }
        // index > 1 的情况
        for (int i = 1; i <= index; i++) {
            head = head.next;
        }
        return head.val;
    }

    public void addAtHead(int val) {
        // 新建一个节点
        Node node = new Node(val);
        // 新节点的 next 指向当前的头节点
        node.next = sentry.next;
        // 哨兵节点的 next 指向 head
        sentry.next = node;
        len++;
    }

    public void addAtTail(int val) {
        if (len == 0) {
            addAtHead(val);
            return;
        }
        len++;
        Node head = sentry.next;
        while (head.next != null) {
            head = head.next;
        }
        Node node = new Node(val);
        node.next = null;
        head.next = node;
        tail = node;
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == len) {
            addAtTail(val);
            return;
        }
        if (index > len) {
            return;
        }
        if (index < 0) {
            addAtHead(val);
        }
        // 下标 0
        Node n = sentry.next;
        for (int i = 0; i < index - 1; i++) {
            n = n.next;
        }
        Node node = new Node(val);
        node.next = n.next;
        n.next = node;
        len++;
    }

    /**
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > len - 1) {
            return;
        }

        len--;

        // 头节点
        Node head = sentry.next;

        if (index == 0) {
            sentry.next = head.next;
            return;
        }

        // 找到 index-1 个节点
        for (int i = 1; i < index; i++) {
            head = head.next;
        }

        head.next = head.next.next;
    }
}

class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }

    public Node() {
    }
}
/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */