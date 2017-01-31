import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numOfItems;

    /**
     * Private inner node class
     * Has next and prev pointers to implement constant worst time operations
     */
    private class Node {
      private Item item;
      private Node next;
      private Node prev;

      Node(Item item) {
        this.item = item;
        this.next = null;
        this.prev = null;
      }
    }

    public Deque() { // constructs an empty deque

    }
    public boolean isEmpty()                 // is the deque empty?
    public int size()                        // return the number of items on the deque
    public void addFirst(Item item)          // add the item to the front
    public void addLast(Item item)           // add the item to the end
    public Item removeFirst()                // remove and return the item from the front
    public Item removeLast()                 // remove and return the item from the end
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    public static void main(String[] args)   // unit testing (optional)
}
