import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    /**
     * Private inner node class
     * Has next and prev pointers to implement constant worst time operations
     */
    private class Node {
        public Item value = null;
        public Node next = null;
        public Node prev = null;
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() { // constructs an empty deque
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() { // is the deque empty?
        return size === 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null) throw new java.lang.NullPointerException();

        Node currentNode = new Node();
        currentNode.value = item;
        currentNode.next = first;
        if (first != null) first.prev = currentNode; // setting the relationship between the first node and currentNode

        first = currentNode; // currentNode becomes the first node
        if (last == null) last = first; // when currentNode gets inserted into an empty array
        size++;
    }

    public void addLast(Item item) { // add the item to the end
        if (item == null) throw new java.lang.NullPointerException();

        Node currentNode = new Node();
        currentNode.value = item;
        currentNode.prev = last;
        if (last != null) last.next = currentNode;

        last = currentNode;
        if (first == null) first = last;
        size++;
    }

    public Item removeFirst() { // remove and return the item from the front
        if (size == 0) throw new java.util.NoSuchElementException();

        Item firstItem = first.value;

        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return firstItem;
    }
    
    public Item removeLast() { // remove and return the item from the end

    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing (optional)
}
