# 🧮 Cache Memory Simulator

A **Java-based Cache Simulator** that simulates different cache replacement policies through an **interactive command-line interface (CLI)**.  
Understand how CPU caches, browser caching, and memory management work through

---

## 🚀 Features

- **Multiple Cache Policies:** Supports both **LRU (Least Recently Used)** and **LFU (Least Frequently Used)** algorithms.  
- **Interactive CLI:** Menu-driven interface for smooth user interaction.  
- **Real-time Visualization:** Observe cache state updates after each operation.  
- **Performance Metrics:** Tracks **cache hits**, **misses**, and **hit ratio**.  

---

## 🧠 Cache Policies Implemented

### 🔹 LRU (Least Recently Used)
- Evicts the least recently accessed item.  
- Uses **HashMap + Doubly Linked List** for `O(1)` operations.  
- Ideal for **temporal locality** access patterns.

### 🔹 LFU (Least Frequently Used)
- Evicts the least frequently accessed item.  
- Uses **multiple HashMaps** for efficient frequency tracking.  
- Great for identifying **popular items** in memory.

---

## 🛠️ Installation & Usage

### ✅ Prerequisites
- Java **8 or higher**
- Command-line terminal

### ▶️ Running the Simulator

### 1. Clone the Repository
```bash
https://github.com/bhosaleparas/CacheSimulator.git
cd CacheSimulator

```
### 2. Compile all Java files
```bash
javac *.java


```
### 3.Run the application
```bash
java Main

```
### 4. Follow the interactive menu to configure and test cache behavior.


### 5.🎮 Sample Session
```bash
========== CACHE MEMORY SIMULATOR ==========
1. Set Cache Size
2. Choose Policy (LRU / LFU)
3. Access Data Item
4. Display Cache
5. Show Statistics
6. Reset Cache
7. Exit
============================================
Enter your choice: 1
Enter cache size: 3

Enter your choice: 2
Choose cache policy:
1. LRU (Least Recently Used)
2. LFU (Least Frequently Used)
Enter your choice: 1
LRU policy selected.

Enter your choice: 3
Access item: A
Accessed item: A

Enter your choice: 3
Access item: B
Accessed item: B

Enter your choice: 4
Cache: [B, A]

Enter your choice: 5
=== Cache Statistics ===
Hits: 0
Misses: 2
Hit Ratio: 0.00%
Cache Size: 2/3



