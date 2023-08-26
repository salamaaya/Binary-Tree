package BinaryTrees;

public class BSTree<Key extends Comparable<Key>, Value>{ //comparable is an interface, not a generic which helps comparabe values
    private Node root;
    public class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size = 0;
        public Node(){
            key = null;
            value = null;
            right = null;
            left = null;
            size = 0;
        }
        public Node(Key key, Value value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
            left = null;
            right = null;
        }
    } //Node class ends

    public Node inOrder(){
        return inOrder(root);
    }

    private Node inOrder(Node search){
        if(search != null){
            inOrder(search.left);
            System.out.println(search.key);
            inOrder(search.right);
        }
        return search;
    }

    public Node search(Key key){
        return search(key, root);
    }

    private Node search(Key key, Node x){
        if(key == null)
            throw new IllegalArgumentException("Searched item is null!");
        if(x == null || key.compareTo(x.key) == 0)
            return x;
        int compare = key.compareTo(x.key);
        if(compare < 0)
            return search(key, x.left);
        if(compare > 0)
            return search(key, x.right);
        return x;
    }

    public Node preOrder(){
        return preOrder(root);
    }

    private Node preOrder(Node search){
        if(search != null){
            System.out.println(search.key);
            preOrder(search.left);
            preOrder(search.right);
        }
        return search;
    }

    public void insert(Key key, Value value){
        if(key == null || value == null)
            throw new IllegalArgumentException("Insert: key/value is null");
        root = insert(root, key, value);
    }

    private Node insert(Node x, Key key, Value value){
        if(x == null)
            x = new Node(key, value, 1);
        int compare = key.compareTo(x.key);
        if(compare < 0)
            x.left = insert(x.left, key, value);
        if(compare > 0)
            x.right = insert(x.right, key, value);
        x.size = 1 + size(x.left) + size(x.right); //updates size for all value in the path
        return x;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.size;
    }

    public Node successor(Key key){
        if(key == null)
            throw new IllegalArgumentException("Successor: Key is null.");
        return successor(root, key);
    }

    private Node successor(Node node, Key key){
        Node x = search(key);
        if(x.right != null) return min(x.right);
        Node successor = null;
        while(node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                successor = node;
                node = node.left;
            } else if (compare > 0)
                node = node.right;
            else break;
        }
        return successor;
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key){
        if(key == null)
            throw new IllegalArgumentException("key is null");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if(x == null)
            return null;
        int compare = key.compareTo(x.key);
        if(compare < 0)
            x.left = delete(x.left, key);
        else if(compare > 0)
            x.right = delete(x.right, key);
        else{
            if(x.right == null)
                return x.left;
            else if(x.left == null)
                return x.right;
            Node temp = x;
            x = min(x.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }
}
