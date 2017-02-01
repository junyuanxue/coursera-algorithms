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
        return size == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null) throw new java.lang.NullPointerException();

        Node currentNode = new Node();
        currentNode.value = item;
        currentNode.next = first;
        if (first != null) first.prev = currentNode; // connecting the first node and currentNode

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
        if (size == 0) throw new java.util.NoSuchElementException();

        Item lastItem = last.value;

        if (size == 1) {
            last = null;
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return lastItem;
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = currentNode.value;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
}
