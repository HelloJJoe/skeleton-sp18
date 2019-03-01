import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {
    @Test
    public void random() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            Double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                stu.addFirst(i);
                sol.addFirst(i);
                System.out.println("addFirst(" + i + ')' );
            } else {
                stu.addLast(i);
                sol.addLast(i);
                System.out.println("addLast(" + i + ')' );
            }

        }
        for (int i = 0; i < 5; i += 1) {
            Double numberBetweenZeroAndOne = StdRandom.uniform();

            Integer actual;
            Integer expect;
            if (numberBetweenZeroAndOne < 0) {
                actual = stu.removeFirst();
                expect = sol.removeFirst();
                System.out.println("removeFirst(" + i + ')' );

            } else {
                actual = stu.removeLast();
                expect = sol.removeLast();
                System.out.println("removeLast(" + i + ')' );

            }
            assertEquals("Oh noooo! This is bad:\n   Actual number " + actual
                            + " not equal to " + expect + "!",
                    expect, actual);
        }


    }
}
