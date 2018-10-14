public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer, Integer> tree = new BinaryTree<>();
        tree.set(12, 12);
        System.out.println(tree);
        tree.set(42, 1);
        System.out.println(tree);
        tree.set(23, 0);
        System.out.println(tree);
        System.out.println(tree.get(12));
        System.out.println(tree.get(23));
        System.out.println(tree.get(42));
    }
}
