import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for ArrayList.
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
public class ArrayListStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a"; // For equality checking.

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
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
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
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

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    //JUnits from Killian Patrick Vetter
    @Test(timeout = TIMEOUT)
    public void testResizeBack() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            arr.addToBack(i);
        }
        assertEquals(13, arr.size());
        Integer[] correctArr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, null, null, null, null, null};
        assertArrayEquals(correctArr, arr.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeFront() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 12; i >= 0; i--) {
            arr.addToFront(i);
        }
        assertEquals(13, arr.size());
        Integer[] correctArr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, null, null, null, null, null};
        assertArrayEquals(correctArr, arr.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeIndex1() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            arr.addToBack(i);
        }
        assertEquals(9, arr.size());
        arr.addAtIndex(3, 20);
        arr.addAtIndex(8, 21);
        Integer[] correctArr = new Integer[] {0, 1, 2, 20, 3, 4, 5, 6, 21, 7, 8,
                null, null, null, null, null, null, null};
        assertEquals(11, arr.size());
        assertArrayEquals(correctArr, arr.getBackingArray());
    }

    //test adding to end and front with addAtIndex
    @Test(timeout = TIMEOUT)
    public void testResizeIndex2() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            arr.addAtIndex(arr.size(), i);
        }
        assertEquals(13, arr.size());
        Integer[] correctArr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, null, null, null, null, null};
        assertArrayEquals(correctArr, arr.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testResizeIndex3() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 12; i >= 0; i--) {
            arr.addAtIndex(0, i);
        }
        assertEquals(13, arr.size());
        Integer[] correctArr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, null, null, null, null, null};
        assertArrayEquals(correctArr, arr.getBackingArray());
    }

    //JUnits from Olivia Blanchette
    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBoundsTooBig() {
        ArrayList<String> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());
        array.addAtIndex(5, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsTooBig() {
        ArrayList<String> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());
        array.removeAtIndex(5);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsEmptyAt0() {
        ArrayList<String> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());
        array.removeAtIndex(0);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBoundsLessThan0() {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            array.addToBack("Num" + (i + 1) + ".");
        }
        assertEquals(15, array.size());
        String[] real = new String[] {"Num1.", "Num2.", "Num3.", "Num4.",
                "Num5.", "Num6.", "Num7.", "Num8.", "Num9.", "Num10.", "Num11.",
                "Num12.", "Num13.", "Num14.", "Num15.", null, null, null};
        assertArrayEquals(real, array.getBackingArray());
        array.addAtIndex(-1, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBoundsLessThan0() {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            array.addToBack("Num" + (i + 1) + ".");
        }
        assertEquals(15, array.size());
        String[] real = new String[] {"Num1.", "Num2.", "Num3.", "Num4.",
                "Num5.", "Num6.", "Num7.", "Num8.", "Num9.", "Num10.", "Num11.",
                "Num12.", "Num13.", "Num14.", "Num15.", null, null, null};
        assertArrayEquals(real, array.getBackingArray());
        array.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testAddOutOfBounds1Larger() {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            array.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals(15, array.size());
        String[] real = new String[] {"Num1.", "Num2.", "Num3.", "Num4.",
                "Num5.", "Num6.", "Num7.", "Num8.", "Num9.", "Num10.", "Num11.",
                "Num12.", "Num13.", "Num14.", "Num15.", null, null, null};
        assertArrayEquals(real, array.getBackingArray());
        array.addAtIndex(16, "Hi");
    }

    @Test(expected = IndexOutOfBoundsException.class, timeout = TIMEOUT)
    public void testRemoveOutOfBounds1Larger() {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            array.addAtIndex(i, "Num" + (i + 1) + ".");
        }
        assertEquals(15, array.size());
        String[] real = new String[] {"Num1.", "Num2.", "Num3.", "Num4.",
                "Num5.", "Num6.", "Num7.", "Num8.", "Num9.", "Num10.", "Num11.",
                "Num12.", "Num13.", "Num14.", "Num15.", null, null, null};
        assertArrayEquals(real, array.getBackingArray());
        array.removeAtIndex(15);
    }

    //JUnits from me (Adil Farooq)
    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddingNullData() {
        ArrayList<Integer> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());

        for (int i = 0; i < 5; i++) {
            array.addAtIndex(i, i);
        }
        assertEquals(5, array.size());
        Integer[] testCopy;
        testCopy = new Integer[] {0, 1, 2, 3, 4, null, null, null, null};
        assertArrayEquals(testCopy, array.getBackingArray());

        array.addAtIndex(2, null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddingNullDataToFront() {
        ArrayList<Integer> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());

        for (int i = 0; i < 5; i++) {
            array.addAtIndex(i, i);
        }
        assertEquals(5, array.size());
        Integer[] testCopy;
        testCopy = new Integer[] {0, 1, 2, 3, 4, null, null, null, null};
        assertArrayEquals(testCopy, array.getBackingArray());

        array.addToFront(null);
    }

    @Test(expected = IllegalArgumentException.class, timeout = TIMEOUT)
    public void testAddingNullDataToBack() {
        ArrayList<Integer> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());

        for (int i = 0; i < 5; i++) {
            array.addAtIndex(i, i);
        }
        assertEquals(5, array.size());
        Integer[] testCopy;
        testCopy = new Integer[] {0, 1, 2, 3, 4, null, null, null, null};
        assertArrayEquals(testCopy, array.getBackingArray());

        array.addToBack(null);
    }

    @Test(expected = NoSuchElementException.class, timeout = TIMEOUT)
    public void testRemovingFromFrontOfEmptyArray() {
        ArrayList<Integer> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());

        array.removeFromFront();
    }

    @Test(expected = NoSuchElementException.class, timeout = TIMEOUT)
    public void testRemovingFromBackOfEmptyArray() {
        ArrayList<Integer> array = new ArrayList<>();
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                array.getBackingArray());

        array.removeFromBack();
    }
}
