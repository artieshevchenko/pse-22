package HW1;

public class DoublyLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public Node<T> addToFront(T entry) {
        Node<T> node = new Node<T>(entry);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }

        size++;
        return node;
    }

    public void moveToFront(Node<T> node) {
        if (node == head) {
            return;
        }

        if (node == tail) {
            Node<T> prev = tail.getPrev();
            prev.setNext(null);
            head.setPrev(tail);
            tail.setNext(head);
            tail.setPrev(null);
            head = tail;
            tail = prev;
            return;
        }

        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        head.setPrev(node);
        node.setPrev(null);
        node.setNext(head);
        head = node;
    }

    public Node<T> replaceItemAndMoveToFront(Node<T> node, T newValue) {
        if (node == head) {
            Node<T> next = head.getNext();
            head = new Node<T>(newValue);
            head.setNext(next);
            return head;
        }

        if (node == tail) {
            Node<T> prev = tail.getPrev();
            prev.setNext(null);
            tail = prev;
            Node<T> newHead = new Node<>(newValue);
            head.setPrev(newHead);
            newHead.setNext(head);
            head = newHead;
            return head;
        }

        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        Node<T> newHead = new Node<T>(newValue);
        prev.setNext(next);
        next.setPrev(prev);
        head.setPrev(newHead);
        newHead.setNext(head);
        head = newHead;
        return head;
    }

    public T deleteLast() {
        assert !isEmpty() : "The list is empty: cannot delete value";

        T deletedValue;

        if (head == tail) {
            deletedValue = head.getValue();
            head = null;
            tail = null;
        } else {
            deletedValue = tail.getValue();
            Node<T> prev = tail.getPrev();
            prev.setNext(null);
            tail = prev;
        }

        size--;
        return deletedValue;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
