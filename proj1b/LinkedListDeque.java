public class LinkedListDeque<T> implements Deque<T> {

    public class StuffNode{

        private T item;
        private StuffNode next;
        private StuffNode prev;

        public StuffNode(T item, StuffNode next, StuffNode prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new StuffNode(null, null, null);
        StuffNode first = new StuffNode(item, sentinel.next, sentinel);
        sentinel.next = first;
        sentinel.prev = first;
        size = 1;
    }

    @Override
    public void addFirst(T item) {
        // sentinel.next = first item
        StuffNode first = new StuffNode(item, sentinel.next, sentinel);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        StuffNode last = new StuffNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode sent = sentinel;
        sent = sent.next;
        while (sent != sentinel) {
            System.out.print(sent.item + " ");
            sent = sent.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == null || sentinel.next == sentinel) {
            return null;
        }
        StuffNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == null || sentinel.next == sentinel) {
            return null;
        }
        StuffNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size -= 1;
        return last.item;
    }

    @Override
    public T get(int index) {
        int counter = 0;
        StuffNode L = sentinel.next;
        if (index > size - 1) {
            return null;
        }
        while (counter < index) {
            L = L.next;
            counter++;
        }
        return L.item;
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);

    }

    private T getRecursive(StuffNode stuffnode, int index) {
        if (index == 0) {
            return stuffnode.item;
        }
        return getRecursive(stuffnode.next, (index - 1));
    }

}