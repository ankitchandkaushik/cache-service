



TODOS
1. Implement LRU, LFU caches (key, value) -> (int, int) - done
2. Make it generic
3. Implement logic to select cache types
4. Make it thread safe



Contract
- get(key) - value if present or null/some identifier
- put(key, value) - return nothing, evict key if cache is full

building a cache
- input -> size
