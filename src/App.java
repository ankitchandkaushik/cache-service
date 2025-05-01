public class App {
    public static void main(String[] args) throws Exception {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,2);
        lruCache.put(2,3);
        System.out.println(lruCache.get(1));
        lruCache.put(4, 8);
        System.out.println(lruCache.get(2));
        lruCache.put(88, 8);
        lruCache.put(34, 8);
        lruCache.put(45, 8);
        lruCache.put(445, 8);
        System.out.println(lruCache.map.size());

        System.out.println(lruCache.get(2));

    }
}
