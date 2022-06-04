import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for ArrayQueue and LinkedQueue.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class QueueStudentTest {
    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
                array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayEnqueueNull() {
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueResize() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");
        array.enqueue("6a");
        array.enqueue("7a");
        array.enqueue("8a");
        array.enqueue("9a");

        assertEquals(10, array.size());

        Object[] expected = new Object[18];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueResize1() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");
        array.enqueue("6a");
        array.dequeue();
        array.enqueue("7a");
        array.enqueue("8a");
        array.enqueue("9a");
        array.enqueue("10a");


        assertEquals(10, array.size());

        Object[] expected = new Object[18];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = "9a";
        expected[9] = "10a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueResize2() {
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");
        array.enqueue("6a");
        array.dequeue();
        array.enqueue("7a");
        array.enqueue("8a");
        array.enqueue("9a");
        array.enqueue("10a");
        array.dequeue();


        assertEquals(9, array.size());

        Object[] expected = new Object[18];
        expected[0] = null;
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        expected[5] = "6a";
        expected[6] = "7a";
        expected[7] = "8a";
        expected[8] = "9a";
        expected[9] = "10a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testArrayDequeueEmpty() {
        array.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue1() {
        String temp = "0a";
        String temp1 = "1a";

        array.enqueue(temp);    // 0a
        array.enqueue(temp1);    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a
        assertSame(temp1, array.dequeue());

        assertEquals(4, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "0a";

        array.enqueue(temp);    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayMassive() {
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");
        array.enqueue("5a");
        array.enqueue("6a");
        array.enqueue("7a");
        array.enqueue("8a");
        array.dequeue();
        array.dequeue();
        array.dequeue();

        assertEquals(6, array.size());
        Object[] expected1 = new Object[9];
        expected1[0] = null;
        expected1[1] = null;
        expected1[2] = null;
        expected1[3] = "3a";
        expected1[4] = "4a";
        expected1[5] = "5a";
        expected1[6] = "6a";
        expected1[7] = "7a";
        expected1[8] = "8a";

        array.enqueue("9a");
        array.enqueue("10a");
        array.enqueue("11a");
        array.enqueue("12a");

        assertEquals(10, array.size());

        Object[] expected = new Object[18];
        expected[0] = "3a";
        expected[1] = "4a";
        expected[2] = "5a";
        expected[3] = "6a";
        expected[4] = "7a";
        expected[5] = "8a";
        expected[6] = "9a";
        expected[7] = "10a";
        expected[8] = "11a";
        expected[9] = "12a";
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueue() {
        linked.enqueue("0a");   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");   // 0a, 1a, 2a, 3a
        linked.enqueue("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLinkedEnqueueNull() {
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeue() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, linked.size());

        assertSame(temp, linked.dequeue()); // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(expected = NoSuchElementException.class)
    public void testLinkedDequeueEmpty() {
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        String temp = "0a";

        linked.enqueue(temp);   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");    // 0a, 1a, 2a, 3a
        linked.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, linked.size());

        assertSame(temp, linked.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }
    //Tests by Shravan Cheekati
    @Test(timeout = TIMEOUT)
    public void testQueueFrontWhenClear() {
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");

        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();

        String top = "topofthequeue";
        String second = "2a";

        assertEquals(0, array.size());
        array.enqueue("1a");
        assertEquals(1, array.size());

        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a"); //after 9 dequeues
        array.enqueue(top);
        array.enqueue(second);
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");
        array.enqueue("1a");

        //if you continually increment front, this should be when it fails
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();
        array.dequeue();

        assertEquals(10, array.size());
        //Makes sure your first element is actually the first enqueued element'
        assertEquals(top, array.peek());

        array.dequeue();
        assertEquals(second, array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void isYourLinkedQueueReallyEmpty() {
        linked.enqueue("1a");
        linked.enqueue("1a");
        linked.enqueue("1a");
        linked.enqueue("1a");

        linked.dequeue();
        linked.dequeue();
        linked.dequeue();
        linked.dequeue();

        assertEquals(0, linked.size());

        //iow - Tests if a node can still be accessible
        assertEquals(null, linked.getHead());
        assertEquals(null, linked.getTail());
    }
}