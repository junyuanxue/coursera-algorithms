import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

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
        if (size == queue.length) resizeQueue(true);
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
        if (queue.length > 4 && size <= queue.length / 4) resizeQueue(false);

        return dequeuedItem;
    }

    private void resizeQueue(boolean isDouble) {
        int currentSize = queue.length;
        int newSize = isDouble ? currentSize * 2 : currentSize / 2;
        int numOfItemsToCopy = isDouble ? currentSize : newSize;
        Item[] resizedQueue = (Item[]) new Object[newSize];
        System.arraycopy(queue, 0, resizedQueue, 0, numOfItemsToCopy);
        queue = resizedQueue;
    }

    private int getRandomIndex() {
        while (true) {
            int randomIndex = StdRandom.uniform(size);
            if (queue[randomIndex] != null) return randomIndex;
        }
    }

    public Item sample() { // return (but do not remove) a random item
        return queue[getRandomIndex()];
    }

    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new QueueIterator(queue, size);
    }

    private class QueueIterator implements Iterator<Item> {
        private Item[] iteratorQueue;
        private int iteratorIndex = 0;

        public QueueIterator(Item[] queue, int size) {
            iteratorQueue = (Item[]) new Object[size];
            // copy items into iterator queue
            System.arraycopy(queue, 0, iteratorQueue, 0, iteratorQueue.length);
            //shuffle iterator queue
            for (int i = 1; i < iteratorQueue.length; i++) {
                int swapIndex = StdRandom.uniform(i + 1);
                Item tempItem = iteratorQueue[i];
                iteratorQueue[i] = iteratorQueue[swapIndex];
                iteratorQueue[swapIndex] = tempItem;
            }
        }

        @Override
        public boolean hasNext() {
            return (iteratorIndex < iteratorQueue.length);
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = iteratorQueue[iteratorIndex];
            iteratorIndex++;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Remove method not supported");
        }
    }
}