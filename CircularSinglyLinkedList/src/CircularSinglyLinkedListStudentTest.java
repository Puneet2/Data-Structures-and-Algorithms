import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for CircularSinglyLinkedList.
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
public class CircularSinglyLinkedListStudentTest {

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a";

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, temp);   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, "3a");   // 0a, 1a, 2a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 2a, 3a, 4a
        assertEquals(6, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        String[] expected = new String[5];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.toArray());
    }

    //My tests

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBoundsTooBig() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.addAtIndex(5, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsTooBig() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.removeAtIndex(2);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsEmptyAt0() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.removeAtIndex(0);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBoundsLessThan0() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.addAtIndex(-1, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsLessThan0() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBounds1Larger() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        for (int i = 0; i < 15; i++) {
            cll.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals(15, cll.size());
        String[] real = new String[] {"Num1.", "Num2.", "Num3.", "Num4.", "Num5.", "Num6.", "Num7.",
                "Num8.", "Num9.", "Num10.", "Num11.", "Num12.", "Num13.", "Num14.", "Num15."};
        assertArrayEquals(real,
                cll.toArray());
        cll.addAtIndex(16, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBounds1Larger() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        for (int i = 0; i < 15; i++) {
            cll.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals(15, cll.size());
        cll.removeAtIndex(15);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testGetOutOfBounds1Larger() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        for (int i = 0; i < 15; i++) {
            cll.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals("Num12.", cll.get(11));
        cll.get(15);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testGetOutOfBoundsNeg() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        for (int i = 0; i < 15; i++) {
            cll.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals("Num10.", cll.get(9));
        cll.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testGetOutOfBoundsEmpty() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.get(0);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddAtNull() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.addToBack("Hi");
        cll.addAtIndex(1, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddFrontNull() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.addToBack("Hi");
        cll.addToFront(null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddBackNull() {
        CircularSinglyLinkedList<String> cll = new CircularSinglyLinkedList<>();
        assertEquals(0, cll.size());
        cll.addToBack("Hi");
        cll.addToBack(null);
    }

    //test from other students
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddOutOfIndex() {
        list.addAtIndex(5, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        list.addAtIndex(-1, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfIndex() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        assertEquals(4, list.size());

        list.removeAtIndex(5);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        list.addToFront("0a");
        list.addToFront("1a");
        assertEquals(2, list.size());

        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAtAddIndex() {
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAtAddFront() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAtAddBack() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFrontEmptyList() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveBackEmptyList() {
        list.removeFromBack();
    }

    //checks how code handles 3 occurances next to each other of same data.
    @Test(timeout = TIMEOUT)
    public void testMultiLastOcc() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "2a");   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, temp);   // 0a, 1a, 2a, 2a, 2a
        assertEquals(5, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 1a, 2a, 2a
        assertEquals(4, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }


    //checks how code handles 3 occurances of same data at different indices.
    @Test(timeout = TIMEOUT)
    public void testMultiLastOcc2() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "2a");   // 0a, 1a, 2a, 3a, 2a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 3a, 2a, 4a
        list.addAtIndex(6, temp);   // 0a, 1a, 2a, 3a, 2a, 4a, 2a
        list.addAtIndex(7, "5a");   // 0a, 1a, 2a, 3a, 2a, 4a, 2a, 5a
        assertEquals(8, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 1a, 2a, 3a, 2a, 4a, 5a
        assertEquals(7, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    //checks if the last occurance of the data is the head itself.
    @Test(timeout = TIMEOUT)
    public void testFirstLastOcc() {
        String temp = "0a";

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a

        assertEquals(4, list.size());

        assertSame(temp, list.removeLastOccurrence("0a")); // 1a, 2a, 3a
        assertEquals(3, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
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
        assertEquals(list.getHead(), cur);
    }

    //Tests if element does not exist in linked List
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testInvalidElementLastOcc() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "2a");   // 0a, 1a, 2a, 3a, 2a

        list.removeLastOccurrence("7a");
    }


    //Tests adding of elements after clearing a list.
    @Test(timeout = TIMEOUT)
    public void testClearAndAdd() {
        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        list.addToBack("3a");

        assertEquals(1, list.size());
        assertEquals("3a", list.getHead().getData());
        assertEquals(list.getHead(), list.getHead().getNext());
    }

    //Tests removeLastOccurrence() for illegal arguments
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullAtRemoveLastOccurrence() {
        list.removeLastOccurrence(null);
    }

    //Tests get() for out of bounds index
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds1() {
        list.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds2() {
        list.get(1);
    }

    //Tests all three add methods when size == 0
    @Test(timeout = TIMEOUT)
    public void testAddAtIndexEmptyList() {
        String temp = "0a";
        list.addAtIndex(0, temp);
        assertEquals(1, list.size());
        assertEquals(list.getHead().getData(), temp);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontEmptyList() {
        String temp = "0a";
        list.addToFront(temp);
        assertEquals(1, list.size());
        assertEquals(list.getHead().getData(), temp);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackEmptyList() {
        String temp = "0a";
        list.addToBack(temp);
        assertEquals(1, list.size());
        assertEquals(list.getHead().getData(), temp);
    }

    //Tests edge cases for removeLastOccurrence()
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceMultiple1() {
        String temp = "2a";
        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(1, "3a"); // 2a 3a
        list.addAtIndex(2, "2a"); // 2a 3a 2a
        list.addAtIndex(0, temp); // 2a 2a 3a 2a

        assertEquals(4, list.size());
        assertEquals("2a", list.removeLastOccurrence("2a")); // 2a 2a 3a
        assertEquals(3, list.size());
        assertEquals(temp, list.getHead().getData());
    }

    //This one specifically might catch you.
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceMultiple2() {
        String temp = "2a";
        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(0, "3a"); // 3a 2a
        list.addAtIndex(0, temp); // 2a 3a 2a

        assertEquals(3, list.size());
        assertEquals("2a", list.removeLastOccurrence("2a")); // 2a 3a;
        assertEquals(2, list.size());
        assertEquals(temp, list.getHead().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceMultiple3() {
        String temp = "2a";
        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(0, "2a"); // 2a 2a
        list.addAtIndex(0, temp); // 2a 2a 2a

        assertEquals(3, list.size());
        assertEquals("2a", list.removeLastOccurrence("2a")); // 2a 2a;
        assertEquals(2, list.size());
        assertEquals(temp, list.getHead().getData());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void emptyRemoveLastOccurence() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        assertEquals(4, list.size());

        list.clear();
        list.removeLastOccurrence("2a");
    }

    // Testing isEmpty and getHead functions along with addToBack and addToFront
    @Test(timeout = TIMEOUT)
    public void testIsEmptyAndGetHead() {
        list.addAtIndex(0, "0a");
        list.addToFront("1a");
        list.addToBack("2a");
        list.clear();

        assertTrue(list.isEmpty());

        list.addToBack("3a");
        assertEquals("3a", list.getHead().getData());

    }

    // Testing removeLastOccurrence -
    @Test(timeout = TIMEOUT)
    public void test1() {
        String temp = "0a";
        list.addToFront("0a");
        list.addToFront(temp);
        list.addToBack("0a");
        list.removeFromBack();
        assertSame(temp, list.removeLastOccurrence("0a"));
    }

    //Test for lists of size 1 to see if the head points to itself
    @Test(timeout = TIMEOUT)
    public void testHeadPointsToItself() {
        //for add to front
        list.addToFront("0a"); // 0a
        assertNotNull(list.getHead().getNext());
        assertEquals("0a", list.getHead().getNext().getData());
        list.clear();

        //for add to back
        list.addToBack("0a"); // 0a
        assertNotNull(list.getHead().getNext());
        assertEquals("0a", list.getHead().getNext().getData());
        list.clear();

        //for add at index
        list.addAtIndex(0, "0a");
        assertNotNull(list.getHead().getNext());
        assertEquals("0a", list.getHead().getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemovingAllOccurrences() {
        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("0a"); // 0a 3a 2a 1a 0a
        list.addToFront("1a"); // 1a 0a 3a 2a 1a 0a
        list.addToFront("0a"); // 0a 1a 0a 3a 2a 1a 0a

        String temp = "0a";
        assertEquals(7, list.size());

        assertSame(temp, list.removeLastOccurrence("0a")); // 0a 1a 0a 3a 2a 1a
        assertEquals(6, list.size());
        assertNotNull(list.get(4));
        assertSame("2a", list.get(4));
        assertNotNull(list.get(5));
        assertSame("1a", list.get(5));

        assertSame(temp, list.removeLastOccurrence("0a")); // 0a 1a 3a 2a 1a
        assertEquals(5, list.size());
        assertNotNull(list.get(2));
        assertSame("3a", list.get(2));
        assertNotNull(list.get(3));
        assertSame("2a", list.get(3));

        assertSame(temp, list.removeLastOccurrence("0a")); // 1a 3a 2a 1a
        assertEquals(4, list.size());
        assertNotNull(list.get(0));
        assertSame("1a", list.get(0));
        assertNotNull(list.get(1));
        assertSame("3a", list.get(1));
        assertNotNull(list.get(2));
        assertSame("2a", list.get(2));
        assertNotNull(list.get(3));
        assertSame("1a", list.get(3));
    }
}