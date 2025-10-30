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

