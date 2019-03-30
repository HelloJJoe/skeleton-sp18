package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /**
     * Return size of the buffer
     */
    public int capacity();

    /**
     * Return number of items currently in the buffer
     *
     */
    public int fillCount();

    /**
     * Add item x to the end
     */
    public void enqueue(T x);

    /**
     * Delete and return item from the front
     */
    public T dequeue();

    /**
     * Return (but do not delete) item from the front
     */
    public T peek();

    /**
     * Is the buffer empty (fillcount equals zero)?
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    };

    /**
     * Is the buffer full (fillCount is same as capacity)?
     */
    default boolean isFull()  {
        return fillCount() == capacity();
    }

    public Iterator<T> iterator();
}
