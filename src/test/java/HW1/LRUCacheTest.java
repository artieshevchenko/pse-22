package HW1;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {
    @Test
    public void simpleTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(2);

        cache.put(1, 1);
        Assert.assertEquals(1, cache.get(1).intValue());

        cache.put(2, 4);
        Assert.assertEquals(4, cache.get(2).intValue());
    }

    @Test
    public void smallestPossibleCapacityTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(1);

        cache.put(1, 1);
        Assert.assertEquals(1, cache.get(1).intValue());

        cache.put(2, 4);
        Assert.assertEquals(4, cache.get(2).intValue());
        Assert.assertNull(cache.get(1));

        cache.put(1, 10);
        Assert.assertEquals(10, cache.get(1).intValue());
        Assert.assertNull(cache.get(2));
    }

    @Test
    public void overwriteKeyTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(2);

        cache.put(1, 1);
        Assert.assertEquals(1, cache.get(1).intValue());

        cache.put(1, 2);
        Assert.assertEquals(2, cache.get(1).intValue());
    }

    @Test
    public void exceedCapacityTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(2);

        cache.put(1, 1);
        cache.put(2, 4);
        cache.put(3, 9);

        Assert.assertEquals(9, cache.get(3).intValue());
        Assert.assertNull(cache.get(1));
    }

    @Test
    public void leastRecentlyUsedAreRemovedTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(3);

        cache.put(1, 1);
        cache.put(2, 4);
        cache.put(3, 9);
        cache.get(1);
        cache.put(4, 16);

        Assert.assertNull(cache.get(2));

        cache.put(3, 90);
        cache.put(5, 25);

        Assert.assertNull(cache.get(1));
    }
}
