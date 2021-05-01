public class Demo {

    public static void main(String[] args){
        BinaryTree<String> tree = new BinaryTree<>("Root Node");
        BinaryTree<String> tree2 = new BinaryTree<>("2");
        BinaryTree<String> tree3 = new BinaryTree<>("3");
        BinaryTree<String> tree4 = new BinaryTree<>("4");
        BinaryTree<String> tree5 = new BinaryTree<>("5");
        BinaryTree<String> tree6 = new BinaryTree<>("6");
        BinaryTree<String> tree7 = new BinaryTree<>("7");

        tree2.addLeftChild(tree4);
        tree2.addRightChild(tree5);

        tree3.addLeftChild(tree6);
        tree3.addRightChild(tree7);

        tree.addLeftChild(tree2);
        tree.addRightChild(tree3);
        tree.iterator().forEachRemaining(System.out::println);
    }
}
