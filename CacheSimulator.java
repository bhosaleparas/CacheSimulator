import java.util.Scanner;

public class CacheSimulator {
    private Cache cache;
    private Scanner scanner;
    private boolean cacheInitialized;
    
    public CacheSimulator() {
        this.scanner = new Scanner(System.in);
        this.cacheInitialized = false;
    }
    
    public void start() {
        System.out.println("-------- CACHE MEMORY SIMULATOR --------");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    setCacheSize();
                    break;
                case 2:
                    choosePolicy();
                    break;
                case 3:
                    accessDataItem();
                    break;
                case 4:
                    displayCache();
                    break;
                case 5:
                    showStatistics();
                    break;
                case 6:
                    resetCache();
                    break;
                case 7:
                    System.out.println("Exiting Cache Memory Simulator. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private void displayMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("1. Set Cache Size");
        System.out.println("2. Choose Policy (LRU / LFU)");
        System.out.println("3. Access Data Item");
        System.out.println("4. Display Cache");
        System.out.println("5. Show Statistics (Hits, Misses, Hit Ratio)");
        System.out.println("6. Reset Cache");
        System.out.println("7. Exit");
        System.out.println("---------------------------------------------");
    }
    
    private void setCacheSize() {
        int size = getIntInput("Enter cache size: ");
        if (size <= 0) {
            System.out.println("Cache size must be positive!");
            return;
        }
        
        if (cache == null) {
            System.out.println("Cache size set to " + size + ". Now choose a policy.");
        } else {
            String policy = (cache instanceof LRUCache) ? "LRU" : "LFU";
            cache = (cache instanceof LRUCache) ? new LRUCache(size) : new LFUCache(size);
            System.out.println("Cache size updated to " + size + " with " + policy + " policy.");
        }
        cacheInitialized = true;
    }
    
    private void choosePolicy() {
        if (!cacheInitialized) {
            System.out.println("Please set cache size first!");
            return;
        }
        
        System.out.println("Choose cache policy:");
        System.out.println("1. LRU (Least Recently Used)");
        System.out.println("2. LFU (Least Frequently Used)");
        
        int choice = getIntInput("Enter your choice: ");
        int size = cache != null ? cache.getCapacity() : 3; // Bydefault size
        
        switch (choice) {
            case 1:
                cache = new LRUCache(size);
                System.out.println("LRU policy selected.");
                break;
            case 2:
                cache = new LFUCache(size);
                System.out.println("LFU policy selected.");
                break;
            default:
                System.out.println("Invalid choice! Using LRU as default.");
                cache = new LRUCache(size);
        }
    }
    
    private void accessDataItem() {
        if (!cacheInitialized || cache == null) {
            System.out.println("Please set cache size and choose policy first!");
            return;
        }
        
        System.out.print("Access item: ");
        String item = scanner.nextLine().trim().toUpperCase();
        
        if (!item.isEmpty()) {
            cache.accessData(item);
            System.out.println("Accessed item: " + item);
        }
    }
    
    private void displayCache() {
        if (!cacheInitialized || cache == null) {
            System.out.println("Cache not initialized!");
            return;
        }
        
        cache.display();
    }
    
    private void showStatistics() {
        if (!cacheInitialized || cache == null) {
            System.out.println("Cache not initialized!");
            return;
        }
        
        System.out.println("=== Cache Statistics ===");
        System.out.println("Hits: " + cache.getHits());
        System.out.println("Misses: " + cache.getMisses());
        System.out.printf("Hit Ratio: %.2f%%\n", cache.getHitRatio());
        System.out.println("Cache Size: " + cache.getSize() + "/" + cache.getCapacity());
    }
    
    private void resetCache() {
        if (!cacheInitialized || cache == null) {
            System.out.println("Cache not initialized!");
            return;
        }
        
        cache.reset();
        System.out.println("Cache has been reset.");
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
