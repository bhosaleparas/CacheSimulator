import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashSet;

public class LFUCache implements Cache {
    private final int capacity;
    private final Map<String, Integer> keyToFreq;
    private final Map<String, String> cache;
    private final Map<Integer, LinkedHashSet<String>> freqToKeys;
    private int minFrequency;
    private int hits;
    private int misses;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyToFreq = new HashMap<>();
        this.cache = new HashMap<>();
        this.freqToKeys = new HashMap<>();
        this.minFrequency = 0;
        this.hits = 0;
        this.misses = 0;
    }
    
    @Override
    public void accessData(String data) {
        if (cache.containsKey(data)) {
            int oldFreq = keyToFreq.get(data);
            int newFreq = oldFreq + 1;
            
            freqToKeys.get(oldFreq).remove(data);
            if (freqToKeys.get(oldFreq).isEmpty()) {
                freqToKeys.remove(oldFreq);
                if (minFrequency == oldFreq) {
                    minFrequency++;
                }
            }
            
            keyToFreq.put(data, newFreq);
            freqToKeys.computeIfAbsent(newFreq, k -> new LinkedHashSet<>()).add(data);
            hits++;
        } else {
            // Cache miss
            misses++;
            if (cache.size() >= capacity) {
                LinkedHashSet<String> minFreqSet = freqToKeys.get(minFrequency);
                String lfuKey = minFreqSet.iterator().next();
                minFreqSet.remove(lfuKey);
                if (minFreqSet.isEmpty()) {
                    freqToKeys.remove(minFrequency);
                }
                cache.remove(lfuKey);
                keyToFreq.remove(lfuKey);
            }
            
            // Add new item
            cache.put(data, data);
            keyToFreq.put(data, 1);
            freqToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(data);
            minFrequency = 1;
        }
    }
    
    @Override
    public boolean contains(String data) {
        return cache.containsKey(data);
    }
    
    @Override
    public void display() {
        if (cache.isEmpty()) {
            System.out.println("Cache: []");
            return;
        }
        
        // show by (highest to lowest)
        TreeMap<Integer, LinkedHashSet<String>> sortedFreq = new TreeMap<>((a, b) -> b - a);
        sortedFreq.putAll(freqToKeys);
        
        System.out.print("Cache: [");
        boolean first = true;
        for (Map.Entry<Integer, LinkedHashSet<String>> entry : sortedFreq.entrySet()) {
            for (String key : entry.getValue()) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(key + "(" + entry.getKey() + ")");
                first = false;
            }
        }
        System.out.println("]");
    }
    
    @Override
    public void reset() {
        cache.clear();
        keyToFreq.clear();
        freqToKeys.clear();
        minFrequency = 0;
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
        return cache.size();
    }
    
    @Override
    public int getCapacity() {
        return capacity;
    }
}