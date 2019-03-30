package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        assertFalse(arb.isEmpty());
        assertEquals(1, (int) arb.dequeue());
        assertFalse(arb.isFull());
        assertEquals(2, (int) arb.dequeue());
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        assertTrue(arb.isFull());


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
//        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);

        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);


        Iterator<Integer> arbi = arb.iterator();
//
//        while (arbi.hasNext()) {
//            System.out.println(arbi.next());
//        }

        for (int number : arb) {
            System.out.println(number);
        }

    }
} 
