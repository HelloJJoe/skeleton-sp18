import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void testRandom1(){
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for(int i = 0; i < 200; i++){
            A.addLast(i);
        }
        assertEquals(0, (Object) A.get(0));

    }
    @Test
    public void testRandom2(){
        ArrayDeque<Integer> A = new ArrayDeque<>();
        for(int i = 0; i < 200; i++){
            A.addFirst(i);
        }
        assertEquals(199, (Object) A.get(0));

    }
}