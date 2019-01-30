import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testRandom1() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 200; i++) {
            A.addLast(i);
        }
        assertEquals(0, (Object) A.get(0));

    }
    @Test
    public void testRandom2() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 200; i++) {
            A.addFirst(i);
        }
        assertEquals(199, (Object) A.get(0));

    }
    @Test
    public void testRandom3() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 200; i++) {
            A.addFirst(i);
        }
        for (int i = 0; i < 100; i++) {
            A.addLast(i);
        }
        assertEquals(199, (Object) A.get(0));

    }
    @Test
    public void testRandom4() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addLast(0);
        A.addLast(1);
        A.addLast(2);
        A.addFirst(3);
        A.addLast(4);
        A.get(0);
        A.removeFirst();
        A.addFirst(7);
        A.addLast(8);
        A.addLast(9);
        A.get(2);
        A.addLast(11);
        A.addLast(12);
        A.addLast(13);
        A.removeFirst();
        A.addLast(15);
        A.addFirst(16);
        A.addFirst(17);
        A.removeLast();
        A.get(7);
        assertEquals(9, (Object) A.get(7));
    }
    @Test
    public void testResize() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            A.addFirst(i);
        }
        for (int i = 0; i < 14; i++) {
            A.removeFirst();
        }
        assertEquals(1, (Object) A.get(0));

    }

    @Test
    public void testRandom5() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(0);
        A.removeFirst();
        A.addFirst(2);
        assertEquals(2, (Object) A.removeFirst());
    }

    @Test
    public void testRandom6() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(0);
        A.removeLast();
        A.addFirst(2);
        assertEquals(2, (Object) A.removeLast());
    }

    @Test
    public void testRandom7() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(0);
        A.removeFirst();
        A.addFirst(2);
        assertEquals(2, (Object) A.removeFirst());
    }

    @Test
    public void testRandom8() {
        ArrayDeque<Integer> A = new ArrayDeque<>();
        A.addFirst(0);
        A.removeFirst();
        A.addFirst(2);
        A.addLast(3);
        A.removeLast();
        assertEquals(2, (Object) A.get(0));
    }


}