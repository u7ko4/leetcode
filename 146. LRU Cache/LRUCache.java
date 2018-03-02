import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int count;
    private int capacity;
    private Map<Integer, CacheNode> cache = new HashMap<>();
    private CacheNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        this.head = new CacheNode();

        head = new CacheNode();
        head.previous = null;

        tail = new CacheNode();
        tail.next = null;

        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        CacheNode n = cache.get(key);
        if (n == null) {
            return -1;
        }
        moveToHead(n);
        return n.value;
    }

    public void put(int key, int value) {
        CacheNode n = cache.get(key);
        if (n == null) {
            CacheNode node = new CacheNode();
            node.key = key;
            node.value = value;

            cache.put(key, node);
            addNode(node);
            count++;

            if (count > capacity) {
                CacheNode tail = popTail();
                cache.remove(tail.key);
                count--;
            }
        } else {
            n.value = value;
            moveToHead(n);
        }
    }

    private void addNode(CacheNode node) {
        node.previous = head;
        node.next = head.next;

        head.next.previous = node;
        head.next = node;
    }

    private void removeNode(CacheNode node) {
        CacheNode previous = node.previous;
        CacheNode next = node.next;

        previous.next = next;
        next.previous = previous;
    }

    private void moveToHead(CacheNode node) {
        removeNode(node);
        addNode(node);
    }

    private CacheNode popTail() {
        CacheNode res = tail.previous;
        removeNode(res);
        return res;
    }

    static class CacheNode {
        int key;
        int value;
        CacheNode previous;
        CacheNode next;
    }
}
