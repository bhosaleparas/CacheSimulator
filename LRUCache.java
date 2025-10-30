import java.util.HashMap;
import java.util.Map;

public class LRUCache implements Cache {
    private class Node {
        String data;
        Node prev;
        Node next;
        
        Node(String data) {
            this.data = data;
        }
    }
    
    private final int capacity;
    private final Map<String, Node> cacheMap;
    private Node head;
    private Node tail;
    private int hits;
    private int misses;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.hits = 0;
        this.misses = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }
    
    @Override
    public void accessData(String data) {
        if (cacheMap.containsKey(data)) {
            Node node = cacheMap.get(data);
            removeNode(node);
            addToFront(node);
            hits++;
        } else {
            // Cache miss
            misses++;
            if (cacheMap.size() >= capacity) {
                // Remove  item
                Node lru = tail.prev;
                removeNode(lru);
                cacheMap.remove(lru.data);
            }
            
            // Add new item
            Node newNode = new Node(data);
            addToFront(newNode);
            cacheMap.put(data, newNode);
        }
    }
    
    @Override
    public boolean contains(String data) {
        return cacheMap.containsKey(data);
    }
    
    @Override
    public void display() {
        if (cacheMap.isEmpty()) {
            System.out.println("Cache: []");
            return;
        }
        
        Node current = head.next;
        System.out.print("Cache: [");
        while (current != tail) {
            System.out.print(current.data);
            if (current.next != tail) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }
    
    @Override
    public void reset() {
        cacheMap.clear();
        head.next = tail;
        tail.prev = head;
        hits = 0;
        misses = 0;
    }
    
    @Override
    public int getHits() {
        return hits;
    }
    
    @Override
    public int getMisses() {
        return misses;
    }
    
    @Override
    public double getHitRatio() {
        int total = hits + misses;
        return total == 0 ? 0.0 : (double) hits / total * 100;
    }
    
    @Override
    public int getSize() {
        return cacheMap.size();
    }
    
    @Override
    public int getCapacity() {
        return capacity;
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}