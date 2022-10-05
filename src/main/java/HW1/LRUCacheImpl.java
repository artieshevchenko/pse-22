package HW1;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheImpl<K, V> extends LRUCache<K, V> {
    private final DoublyLinkedList<Entry<K, V>> prioritizedValues = new DoublyLinkedList<>();
    private final Map<K, Node<Entry<K, V>>> keys = new HashMap<>();

    public LRUCacheImpl(int capacity) {
        super(capacity);
    }

    @Override
    protected V doGet(K key) {
        var node = keys.get(key);
        if (node == null) {
            return null;
        }

        prioritizedValues.moveToFront(node);
        return node.getValue().getValue();
    }

    @Override
    protected void doPut(K key, V value) {
        var node = keys.get(key);

        if (node == null) {
            var newNode = prioritizedValues.addToFront(new Entry<>(key, value));
            if (prioritizedValues.getSize() > capacity) {
                var deleted = prioritizedValues.deleteLast();
                keys.remove(deleted.getKey());
            }

            keys.put(key, newNode);
            return;
        }

        var newNode = prioritizedValues
                .replaceItemAndMoveToFront(node, new Entry<>(key, value));
        keys.put(key, newNode);
    }
}
