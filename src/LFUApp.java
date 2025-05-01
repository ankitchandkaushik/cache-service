public class LFUApp {
    public static void main(String[] args) {
        ICache lfuCache = new LFUCache(2);
        lfuCache.put(1,2);
        lfuCache.put(2,3);
        System.out.println(lfuCache.get(1));
        lfuCache.put(4, 8);
        System.out.println(lfuCache.get(2));
        lfuCache.put(88, 88);
        lfuCache.put(34, 89);
        lfuCache.put(45, 83);
        lfuCache.put(445, 80);
        // System.out.println(lfuCache.map.size());

        System.out.println(lfuCache.get(445));
        System.out.println(lfuCache.get(34));


    }
}
