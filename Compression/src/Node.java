public class Node {
    public int a;
    public Character c;
    public Node left;
    public Node right;

    Node(Node o1, Node o2) {
        left = o1;
        right = o2;
        a = o1.a + o2.a;
    }

    Node() {

    }

}
