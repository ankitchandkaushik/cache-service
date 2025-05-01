public class App {
    public static void main(String[] args) throws Exception {
        LRUCache lfuCache = new LRUCache(2);
        lfuCache.put(1,2);
        lfuCache.put(2,3);
        System.out.println(lfuCache.get(1));
        lfuCache.put(4, 8);
        System.out.println(lfuCache.get(2));
        lfuCache.put(88, 8);
        lfuCache.put(34, 8);
        lfuCache.put(45, 8);
        lfuCache.put(445, 8);
        System.out.println(lfuCache.map.size());

        System.out.println(lfuCache.get(2));

    }
}
