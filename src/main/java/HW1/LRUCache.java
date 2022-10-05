package HW1;

public abstract class LRUCache<K, V> {
    protected final int capacity;

    public LRUCache(int capacity) {
        assert capacity > 0;

        this.capacity = capacity;
    }

    public V get(K key) {
        assert key != null;

        return doGet(key);
    }

    public void put(K key, V value) {
        assert key != null;

        doPut(key, value);
    }

    protected abstract V doGet(K key);
    protected abstract void doPut(K key, V value);

}
