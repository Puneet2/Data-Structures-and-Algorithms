import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for MaxHeap.
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
public class MaxHeapStudentTest {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        /*
                 2
               /   \
              1     4
             / \
            6   8
            ->
                 8
               /   \
              6     4
             / \
            2   1
        */

        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(1);
        data.add(4);
        data.add(6);
        data.add(8);
        heap = new MaxHeap<>(data);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[11];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 4;
        expected[4] = 2;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                 4
        */
        heap.add(4);

        /*
                 6
               /
              4
        */
        heap.add(6);

        /*
                 6
               /   \
              4     2
        */
        heap.add(2);

        /*
                 8
               /   \
              6     2
             /
            4
        */
        heap.add(8);

        /*
                 8
               /   \
              6     2
             / \
            4   1
        */
        heap.add(1);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 2;
        expected[4] = 4;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                 8
               /   \
              6     4
             / \
            1   2
        */
        heap.add(4);
        heap.add(1);
        heap.add(6);
        heap.add(2);
        heap.add(8);
        assertEquals(5, heap.size());

        /*
                 6
               /   \
              2     4
             /
            1
        */
        assertEquals((Integer) 8, heap.remove());

        /*
                 4
               /   \
              2     1
        */
        assertEquals((Integer) 6, heap.remove());

        /*
                 2
               /
              1
        */
        assertEquals((Integer) 4, heap.remove());

        assertEquals(2, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 2;
        expected[2] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetMax() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertSame(5, heap.getMax());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());

        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertFalse(heap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullData() {
        heap.add(1);
        heap.add(2);

        heap.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddUntilResize() {
        for (int i = 0; i < 20; i++) {
            heap.add(i);
        }

        assertEquals(20, heap.size());
        int x = heap.remove();
        assertEquals(19, x);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveUntilEmpty() {
        for (int i = 0; i < 10; i++) {
            heap.add(i);
        }

        for (int i = 0; i < 10; i++) {
            int x = heap.remove();
        }

        assertEquals(0, heap.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemovePastEmpty() {
        for (int i = 0; i < 10; i++) {
            heap.add(i);
        }

        for (int i = 0; i < 11; i++) {
            int x = heap.remove();
        }
    }
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetMaxIfEmpty() {
        heap.remove();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapNullData() {
        heap = new MaxHeap<>(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapListHasNull() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(1);
        data.add(null);
        data.add(6);
        data.add(8);
        heap = new MaxHeap<>(data);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeapArraySize() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        heap = new MaxHeap<>(data);

        Integer[] arr = new Integer[3];
        arr[1] = 2;
        assertArrayEquals(arr,
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeapLargeDataSet() {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 101; i += 2) {
            data.add(i);
        }
        heap = new MaxHeap<>(data);

        assertEquals(51, heap.size());

        assertEquals((Integer) 100, heap.remove());
        assertEquals((Integer) 98, heap.remove());
        assertEquals((Integer) 96, heap.remove());
    }

    //Tests if the add method works correctly after clearing the heap.
    @Test(timeout = TIMEOUT)
    public void testClearAndAdd() {
        /*
                 3
               /
              2
         */
        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(3);
        heap = new MaxHeap<>(data);

        assertEquals(2, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 3;
        expected[2] = 2;
        assertEquals((Integer) 3, heap.getMax());

        heap.clear();

        assertTrue(heap.isEmpty());

        heap.add(5);
        assertEquals((Integer) 5, heap.getMax());
    }

    //Tests if the implemented BuildHeap algorithm works with negative integeres.
    @Test(timeout = TIMEOUT)
    public void testBuildNegativeInt() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(-7);
        data.add(-4);
        data.add(-6);
        data.add(-25);
        data.add(-5);
        heap = new MaxHeap<>(data);

        assertEquals(5, heap.size());

        assertEquals((Integer) (-4), heap.getMax());
    }

    //Tests what happens by adding a negative integer into the heap.
    @Test(timeout = TIMEOUT)
    public void testAddNegativeInt() {
        heap.add(-2);
        heap.add(-7);
        heap.add(-1);

        assertEquals(3, heap.size());

        assertEquals((Integer) (-1), heap.getMax());
    }

    //Tests adding building heap with both positive and negative integers.
    @Test(timeout = TIMEOUT)
    public void testBuildNegativeInt2() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(7);
        data.add(-6);
        data.add(9);
        data.add(-2);
        data.add(1);
        heap = new MaxHeap<>(data);

        assertEquals(6, heap.size());

        assertEquals((Integer) 9, heap.remove());
        assertEquals((Integer) 7, heap.remove());
        assertEquals((Integer) 2, heap.remove());
        assertEquals((Integer) 1, heap.remove());
        assertEquals((Integer) (-2), heap.remove());
        assertEquals((Integer) (-6), heap.remove());

        assertEquals(true, heap.isEmpty());
    }

    //Tests what calling getMax() does on an empty heap.
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testEmptyMax() {
        heap.clear();
        heap.getMax();
    }

    //Olivia Blanchette exception tests
    @Test(timeout = TIMEOUT, expected=IllegalArgumentException.class)
    public void testNullListMakeHeap(){
        heap=new MaxHeap<>(null);
    }

    @Test(timeout = TIMEOUT, expected=IllegalArgumentException.class)
    public void testNullListElementMakeHeap(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(12);
        list.add(null);
        heap=new MaxHeap<>(list);
    }

    @Test(timeout = TIMEOUT, expected=IllegalArgumentException.class)
    public void testNullAdd(){
        heap.add(15);
        heap.add(57);
        heap.add(null);
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testRemoveTooMany(){
        heap.add(15);
        heap.add(57);
        heap.remove();
        heap.remove();
        heap.remove();
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testClearAndRemove(){
        heap.add(15);
        heap.add(57);
        heap.clear();
        heap.remove();
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testEmptyRemove(){
        heap.remove();
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testRemoveAndGetMaxTooMany(){
        heap.add(15);
        heap.add(57);
        heap.remove();
        heap.remove();
        heap.getMax();
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testClearAndGetMax(){
        heap.add(15);
        heap.add(57);
        heap.clear();
        heap.getMax();
    }

    @Test(timeout = TIMEOUT, expected=NoSuchElementException.class)
    public void testEmptyGetMax(){
        heap.remove();
    }
}