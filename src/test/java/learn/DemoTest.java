package learn;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class DemoTest {

    @Test
    public void testLinkedList(){

        LinkedList<String> ll = new LinkedList<>();

        ll.add("Good");
        ll.add("Vibes");
        ll.add("Only");

        assertEquals("Good", ll.removeFirst());
    }

    @Test
    public void testStack(){

        Stack<String> stack = new Stack<>();

        stack.push("Good");
        stack.push("Vibes");
        stack.push("Only");

        assertEquals("Only", stack.pop());
    }

    @Test
    public void testArray(){

        int arr[] = new int[7];
    
        assertEquals(7, arr.length);

    }

}