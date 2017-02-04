import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() { // construct an empty randomized queue
        queue = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() { // is the queue empty?
        return size == 0;
    }

    public int size() { // return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) { // add the item
        if (item == null) throw new java.lang.NullPointerException();
        if (size == queue.length) doubleQueueSize();
        queue[size] = item;
        size++;
    }

    public Item dequeue() { // remove and return a random item
        if (size == 0) throw new java.util.NoSuchElementException("Queue is currently empty.");

        int index = getRandomIndex();
        Item dequeuedItem = queue[index];
        size--;

        queue[index] = queue[size]; // fill in the empty index
        queue[size] = null;

        if (queue.length > 4 && size <= queue.length / 4) halfQueueSize();
    }

    private void doubleQueueSize () {
        private int currentSize = queue.length
        Item[] resizedQueue = (Item[]) new Object[currentSize * 2];
        System.arraycopy(queue, 0, resizedQueue, 0, currentSize);
        queue = resizedQueue;
    }

    private void halfQueueSize () {
        
    }

    private int getRandomIndex () {
        while (true) {
            int randomIndex = StdRandom.uniform(size);
            if (queue[randomIndex] != null) return randomIndex;
        }
    }

    public Item sample()                     // return (but do not remove) a random item
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    public static void main(String[] args)   // unit testing (optional)
}