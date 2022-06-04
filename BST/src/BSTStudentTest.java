import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for BST.
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
 * @author CS 1332 TAs [and Roshan Desai :)]
 * @version 1.0
 */
public class BSTStudentTest {

    private static final int TIMEOUT = 200;
    private BST<Integer> tree;

    @Before
    public void setup() {
        tree = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        /*
              2
             /
            0
             \
              1
        */

        List<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(0);
        data.add(1);
        tree = new BST<>(data);

        assertEquals(3, tree.size());

        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getRight()
                .getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        assertEquals(3, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer temp = 2;

        /*
              1
             / \
            0   2
                 \
                  3
                   \
                    4
            ->
              1
             / \
            0   3
                 \
                  4
        */

        tree.add(1);
        tree.add(0);
        tree.add(temp);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(2));

        assertEquals(4, tree.size());

        assertEquals((Integer) 1, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 3, tree.getRoot().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight()
                .getRight().getData());

        temp = 1;
        tree = new BST<>();

        /*
              1
             / \
            0   2
                 \
                  3
                   \
                    4
            ->
              2
             / \
            0   3
                 \
                  4
        */

        tree.add(temp);
        tree.add(0);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assertEquals(5, tree.size());

        assertSame(temp, tree.remove(1));

        assertEquals(4, tree.size());

        assertEquals((Integer) 2, tree.getRoot().getData());
        assertEquals((Integer) 0, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 3, tree.getRoot().getRight().getData());
        assertEquals((Integer) 4, tree.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer temp3 = 3;
        Integer temp0 = 0;
        Integer temp1 = 1;
        Integer temp2 = 2;
        Integer temp6 = 6;
        Integer temp4 = 4;
        Integer temp5 = 5;

        /*
                3
             /     \
            0       6
             \     /
              1   4
               \   \
                2   5
        */

        tree.add(temp3);
        tree.add(temp0);
        tree.add(temp1);
        tree.add(temp2);
        tree.add(temp6);
        tree.add(temp4);
        tree.add(temp5);
        assertEquals(7, tree.size());

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(temp0, tree.get(0));
        assertSame(temp1, tree.get(1));
        assertSame(temp2, tree.get(2));
        assertSame(temp3, tree.get(3));
        assertSame(temp4, tree.get(4));
        assertSame(temp5, tree.get(5));
        assertSame(temp6, tree.get(6));
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
                3
             /     \
            0       6
             \     /
              1   4
               \   \
                2   5
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(6);
        tree.add(4);
        tree.add(5);
        assertEquals(7, tree.size());

        assertTrue(tree.contains(0));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(6));
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> preorder = new ArrayList<>();
        preorder.add(3);
        preorder.add(0);
        preorder.add(1);
        preorder.add(2);
        preorder.add(8);
        preorder.add(4);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);

        // Should be [3, 0, 1, 2, 8, 4, 6, 5, 7]
        assertEquals(preorder, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> inorder = new ArrayList<>();
        inorder.add(0);
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);

        // Should be [0, 1, 2, 3, 4, 5, 6, 7, 8]
        assertEquals(inorder, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> postorder = new ArrayList<>();
        postorder.add(2);
        postorder.add(1);
        postorder.add(0);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(4);
        postorder.add(8);
        postorder.add(3);

        // Should be [2, 1, 0, 5, 7, 6, 4, 8, 3]
        assertEquals(postorder, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                3
             /     \
            0       8
             \     /
              1   4
               \   \
                2   6
                   / \
                  5   7
        */

        tree.add(3);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(8);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        tree.add(7);
        assertEquals(9, tree.size());

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(3);
        levelorder.add(0);
        levelorder.add(8);
        levelorder.add(1);
        levelorder.add(4);
        levelorder.add(2);
        levelorder.add(6);
        levelorder.add(5);
        levelorder.add(7);

        // Should be [3, 0, 8, 1, 4, 2, 6, 5, 7]
        assertEquals(levelorder, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
              2
             /
            0
             \
              1
        */

        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals(3, tree.size());

        assertEquals(2, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
              2
             /
            0
             \
              1
        */

        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals(3, tree.size());

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testFindPathBetween() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        assertEquals(expected, tree.findPathBetween(0, 2));

        expected.clear();
        expected.add(2);
        expected.add(1);
        assertEquals(expected, tree.findPathBetween(2, 1));
    }

    // Roshan's Tests

    // add
    // Tests adding a null value to the BST
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        tree.add(1);
        tree.add(0);
        tree.add(null);
    }

    // Test to verify that adding an element to BST that already exists does nothing
    @Test(timeout = TIMEOUT)
    public void addExistingElement() {
        /*
              5
             / \
            1   9
        */
        tree.add(5);
        tree.add(9);
        tree.add(1);

        assertEquals(3, tree.size());

        assertEquals((Integer) 5, tree.getRoot().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getData());

        tree.add(1);
        assertEquals(3, tree.size());
    }

    // Test add method with many different cases
    @Test(timeout = TIMEOUT)
    public void addABunch() {
        /*
              6
             / \
            1   9
           / \ / \
          0  2 7  12
        */

        tree.add(6);
        tree.add(9);
        tree.add(1);

        assertEquals(3, tree.size());

        assertEquals((Integer) 6, tree.getRoot().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getData());

        tree.add(12);
        tree.add(2);
        tree.add(0);
        tree.add(7);

        assertEquals(7, tree.size());

        assertEquals((Integer) 6, tree.getRoot().getData());
        assertEquals((Integer) 1, tree.getRoot().getLeft().getData());
        assertEquals((Integer) 9, tree.getRoot().getRight().getData());

        assertEquals((Integer) 0, tree.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 2, tree.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 7, tree.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 12, tree.getRoot().getRight().getRight().getData());

        tree.add(12);
        tree.add(9);
        tree.add(2);

        assertEquals(7, tree.size());
    }

    // remove
    // test removing null data
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        tree.remove(null);
    }

    // test removing data that is not in the tree
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveElementNotInBST() {
        tree.add(6);
        tree.add(9);
        tree.add(3);
        tree.remove(2);
    }

    // test remove with no-child case
    @Test(timeout = TIMEOUT)
    public void testRemoveNoChild() {
        /*
                          25
                       /      \
                      17      29
                     /  \    /  \
                    16   18 28  30
                   /       \      \
                  4         21     32
                /   \                \
               2     8                55
         */

        tree.add(25);
        tree.add(17);
        tree.add(29);
        tree.add(28);
        tree.add(30);
        tree.add(16);
        tree.add(18);
        tree.add(32);
        tree.add(21);
        tree.add(4);
        tree.add(8);
        tree.add(2);
        tree.add(55);

        assertEquals(13, tree.size());

        tree.remove(8);
        assertEquals(12, tree.size());
        assertNull(tree.getRoot().getLeft().getLeft().getLeft().getRight());

        tree.remove(28);
        assertEquals(11, tree.size());
        assertNull(tree.getRoot().getRight().getLeft());
    }

    // test remove with one-child case
    @Test(timeout = TIMEOUT)
    public void testRemoveOneChild() {
        /*
                          25
                       /      \
                      17      29
                     /  \    /  \
                    16   18 28  30
                   /       \      \
                  4         21     32
                /   \                \
               2     8                55
         */

        tree.add(25);
        tree.add(17);
        tree.add(29);
        tree.add(28);
        tree.add(30);
        tree.add(16);
        tree.add(18);
        tree.add(32);
        tree.add(21);
        tree.add(4);
        tree.add(8);
        tree.add(2);
        tree.add(55);

        assertEquals(13, tree.size());

        tree.remove(18);
        assertEquals(12, tree.size());
        assertEquals((Integer) 21, tree.getRoot().getLeft().getRight().getData());
    }

    // test remove with two-child case
    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChild() {
        /*
                          25
                       /      \
                      17      29
                     /  \    /  \
                    16   18 28  30
                   /       \      \
                  4         21     32
                /   \                \
               2     8                55
         */

        tree.add(25);
        tree.add(17);
        tree.add(29);
        tree.add(28);
        tree.add(30);
        tree.add(16);
        tree.add(18);
        tree.add(32);
        tree.add(21);
        tree.add(4);
        tree.add(8);
        tree.add(2);
        tree.add(55);

        assertEquals(13, tree.size());

        tree.remove(29);
        assertEquals(12, tree.size());
        assertEquals((Integer) 30, tree.getRoot().getRight().getData());
        assertEquals((Integer) 28, tree.getRoot().getRight().getLeft().getData());
    }

    // test remove with root (2 child with no-child successor)
    @Test(timeout = TIMEOUT)
    public void testRemoveRootNoChildSuccessor() {
        /*
                          25
                       /      \
                      17      29
                     /  \    /  \
                    16   18 28  30
                   /       \      \
                  4         21     32
                /   \                \
               2     8                55
         */

        tree.add(25);
        tree.add(17);
        tree.add(29);
        tree.add(28);
        tree.add(30);
        tree.add(16);
        tree.add(18);
        tree.add(32);
        tree.add(21);
        tree.add(4);
        tree.add(8);
        tree.add(2);
        tree.add(55);

        assertEquals(13, tree.size());

        tree.remove(25);
        assertEquals(12, tree.size());
        // check if successor replaced root
        assertEquals((Integer) 28, tree.getRoot().getData());
        // check if successor was removed
        assertNull(tree.getRoot().getRight().getLeft());
    }

    // test remove with root (2 child with one-child successor)
    @Test(timeout = TIMEOUT)
    public void testRemoveRootOneChildSuccessor() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        assertEquals(13, tree.size());

        tree.remove(25);
        assertEquals(12, tree.size());
        // check if successor replaced root
        assertEquals((Integer) 26, tree.getRoot().getData());
        assertEquals((Integer) 27, tree.getRoot().getRight().getLeft().getLeft().getData());
    }

    // get
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNullData() {
        tree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetDataNotInTree() {
        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.get(18);
    }

    @Test(timeout = TIMEOUT)
    public void testGetLeaf() {
        /*
                22
              /    \
             15    34
              \
               17
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        assertEquals((Integer) 17, tree.get(17));
    }

    @Test(timeout = TIMEOUT)
    public void testGetRoot() {
        /*
                22
              /    \
             15    34
              \
               17
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        assertEquals((Integer) 22, tree.get(22));
    }

    @Test(timeout = TIMEOUT)
    public void testGetInternalNode() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);
        assertEquals((Integer) 15, tree.get(15));
        assertEquals((Integer) 34, tree.get(34));
    }

    // contains
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNullData() {
        tree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsDataNotInTree() {
        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        assertEquals(false, tree.contains(18));
        assertEquals(false, tree.contains(14));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsLeaf() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        assertEquals(true, tree.contains(17));
        assertEquals(true, tree.contains(31));
        assertEquals(true, tree.contains(38));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsRoot() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        assertEquals(true, tree.contains(22));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsInternalNode() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        assertEquals(true, tree.contains(15));
        assertEquals(true, tree.contains(34));
    }

    // preorder
    @Test(timeout = TIMEOUT)
    public void testName() {

    }

    @Test(timeout = TIMEOUT)
    public void testPreorderTraversalSimple() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(0);
        expected.add(2);

        assertEquals(3, tree.preorder().size());
        assertEquals(expected, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderComplex() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] preorderTraversal = {25, 17, 16, 4, 2, 8, 29, 28, 26, 27, 30, 32, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : preorderTraversal) {
            expected.add(i);
        }

        assertEquals(13, tree.preorder().size());
        assertEquals(expected, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderDegenerate() {
        /*
        1
         \
          3
           \
            6
             \
              10
               \
                15
               /  \
              14  21
                   \
                    28
                     \
                      36
                       \
                        45
                         \
                          55
         */

        int[] data = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 14};
        for (int i : data) {
            tree.add(i);
        }

        int[] preorderTraversal = {1, 3, 6, 10, 15, 14, 21, 28, 36, 45, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : preorderTraversal) {
            expected.add(i);
        }

        assertEquals(11, tree.preorder().size());
        assertEquals(expected, tree.preorder());
    }

    // inorder
    @Test(timeout = TIMEOUT)
    public void testInorderTraversalSimple() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);

        assertEquals(3, tree.inorder().size());
        assertEquals(expected, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderComplex() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] inorderTraversal = {2, 4, 8, 16, 17, 25, 26, 27, 28, 29, 30, 32, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : inorderTraversal) {
            expected.add(i);
        }

        assertEquals(13, tree.inorder().size());
        assertEquals(expected, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderDegenerate() {
        /*
        1
         \
          3
           \
            6
             \
              10
               \
                15
               /  \
              14  21
                   \
                    28
                     \
                      36
                       \
                        45
                         \
                          55
         */

        int[] data = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 14};
        for (int i : data) {
            tree.add(i);
        }

        int[] inorderTraversal = {1, 3, 6, 10, 14, 15, 21, 28, 36, 45, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : inorderTraversal) {
            expected.add(i);
        }

        assertEquals(11, tree.inorder().size());
        assertEquals(expected, tree.inorder());
    }

    // postorder
    @Test(timeout = TIMEOUT)
    public void testPostorderTraversalSimple() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);

        assertEquals(3, tree.inorder().size());
        assertEquals(expected, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderComplex() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] postorderTraversal = {2, 8, 4, 16, 17, 27, 26, 28, 55, 32, 30, 29, 25};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : postorderTraversal) {
            expected.add(i);
        }

        assertEquals(13, tree.postorder().size());
        assertEquals(expected, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderDegenerate() {
        /*
        1
         \
          3
           \
            6
             \
              10
               \
                15
               /  \
              14  21
                   \
                    28
                     \
                      36
                       \
                        45
                         \
                          55
         */

        int[] data = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 14};
        for (int i : data) {
            tree.add(i);
        }

        int[] postorderTraversal = {14, 55, 45, 36, 28, 21, 15, 10, 6, 3, 1};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : postorderTraversal) {
            expected.add(i);
        }

        assertEquals(11, tree.postorder().size());
        assertEquals(expected, tree.postorder());
    }

    // levelorder
    @Test(timeout = TIMEOUT)
    public void testLevelorderTraversalSimple() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(0);
        expected.add(2);

        assertEquals(3, tree.levelorder().size());
        assertEquals(expected, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderComplex() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] levelorderTraversal = {25, 17, 29, 16, 28, 30, 4, 26, 32, 2, 8, 27, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : levelorderTraversal) {
            expected.add(i);
        }

        assertEquals(13, tree.levelorder().size());
        assertEquals(expected, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderImbalanced() {
        /*
                           25
                       /       \
                      17        29
                     /  \     /    \
                    16   20 28     40
                   /       /     /   \
                  4       26    33    62
                /   \       \           \
               2     8       27          69
         */
        int[] data = {25, 17, 16, 4, 2, 8, 20, 29, 28, 26, 27, 40, 33, 62, 69};
        for (int i : data) {
            tree.add(i);
        }

        int[] levelorderTraversal = {25, 17, 29, 16, 20, 28, 40, 4, 26, 33, 62, 2, 8, 27, 69};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : levelorderTraversal) {
            expected.add(i);
        }

        assertEquals(15, tree.levelorder().size());
        assertEquals(expected, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderDegenerate() {
        /*
        1
         \
          3
           \
            6
             \
              10
               \
                15
               /  \
              14  21
                   \
                    28
                     \
                      36
                       \
                        45
                         \
                          55
         */

        int[] data = {1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 14};
        for (int i : data) {
            tree.add(i);
        }

        int[] levelorderTraversal = {1, 3, 6, 10, 15, 14, 21, 28, 36, 45, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : levelorderTraversal) {
            expected.add(i);
        }

        assertEquals(11, tree.levelorder().size());
        assertEquals(expected, tree.levelorder());
    }


    // height
    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        assertEquals(-1, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightSimple() {
        /*
              1
             / \
            0   2
        */

        tree.add(1);
        tree.add(0);
        tree.add(2);

        assertEquals(1, tree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightComplex() {
        /*
                           25
                       /       \
                      17        29
                     /  \     /    \
                    16   20 28     40
                   /       /     /   \
                  4       26    33    62
                /   \       \           \
               2     8       27          69
         */
        int[] data = {25, 17, 16, 4, 2, 8, 20, 29, 28, 26, 27, 40, 33, 62, 69};
        for (int i : data) {
            tree.add(i);
        }

        assertEquals(4, tree.height());
    }

    // clear
    @Test(timeout = TIMEOUT)
    public void testClearTree() {
        tree.add(2);
        tree.add(5);
        tree.add(16);
        tree.add(8);

        assertEquals(4, tree.size());
        tree.clear();

        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    // findPathBetween
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testFindPathBetweenDataNotInTree() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(16, 69);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testFindPathBetweenDataNotInTree2() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(16, 38);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testFindPathBetweenDataNotInTree3() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(17, 69);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testFindPathBetweenNullData() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(null, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testFindPathBetweenNullData2() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(null, 31);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testFindPathBetweenNullData3() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        tree.findPathBetween(15, null);
    }

    @Test(timeout = TIMEOUT)
    public void testFindPathBetweenLeafNodes() {
        /*
                 22
              /      \
             15      34
              \     /  \
               17  31   38
         */

        tree.add(22);
        tree.add(15);
        tree.add(17);
        tree.add(34);
        tree.add(31);
        tree.add(38);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(17);
        expected.add(15);
        expected.add(22);
        expected.add(34);
        expected.add(38);

        assertEquals(expected.size(), tree.findPathBetween(17, 38).size());
        assertEquals(expected, tree.findPathBetween(17, 38));

        expected.clear();
        expected.add(38);
        expected.add(34);
        expected.add(22);
        expected.add(15);
        expected.add(17);
        assertEquals(expected.size(), tree.findPathBetween(38, 17).size());
        assertEquals(expected, tree.findPathBetween(38, 17));
    }

    @Test(timeout = TIMEOUT)
    public void testFindPathBetweenComplex() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] pathBtwn = {4, 16, 17, 25, 29, 30, 32};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : pathBtwn) {
            expected.add(i);
        }

        assertEquals(expected.size(), tree.findPathBetween(4, 32).size());
        assertEquals(expected, tree.findPathBetween(4, 32));

        // test with non-root DCA
        pathBtwn = new int[] {26, 28, 29, 30, 32, 55};
        expected.clear();
        for (Integer i : pathBtwn) {
            expected.add(i);
        }

        assertEquals(expected.size(), tree.findPathBetween(26, 55).size());
        assertEquals(expected, tree.findPathBetween(26, 55));

        // another test with non-root DCA
        pathBtwn = new int[] {32, 30, 29, 28};
        expected.clear();
        for (Integer i : pathBtwn) {
            expected.add(i);
        }

        assertEquals(expected.size(), tree.findPathBetween(32, 28).size());
        assertEquals(expected, tree.findPathBetween(32, 28));
    }

    @Test(timeout = TIMEOUT)
    public void testFindPathBetweenDCA() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        int[] pathBtwn = {29, 30, 32, 55};
        ArrayList<Integer> expected = new ArrayList<>();
        for (Integer i : pathBtwn) {
            expected.add(i);
        }

        assertEquals(expected.size(), tree.findPathBetween(29, 55).size());
        assertEquals(expected, tree.findPathBetween(29, 55));

        // another test with dca as data
        pathBtwn = new int[] {29, 28, 26, 27};
        expected.clear();
        for (Integer i : pathBtwn) {
            expected.add(i);
        }

        assertEquals(expected.size(), tree.findPathBetween(29, 27).size());
        assertEquals(expected, tree.findPathBetween(29, 27));
    }

    // comprehensive test of a bunch of methods on a huge BST
    @Test(timeout = TIMEOUT)
    public void testEverything() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }

        assertEquals(13, tree.size());

        // add/remove operations
        tree.add(19);
        tree.remove(28);
        tree.add(33);
        tree.add(79);
        tree.remove(29);
        tree.remove(25);
        tree.add(10);

        /*
                          26
                       /      \
                      17       30
                     /  \     /  \
                    16   19  27   32
                   /               \
                  4                 55
                /   \              /  \
               2     8           33    79
                      \
                       10
         */
        assertEquals(14, tree.size());
        assertEquals(5, tree.height());

        ArrayList<Integer> expected = new ArrayList<>();
        int[] preorder = new int[] {26, 17, 16, 4, 2, 8, 10, 19, 30, 27, 32, 55, 33, 79};
        for (Integer i : preorder) {
            expected.add(i);
        }

        assertEquals(expected, tree.preorder());

        expected.clear();
        int[] inorder = new int[] {2, 4, 8, 10, 16, 17, 19, 26, 27, 30, 32, 33, 55, 79};
        for (Integer i : inorder) {
            expected.add(i);
        }

        assertEquals(expected, tree.inorder());

        expected.clear();
        int[] postorder = new int[] {2, 10, 8, 4, 16, 19, 17, 27, 33, 79, 55, 32, 30, 26};
        for (Integer i : postorder) {
            expected.add(i);
        }

        assertEquals(expected, tree.postorder());

        expected.clear();
        int[] levelorder = new int[] {26, 17, 30, 16, 19, 27, 32, 4, 55, 2, 8, 33, 79, 10};
        for (Integer i : levelorder) {
            expected.add(i);
        }

        assertEquals(expected, tree.levelorder());

        assertEquals(false, tree.contains(420));
        assertEquals(true, tree.contains(10));
        assertEquals(true, tree.contains(26));
        tree.remove(26);
        assertEquals(false, tree.contains(26));

        assertEquals((Integer) 10, tree.get(10));

        tree.clear();
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }


    @Test(timeout = TIMEOUT)
    public void testLevelOrderNull() {
        assertEquals(new ArrayList<>(), tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrderNull() {
        assertEquals(new ArrayList<>(), tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInOrderNull() {
        assertEquals(new ArrayList<>(), tree.inorder());
    }


    @Test(timeout = TIMEOUT)
    public void testPreOrderNull() {
        assertEquals(new ArrayList<>(), tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreOrderHead() {
        tree.add(19);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(19);
        assertEquals(expected, tree.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInOrderHead() {
        tree.add(19);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(19);
        assertEquals(expected, tree.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrderHead() {
        tree.add(19);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(19);
        assertEquals(expected, tree.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrderHead() {
        tree.add(19);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(19);
        assertEquals(expected, tree.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPathBetweenSameNodesRoot() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }
        assertEquals(13, tree.size());
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(25);
        assertEquals(expected, tree.findPathBetween(25, 25));
    }


    @Test(timeout = TIMEOUT)
    public void testPathBetweenSameNodesLeaf() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }
        assertEquals(13, tree.size());
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(27);
        assertEquals(expected, tree.findPathBetween(27, 27));
    }

    @Test(timeout = TIMEOUT)
    public void testPathBetweenSameNodes() {
        /*
                          25
                       /      \
                      17      29
                     /       /  \
                    16      28  30
                   /       /      \
                  4       26       32
                /   \       \        \
               2     8       27       55
         */
        int[] data = {25, 17, 29, 28, 30, 16, 32, 4, 8, 2, 55, 26, 27};
        for (int i : data) {
            tree.add(i);
        }
        assertEquals(13, tree.size());
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(29);
        assertEquals(expected, tree.findPathBetween(29, 29));
    }
}