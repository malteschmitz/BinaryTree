public class BinaryTree<K extends Comparable<K>,V> {
    private Node<K, V> root = new Empty<>();

    public void set(K key, V value) {
        root = root.set(key, value);
    }

    public V get(K key) {
        return root.get(key);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

interface Node<K extends Comparable<K>, V> {
    Node<K,V> set(K key, V value);
    V get(K key);
}

class Empty<K extends Comparable<K>, V> implements Node<K,V> {
    @Override
    public Node<K,V> set(K key, V value) {
        return new Leave<>(key, value);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public String toString() {
        return "<>";
    }
}

abstract class DataNode<K extends Comparable<K>, V> implements Node<K,V> {
    protected K key;
    protected V value;

    protected abstract Node<K,V> insertLeft(K key, V value);
    protected abstract Node<K,V> insertRight(K key, V value);

    protected abstract V findLeft(K key);
    protected abstract V findRight(K key);

    @Override
    public Node<K, V> set(K key, V value) {
        if (key == this.key) {
            this.value = value;
            return this;
        } else if (key.compareTo(this.key) < 0) {
            return insertLeft(key, value);
        } else {
            return insertRight(key, value);
        }
    }

    @Override
    public V get(K key) {
        if (key == this.key) {
            return this.value;
        } else if (key.compareTo(this.key) < 0) {
            return findLeft(key);
        } else {
            return findRight(key);
        }
    }
}

class Leave<K extends Comparable<K>, V> extends DataNode<K,V> {

    public Leave(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    protected Node<K, V> insertLeft(K key, V value) {
        return new InnerNode<>(this.key, this.value, new Leave<>(key, value), new Empty<>());
    }

    @Override
    protected Node<K, V> insertRight(K key, V value) {
        return new InnerNode<>(this.key, this.value, new Empty<>(), new Leave<>(key, value));
    }

    @Override
    protected V findLeft(K key) {
        return null;
    }

    @Override
    protected V findRight(K key) {
        return null;
    }

    @Override
    public String toString() {
        return "<" + key + ": " + value + ">";
    }
}

class InnerNode<K extends Comparable<K>, V> extends DataNode<K, V> {
    public InnerNode(K key, V value, Node<K,V> left, Node<K,V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    private Node<K, V> left;
    private Node<K, V> right;

    @Override
    protected Node<K, V> insertLeft(K key, V value) {
        left = left.set(key, value);
        return this;
    }

    @Override
    protected Node<K, V> insertRight(K key, V value) {
        right = right.set(key, value);
        return this;
    }

    @Override
    protected V findLeft(K key) {
        return left.get(key);
    }

    @Override
    protected V findRight(K key) {
        return right.get(key);
    }

    @Override
    public String toString() {
        return "<" + left + ", " + key + ": " + value + ", " + right + ">";
    }
}