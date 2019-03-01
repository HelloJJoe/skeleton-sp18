import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {
    @Test
    public void random() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        String msg = "";
        Integer actual;
        Integer expect;

        for (int i = 0; i < 1000; i += 1) {
            Integer numberBetweenZeroAndOne = StdRandom.uniform(1,10);

            switch (numberBetweenZeroAndOne) {
                case 1:
                    stu.addFirst(i);
                    sol.addFirst(i);
                    msg += "addFirst(" + i + ")\n";
                    break;

                case 2:
                    stu.addFirst(i);
                    sol.addFirst(i);
                    msg += "addLast(" + i + ")\n";
                    break;

                case 3:
                    if (stu.size() == 0 || sol.size() == 0) {
                        break;
                    }
                    actual = stu.removeFirst();
                    expect = sol.removeFirst();
                    msg += "removeFirst(" + i + ")\n";
                    assertEquals(msg,expect, actual);

                    break;

                case 4:
                    if (stu.size() == 0 || sol.size() == 0) {
                        break;
                    }
                    actual = stu.removeLast();
                    expect = sol.removeLast();
                    msg += "removeLast(" + i + ")\n";
                    assertEquals(msg,expect, actual);

                    break;

            }
        }


    }
}
