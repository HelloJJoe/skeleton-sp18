import org.junit.Test;
import static org.junit.Assert.*;


/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(int a, int b) {
        return a == b;
    }

    @Test
    public void testIsSameNumber(){
        Integer a = 128;
        Integer b = 128;
        assertTrue(a == b);

        Integer x = 127;
        Integer y = 127;
        assertTrue(x == y);
    }


}
