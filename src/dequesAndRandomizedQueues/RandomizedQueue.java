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

    private void doubleQueueSize () {
        private int currentSize = queue.length
        Item[] resizedQueue = (Item[]) new Object[currentSize * 2];
        System.arraycopy(queue, 0, resizedQueue, 0, currentSize);
        queue = resizedQueue;
    }

    public Item dequeue()                    // remove and return a random item
    public Item sample()                     // return (but do not remove) a random item
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    public static void main(String[] args)   // unit testing (optional)
}