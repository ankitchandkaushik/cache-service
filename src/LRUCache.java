import java.util.HashMap;

public class LRUCache implements ICache {

    NodeList list;
    int capacity;
    HashMap<Integer, Node> map ;

    public LRUCache(int capacity) {
        this.list = new NodeList();
        this.capacity = capacity;
        this.map = new HashMap<>();
    }


    @Override
    public int get(int key) {
        if(!map.containsKey(key)) {
            System.out.println("Key " + key + " does not exists in cache");
            return -1;
        }
        Node requestedNode = map.get(key);
        removeNode(requestedNode);
        addToFront(requestedNode);
        return requestedNode.getValue();
    }

    @Override
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            System.out.println("Key " + key + " already exists");
            Node requestedNode = map.get(key);
            requestedNode.setValue(value);
            map.put(key, requestedNode);
            removeNode(requestedNode);
            addToFront(requestedNode);
        } else {
            if(map.size() == capacity) {
                // assuming capacity > 0
                System.out.println("removing key " + list.getEnd().getKey());
                map.remove(list.getEnd().getKey());
                removeNode(list.getEnd());
            } 
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToFront(newNode);
            
        }
    }

public void removeNode(Node node) {
        if(node == null) {
            return;
        }
        if(node == list.getEnd()) {
            list.setEnd(node.left); 
        }
        node.getLeft().setRight(node.getRight());
        if(node.getRight() != null) {
            node.getRight().setLeft(node.getLeft());
        }
        node.setLeft(null);
        node.setRight(null);
    }

    private void addToFront(Node node) {
        node.setRight(list.getHead().getRight());
        node.setLeft(list.getHead());
    
        if (list.getHead().getRight() != null) {
            list.getHead().getRight().setLeft(node);
        } else {
            list.setEnd(node); // first real node being added
        }
        list.getHead().setRight(node);

    }

    public class NodeList {
        Node head;
        Node end;
        public NodeList() {
            this.head = new Node(0 , 0);
            this.end = head;
        }
        public Node getHead() {
            return head;
        }
        public Node getEnd() {
            return end;
        }
        public void setEnd(Node end) {
            this.end = end;
        }


        
    }

    class Node {
        int key;
        int value;
        Node left;
        Node right;

        public Node (int key, int value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        
    }
    
}
