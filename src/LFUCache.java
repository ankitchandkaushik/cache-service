import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeMap;

public class LFUCache implements ICache{

    HashMap<Integer, Node> keyNodeMap;
    TreeMap<Integer, LinkedHashSet<Node>> frequencyMap;
    int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyNodeMap = new HashMap<>();
        this.frequencyMap = new TreeMap<>();
    }

    @Override
    public int get(int key) {
        if(!keyNodeMap.containsKey(key)) {
            System.out.println("Key not available in map " + key);
            return -1;
        } else {
            Node requiredNode = keyNodeMap.get(key);
            addFrequency(requiredNode);
            return requiredNode.getValue();
            
        }
        
    }

    @Override
    public void put(int key, int value) {
        if(keyNodeMap.containsKey(key)) {
            Node requiredNode = keyNodeMap.get(key);
            requiredNode.setValue(value);
            addFrequency(requiredNode);
            return;
        } else {
            if(keyNodeMap.size() == capacity) {
                removeNode();
            }
            Node newNode = new Node(key, value);
            keyNodeMap.put(key, newNode);
            frequencyMap.computeIfAbsent(newNode.getFrequency(), k -> new LinkedHashSet<>()).add(newNode);
        }
    }

    public void addFrequency(Node requiredNode) {
        frequencyMap.get(requiredNode.getFrequency()).remove(requiredNode);
        requiredNode.setFrequency(requiredNode.getFrequency()+1);
        frequencyMap.computeIfAbsent(requiredNode.getFrequency(), k -> new LinkedHashSet<>()).add(requiredNode);
        if(frequencyMap.get(requiredNode.getFrequency()-1).size() == 0) {
            frequencyMap.remove(requiredNode.getFrequency()-1);
        }
        
    }

    public void removeNode() {
        int lowestFreq = frequencyMap.firstKey();
        Iterator<Node> iterator = frequencyMap.get(lowestFreq).iterator();
        int removedKey = -1;
        if (iterator.hasNext()) {
            removedKey = iterator.next().getKey();
            iterator.remove();
        }
        if(frequencyMap.get(lowestFreq).size() == 0) {
            frequencyMap.remove(lowestFreq);
        }
        keyNodeMap.remove(removedKey);
        
    }

    class Node implements Comparable<Node> {
        int key;
        int value;
        int frequency;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(LFUCache.Node  o) {
            Node obj = (Node) o;
            return Integer.compare(this.key, obj.getKey());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + key;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (key != other.key)
                return false;
            return true;
        }

        private LFUCache getEnclosingInstance() {
            return LFUCache.this;
        } 
        
    }
    
}
