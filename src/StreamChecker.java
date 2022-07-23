class StreamChecker {

    private TrieNode root = new TrieNode('/'); // 存储无意义字符
    StringBuilder builder = new StringBuilder();

    public StreamChecker(String[] words) {
        for (String word : words) {
            StringBuilder stringBuilder = new StringBuilder(word);
            // 反转字符串后构建字典树
            root.insert(stringBuilder.reverse().toString().toCharArray());
        }
    }

    public boolean query(char letter) {
        // 左侧插入
        StringBuilder param = builder.insert(0, letter);
        int length = param.length();
        // 此时 temp 是 '/'
        TrieNode temp = root;
        for (int i = 0; i < length; i++) {
            char c = param.charAt(i);
            temp = temp.children[c - 'a'];
            if (temp == null) {
                return false;
            }
            // 若 temp 和 该字符匹配上了，则需要判断 temp 是否是红色节点。
            if (temp.isEndingChar) {
                return true;
            }
        }
        return false;
    }


    public class TrieNode {
        public char data;
        // a-z 26 个小写字母
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;

        public TrieNode(char data) {
            this.data = data;
        }

        public void insert(char[] text) {
            TrieNode p = root;
            for (int i = 0; i < text.length; ++i) {
                int index = text[i] - 'a';
                if (p.children[index] == null) {
                    TrieNode newNode = new TrieNode(text[i]);
                    p.children[index] = newNode;
                }
                p = p.children[index];
            }
            p.isEndingChar = true;
        }
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abc", "xyz"};
        StreamChecker streamChecker = new StreamChecker(words);
        String param = "axyz";
        for (int i = 0; i < param.length(); i++) {
            System.out.println(streamChecker.query(param.charAt(i)));
        }
    }

}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 * <p>
 * 测试用例：
 * ["StreamChecker","query","query","query","query"]
 * [[["abc","xyz"]],["a"],["x"],["y"],["z"]]
 */