import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for Sorting.
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
public class SortingStudentTest {

    private static final int TIMEOUT = 200;
    private TeachingAssistant[] tas;
    private TeachingAssistant[] tasByName;
    private ComparatorPlus<TeachingAssistant> comp;

    // END OF STABILITY TESTING BLOCK

    @Before
    public void setUp() {
        /*
            Unsorted Names:
                index 0: Aleksandr
                index 1: Grant
                index 2: Mahima
                index 3: Keely
                index 4: Harneet
                index 5: Shayar
                index 6: Yotam
                index 7: Tri
                index 8: Robert
                index 9: Isha
         */

        /*
            Sorted Names:
                index 0: Aleksandr
                index 1: Grant
                index 2: Harneet
                index 3: Isha
                index 4: Keely
                index 5: Mahima
                index 6: Robert
                index 7: Shayar
                index 8: Tri
                index 9: Yotam
         */

        tas = new TeachingAssistant[10];
        tas[0] = new TeachingAssistant("Aleksandr");
        tas[1] = new TeachingAssistant("Grant");
        tas[2] = new TeachingAssistant("Mahima");
        tas[3] = new TeachingAssistant("Keely");
        tas[4] = new TeachingAssistant("Harneet");
        tas[5] = new TeachingAssistant("Shayar");
        tas[6] = new TeachingAssistant("Yotam");
        tas[7] = new TeachingAssistant("Tri");
        tas[8] = new TeachingAssistant("Robert");
        tas[9] = new TeachingAssistant("Isha");
        tasByName = new TeachingAssistant[10];
        tasByName[0] = tas[0];
        tasByName[1] = tas[1];
        tasByName[2] = tas[4];
        tasByName[3] = tas[9];
        tasByName[4] = tas[3];
        tasByName[5] = tas[2];
        tasByName[6] = tas[8];
        tasByName[7] = tas[5];
        tasByName[8] = tas[7];
        tasByName[9] = tas[6];

        comp = TeachingAssistant.getNameComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        Sorting.selectionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 26 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 21 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        Sorting.quickSort(tas, comp, new Random(234));
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 27 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, -85, 3};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[] {-85, 3, 20, 28, 54, 58, 84, 122};
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    // Test Cases By: Tomer Shmul

    // STABILITY TESTING
    /* basically, using objects with IDs to test that when sorted,
       the IDs of the objects remain in the same order that they
       were present in the original list
     */
    StringNum a1 = new StringNum("A", 1);
    StringNum b1 = new StringNum("B", 1);
    StringNum b2 = new StringNum("B", 2);
    StringNum c1 = new StringNum("C", 1);
    StringNum c2 = new StringNum("C", 2);
    StringNum c3 = new StringNum("C", 3);
    StringNum d1 = new StringNum("D", 1);
    StringNum d2 = new StringNum("D", 2);
    StringNum d3 = new StringNum("D", 3);
    StringNum d4 = new StringNum("D", 4);
    StringNum e1 = new StringNum("E", 1);
    StringNum e2 = new StringNum("E", 2);
    StringNum e3 = new StringNum("E", 3);
    StringNum e4 = new StringNum("E", 4);
    StringNum e5 = new StringNum("E", 5);
    StringNum[] listAscend = {a1, b1, c1, d1, e1, b2, c2, d2, e2, c3, d3, e3, d4, e4, e5};
    StringNum[] correctAscend = {a1, b1, b2, c1, c2, c3, d1, d2, d3, d4, e1, e2, e3, e4, e5};
    StringNum[] listDescend = {e5, e4, e3, e2, e1, d4, d3, d2, d1, c3, c2, c1, b2, b1, a1};
    StringNum[] correctDescend = {a1, b2, b1, c3, c2, c1, d4, d3, d2, d1, e5, e4, e3, e2, e1};
    private Comparator<StringNum> compS = StringNum.getNameComparator();

    static class StringNum implements Comparable<StringNum> {
        private final String s;
        private final int n;
        public StringNum(String s, int n) {
            this.s = s;
            this.n = n;
        }
        @Override
        public int compareTo(StringNum that) {
            return this.s.compareTo(that.s); //Ordering by the string
        }
        @Override
        public String toString() {
            return this.s + ":" + this.n;
        }

        public String getName() {
            return s;
        }

        public static ComparatorPlus<StringNum> getNameComparator() {
            return new ComparatorPlus<StringNum>() {
                @Override
                public int compare(StringNum ta1,
                                   StringNum ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortStability() {
        Sorting.cocktailSort(listAscend, compS);
//        Uncomment these lines to see where the "unstableness" occurs
//        printArr(listAscend);
//        printArr(correctAscend);
        assertArrayEquals(listAscend, correctAscend);

        Sorting.cocktailSort(listDescend, compS);
//        printArr(listDescend);
//        printArr(correctDescend);
        assertArrayEquals(listDescend, correctDescend);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortStability() {
        Sorting.mergeSort(listAscend, compS);
        assertArrayEquals(listAscend, correctAscend);

        Sorting.mergeSort(listDescend, compS);
        assertArrayEquals(listDescend, correctDescend);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionException1() {
        Sorting.selectionSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testSelectionException2() {
        Sorting.selectionSort(tas, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCockTailException1() {
        Sorting.cocktailSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testCockTailException2() {
        Sorting.cocktailSort(tas, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortException1() {
        Sorting.mergeSort(null, comp);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testMergeSortException2() {
        Sorting.mergeSort(tas, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException1() {
        Sorting.quickSort(null, comp, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException2() {
        Sorting.quickSort(tas, null, new Random());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testQuickSortException3() {
        Sorting.quickSort(tas, comp, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLSDRadixException() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapSortException() {
        Sorting.heapSort(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArraySize0() {
        Sorting.heapSort(new LinkedList<Integer>());
        Sorting.lsdRadixSort(new int[0]);
        Sorting.quickSort(new TeachingAssistant[0], comp, new Random());
        Sorting.mergeSort(new TeachingAssistant[0], comp);
        Sorting.cocktailSort(new TeachingAssistant[0], comp);
        Sorting.selectionSort(new TeachingAssistant[0], comp);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort2() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3, -60, -120};
        int[] sortedArray = new int[]{-120, -85, -60, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    public static void printArr(StringNum[] arr) {
        for (StringNum s : arr)
            System.out.print(s + ", ");
        System.out.println();
    }

    // Test Cases By: Pratham Darrpan Mehta

    private TeachingAssistant[] empty = new TeachingAssistant[0];
    private TeachingAssistant[] singular = new TeachingAssistant[1];

    @Before
    public void setUp2() {
        singular[0] = new TeachingAssistant("Pratham");
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortEmptyArray() {
        Sorting.selectionSort(empty, comp);
        assertArrayEquals(empty, empty);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortSingleElementArray() {
        Sorting.selectionSort(singular, comp);
        assertArrayEquals(singular, singular);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortEmptyArray() {
        Sorting.cocktailSort(empty, comp);
        assertArrayEquals(empty, empty);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortSingleElementArray() {
        Sorting.cocktailSort(singular, comp);
        assertArrayEquals(singular, singular);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortEmptyArray() {
        Sorting.mergeSort(empty, comp);
        assertArrayEquals(empty, empty);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortSingleElementArray() {
        Sorting.mergeSort(singular, comp);
        assertArrayEquals(singular, singular);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortEmptyArray() {
        Sorting.quickSort(empty, comp, new Random(234));
        assertArrayEquals(empty, empty);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortSingleElementArray() {
        Sorting.quickSort(singular, comp, new Random(234));
        assertArrayEquals(singular, singular);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSortEmptyArray() {
        List<Integer> unsortedList = new ArrayList<>();
        int[] sortedArray = new int[0];
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixEmptyArray() {
        int[] unsortedArray = new int[0];
        int[] sortedArray = new int[0];
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortMinValue() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3, Integer.MIN_VALUE};
        int[] sortedArray = new int[]{Integer.MIN_VALUE, -85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortMaxValue() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, Integer.MAX_VALUE, 922, -85, 3};
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 922, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);

        // Check the value of k being calculated here and think of how you could potentially
        // make that more efficient

        // ideal k here is 4
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortMinMaxValue() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, Integer.MAX_VALUE, 122, -85, 3, Integer.MIN_VALUE};
        int[] sortedArray = new int[]{Integer.MIN_VALUE, -85, 3, 20, 28, 54, 58, 84, 122, Integer.MAX_VALUE};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);

        // Check the value of k being calculated here and think of how you could potentially
        // make that more efficient

        // ideal k here is 4
    }


    // Test Cases By: Rian Rahman

    private TeachingAssistant[] sortedTAs = new TeachingAssistant[5];
    private TeachingAssistant[] reverseSortedTAs = new TeachingAssistant[5];
    private TeachingAssistant[] allDuplicateTAs = new TeachingAssistant[5];
    private Integer[] sortedInts = new Integer[10];
    private Integer[] reverseSortedInts = new Integer[10];
    private Integer[] allDuplicateInts = new Integer[10];
    private ComparatorPlus<Integer> comp2 = getIntComparator();

    private static ComparatorPlus<Integer> getIntComparator() {
        return new ComparatorPlus<>() {
            @Override
            public int compare(Integer firstInteger, Integer secondInteger) {
                incrementCount();
                return firstInteger.compareTo(secondInteger);
            }
        };
    }

    @Before
    public void setUp3() {
        String duplicate = "Ethan";
        sortedTAs[0] = new TeachingAssistant("Anthony");
        sortedTAs[1] = new TeachingAssistant("Bradley");
        sortedTAs[2] = new TeachingAssistant("Christan");
        sortedTAs[3] = new TeachingAssistant("Drake");
        sortedTAs[4] = new TeachingAssistant(duplicate);
        for (int i = sortedTAs.length - 1, j = 0; i >= 0; i--, j++) {
            reverseSortedTAs[j] = sortedTAs[i];
        }
        for (int i = 0; i < allDuplicateTAs.length; i++) {
            allDuplicateTAs[i] = new TeachingAssistant(duplicate);
        }
        for (int i = 0; i < sortedInts.length; i++) {
            sortedInts[i] = i + 1;
        }
        for (int i = sortedInts.length - 1, j = 0; i >= 0; i--, j++) {
            reverseSortedInts[j] = sortedInts[i];
        }
        for (int i = 0; i < allDuplicateInts.length; i++) {
            allDuplicateInts[i] = 1000;
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortAlreadySortedArray() {
        TeachingAssistant[] outputArray = sortedTAs.clone();
        Sorting.selectionSort(outputArray, comp);
        assertArrayEquals(sortedTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 10 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortAlreadySortedArray2() {
        Integer[] outputArray = sortedInts.clone();
        Sorting.selectionSort(outputArray, comp2);
        assertArrayEquals(sortedInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 45 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortReverseSortedArray() {
        Sorting.selectionSort(reverseSortedTAs, comp);
        assertArrayEquals(sortedTAs, reverseSortedTAs);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 10 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortReverseSortedArray2() {
        Sorting.selectionSort(reverseSortedInts, comp2);
        assertArrayEquals(sortedInts, reverseSortedInts);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 45 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortWithAllDuplicateArray() {
        TeachingAssistant[] outputArray = allDuplicateTAs.clone();
        Sorting.selectionSort(outputArray, comp);
        assertArrayEquals(allDuplicateTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 10 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSortWithAllDuplicateArray2() {
        Integer[] outputArray = allDuplicateInts.clone();
        Sorting.selectionSort(outputArray, comp2);
        assertArrayEquals(allDuplicateInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 45 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortAlreadySortedArray() {
        TeachingAssistant[] outputArray = sortedTAs.clone();
        Sorting.cocktailSort(outputArray, comp);
        assertArrayEquals(sortedTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 4 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortAlreadySortedArray2() {
        Integer[] outputArray = sortedInts.clone();
        Sorting.cocktailSort(outputArray, comp2);
        assertArrayEquals(sortedInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 9 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortReverseSortedArray() {
        Sorting.cocktailSort(reverseSortedTAs, comp);
        assertArrayEquals(sortedTAs, reverseSortedTAs);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 10 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortReverseSortedArray2() {
        Sorting.cocktailSort(reverseSortedInts, comp2);
        assertArrayEquals(sortedInts, reverseSortedInts);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 45 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortWithAllDuplicateArray() {
        TeachingAssistant[] outputArray = allDuplicateTAs.clone();
        Sorting.cocktailSort(outputArray, comp);
        assertArrayEquals(allDuplicateTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 4 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSortWithAllDuplicateArray2() {
        Integer[] outputArray = allDuplicateInts.clone();
        Sorting.cocktailSort(outputArray, comp2);
        assertArrayEquals(allDuplicateInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 9 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortAlreadySortedArray() {
        TeachingAssistant[] outputArray = sortedTAs.clone();
        Sorting.mergeSort(outputArray, comp);
        assertArrayEquals(sortedTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 5 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortAlreadySortedArray2() {
        Integer[] outputArray = sortedInts.clone();
        Sorting.mergeSort(outputArray, comp2);
        assertArrayEquals(sortedInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 15 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortReverseSortedArray() {
        Sorting.mergeSort(reverseSortedTAs, comp);
        assertArrayEquals(sortedTAs, reverseSortedTAs);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 7 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortReverseSortedArray2() {
        Sorting.mergeSort(reverseSortedInts, comp2);
        assertArrayEquals(sortedInts, reverseSortedInts);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 19 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortWithAllDuplicateArray() {
        TeachingAssistant[] outputArray = allDuplicateTAs.clone();
        Sorting.mergeSort(outputArray, comp);
        assertArrayEquals(allDuplicateTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 5 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSortWithAllDuplicateArray2() {
        Integer[] outputArray = allDuplicateInts.clone();
        Sorting.mergeSort(outputArray, comp2);
        assertArrayEquals(allDuplicateInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 15 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithAlreadySortedArray() {
        TeachingAssistant[] outputArray = sortedTAs.clone();
        Sorting.quickSort(outputArray, comp, new Random(234));
        assertArrayEquals(sortedTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 12 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithAlreadySortedArray2() {
        Integer[] outputArray = sortedInts.clone();
        Sorting.quickSort(outputArray, comp2, new Random(234));
        assertArrayEquals(sortedInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 36 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithReverseSortedArray() {
        Sorting.quickSort(reverseSortedTAs, comp, new Random(234));
        assertArrayEquals(sortedTAs, reverseSortedTAs);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 12 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithReverseSortedArray2() {
        Sorting.quickSort(reverseSortedInts, comp2, new Random(234));
        assertArrayEquals(sortedInts, reverseSortedInts);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 36 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithAllDuplicateArray() {
        TeachingAssistant[] outputArray = allDuplicateTAs.clone();
        Sorting.quickSort(outputArray, comp, new Random(234));
        assertArrayEquals(allDuplicateTAs, outputArray);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 10 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSortWithAllDuplicateArray2() {
        Integer[] outputArray = allDuplicateInts.clone();
        Sorting.quickSort(outputArray, comp2, new Random(234));
        assertArrayEquals(allDuplicateInts, outputArray);
        assertTrue("Number of comparisons: " + comp2.getCount(),
                comp2.getCount() <= 45 && comp2.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortAlreadySortedArray() {
        int[] outputArray = Arrays.stream(sortedInts).mapToInt(Integer::intValue).toArray();
        Sorting.lsdRadixSort(outputArray);
        assertArrayEquals(Arrays.stream(sortedInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortReverseSortedArray() {
        int[] outputArray = Arrays.stream(reverseSortedInts).mapToInt(Integer::intValue).toArray();
        Sorting.lsdRadixSort(outputArray);
        assertArrayEquals(Arrays.stream(sortedInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSortWithAllDuplicateArray() {
        int[] outputArray = Arrays.stream(allDuplicateInts).mapToInt(Integer::intValue).toArray();
        Sorting.lsdRadixSort(outputArray);
        assertArrayEquals(Arrays.stream(allDuplicateInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSortAlreadySortedArray() {
        List<Integer> sortedIntList = new ArrayList<>();
        for (int i : sortedInts) {
            sortedIntList.add(i);
        }
        int[] outputArray = Sorting.heapSort(sortedIntList);
        assertArrayEquals(Arrays.stream(sortedInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSortReverseSortedArray() {
        List<Integer> reversedSortedIntsList = new ArrayList<>();
        for (int i : reverseSortedInts) {
            reversedSortedIntsList.add(i);
        }
        int[] outputArray = Sorting.heapSort(reversedSortedIntsList);
        assertArrayEquals(Arrays.stream(sortedInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSortWithAllDuplicateArray() {
        List<Integer> allDuplicateIntsList = new ArrayList<>();
        for (int i : allDuplicateInts) {
            allDuplicateIntsList.add(i);
        }
        int[] outputArray = Sorting.heapSort(allDuplicateIntsList);
        assertArrayEquals(Arrays.stream(allDuplicateInts).mapToInt(Integer::intValue).toArray(), outputArray);
    }

    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public TeachingAssistant(String name) {
            this.name = name;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Set the name of the teaching assistant.
         *
         * @param name name of the teaching assistant
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<TeachingAssistant> getNameComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                                   TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}