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
    
    public void addFirst(Item item)          // add the item to the front
    public void addLast(Item item)           // add the item to the end
    public Item removeFirst()                // remove and return the item from the front
    public Item removeLast()                 // remove and return the item from the end
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing (optional)
}
