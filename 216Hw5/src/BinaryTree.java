import java.util.*;

public class BinaryTree<T> implements Iterable<T> {

    T data;
    BinaryTree<T> left;
    BinaryTree<T> right;

    public BinaryTree(){
        this.left = null;
        this.right = null;
    }

    public BinaryTree(T data){
        this.data = data;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator(this);
    }

    private class BinaryTreeIterator implements Iterator {

        private Queue<BinaryTree<T>> queue = new LinkedList<>();

        public BinaryTreeIterator(BinaryTree<T> tree){
            if(tree.data != null){
                queue.add(tree);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() throws NoSuchElementException {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            BinaryTree<T> tree = queue.poll();
            if(tree.left != null){
                queue.add(tree.left);
            }
            if(tree.right != null){
                queue.add(tree.right);
            }
            return tree.data;
        }
    }

    public void addLeftChild(BinaryTree<T> left){
        this.left = left;
    }

    public void addRightChild(BinaryTree<T> right){
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
