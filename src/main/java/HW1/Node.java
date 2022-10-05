package HW1;

public class Node<T> {
    private T value;
    private Node<T> prev = null;

    private Node<T> next = null;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }
}