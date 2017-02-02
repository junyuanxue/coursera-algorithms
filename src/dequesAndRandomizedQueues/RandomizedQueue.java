public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() { // construct an empty randomized queue
        queue = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty()                 // is the queue empty?
    public int size()                        // return the number of items on the queue
    public void enqueue(Item item)           // add the item
    public Item dequeue()                    // remove and return a random item
    public Item sample()                     // return (but do not remove) a random item
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    public static void main(String[] args)   // unit testing (optional)
}