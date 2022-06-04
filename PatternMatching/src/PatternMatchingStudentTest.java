import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * This is a basic set of unit tests for PatternMatching.
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
 * @author CS 1332 TAs + Kevin Jiang + Tomer Shmul
 * @version 2.0
 */
public class PatternMatchingStudentTest {

    private static final int TIMEOUT = 200;

    private String kmpPattern;
    private String kmpText;
    private String kmpNoMatch;

    private List<Integer> kmpAnswer;
    private List<Integer> kmpPatternEquivalencyAnswer;

    private String bm1Pattern;
    private String bm1Text;
    private List<Integer> bm1Answer;

    private String bm2Pattern;
    private String bm2Text;
    private List<Integer> bm2Answer;

    private String kmp1Pattern;
    private String kmp1Text;
    private List<Integer> kmp1Answer;

    private String kmp2Pattern;
    private String kmp2Text;
    private List<Integer> kmp2Answer;

    private String test4Pattern;
    private String test4Text;
    private List<Integer> test4Answer;

    private String kmp3Pattern;
    private String kmp3Text;
    private List<Integer> kmp3Answer;

    private String sellPattern;
    private String sellText;
    private String sellNoMatch;
    private List<Integer> sellAnswer;

    private String multiplePattern;
    private String multipleText;
    private List<Integer> multipleAnswer;

    private List<Integer> emptyList;

    private CharacterComparator comparator;

    //Text source: https://www.lipsum.com/
    private String longtext = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pulvinar lorem id neque volutpat lobortis consectetur";
    private String longtextPattern = "consectetur";


    @Before
    public void setUp() {

        test4Pattern = "aab";
        test4Text = "aabcaabdaab";
        test4Answer = new ArrayList<>();
        test4Answer.add(0);
        test4Answer.add(4);
        test4Answer.add(8);

        kmpPattern = "ababa";
        kmpText = "ababaaababa";
        kmpNoMatch = "ababbaba";

        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(0);
        kmpAnswer.add(6);

        kmp1Pattern = "ab";
        kmp1Text = "abcd";
        kmp1Answer = new ArrayList<>();
        kmp1Answer.add(0);

        kmp2Pattern = "aa";
        kmp2Text = "aaaaab";
        kmp2Answer = new ArrayList<>();
        kmp2Answer.add(0);
        kmp2Answer.add(1);
        kmp2Answer.add(2);
        kmp2Answer.add(3);

        kmp3Pattern = "abcdefg";
        kmp3Text = "zxyabcdefgzxy";
        kmp3Answer = new ArrayList<>();
        kmp3Answer.add(3);

        bm1Pattern = "aa";
        bm1Text = "aaaaab";
        bm1Answer = new ArrayList<>();
        bm1Answer.add(0);
        bm1Answer.add(1);
        bm1Answer.add(2);
        bm1Answer.add(3);

        bm2Pattern = "he";
        bm2Text = "she he she he";
        bm2Answer = new ArrayList<>();
        bm2Answer.add(1);
        bm2Answer.add(4);
        bm2Answer.add(8);
        bm2Answer.add(11);

        kmpPatternEquivalencyAnswer = new ArrayList<>();
        kmpPatternEquivalencyAnswer.add(0);

        sellPattern = "sell";
        sellText = "She sells seashells by the seashore.";
        sellNoMatch = "sea lions trains cardinal boardwalk";

        sellAnswer = new ArrayList<>();
        sellAnswer.add(4);

        multiplePattern = "ab";
        multipleText = "abab";

        multipleAnswer = new ArrayList<>();
        multipleAnswer.add(0);
        multipleAnswer.add(2);

        emptyList = new ArrayList<>();

        comparator = new CharacterComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        /*
            pattern: ababa
            failure table: [0, 0, 1, 2, 3]
            comparisons: 4
         */
        int[] failureTable = PatternMatching
                .buildFailureTable(kmpPattern, comparator);
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM1() {
        /*
            pattern: aaaaab
            text: aa
            indices: 0, 1, 2, 3
            expected total comparison: 9
            a / a / a / a / a / b
            --+---+---+---+---+---
            a / a / - / - / - / -
            - / a / a / - / - / -
            - / - / a / a / - / -
            - / - / - / a / a / -
            - / - / - / - / a / a
         */
        assertEquals(bm1Answer,
                PatternMatching.boyerMoore(bm1Pattern, bm1Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM2() {
        /*
            pattern: she he she he
            text: he
            indices: 1, 4, 8, 11
            expected total comparison: 13
            s / h / e /   / h / e /   / s / h / e /   / h / e
            --+---+---+---+---+---+---+---+---+---+---+---+---
            h / e / - / - / - / - / - / - / - / - / - / - / -
            - / h / e / - / - / - / - / - / - / - / - / - / -
            - / - / h / e / - / - / - / - / - / - / - / - / -
            - / - / - / - / h / e / - / - / - / - / - / - / -
            - / - / - / - / - / h / e / - / - / - / - / - / -
            - / - / - / - / - / - / - / h / e / - / - / - / -
            - / - / - / - / - / - / - / - / h / e / - / - / -
            - / - / - / - / - / - / - / - / - / h / e / - / -
            - / - / - / - / - / - / - / - / - / - / - / h / e
         */
        assertEquals(bm2Answer,
                PatternMatching.boyerMoore(bm2Pattern, bm2Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 13.", 13, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBM3() {
        assertEquals(test4Answer,
                PatternMatching.boyerMoore(test4Pattern, test4Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 11.", 11, comparator.getComparisonCount());
    }
    @Test(timeout = TIMEOUT)
    public void testKMP4() {
        assertEquals(test4Answer,
                PatternMatching.kmp(test4Pattern, test4Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 14.", 14, comparator.getComparisonCount());
    }
    @Test(timeout = TIMEOUT)
    public void testRK4() {
        assertEquals(test4Answer,
                PatternMatching.rabinKarp(test4Pattern, test4Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPMatch() {
        /*
            pattern: ababa
            text: ababaaababa
            indices: 0, 6
            expected total comparison: 18
            failure table: [0, 0, 1, 2, 3]
            comparisons: 4
        a | b | a | b | a | a | a | b | a | b | a
        --+---+---+---+---+---+---+---+---+---+---
        a | b | a | b | a |   |   |   |   |   |
        - | - | - | - | - |   |   |   |   |   |         comparisons: 5
          |   | a | b | a | b | a |   |   |   |
          |   |   |   |   | - |   |   |   |   |         comparisons: 1
          |   |   |   | a | b | a | b | a |   |
          |   |   |   |   | - |   |   |   |   |         comparisons: 1
          |   |   |   |   | a | b | a | b | a |
          |   |   |   |   | - | - |   |   |   |         comparisons: 2
          |   |   |   |   |   | a | b | a | b | a
          |   |   |   |   |   | - | - | - | - | -       comparisons: 5
         comparisons: 14
         */
        assertEquals(kmpAnswer,
                PatternMatching.kmp(kmpPattern, kmpText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 18.", 18, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP1() {
        /*
            pattern: ab
            text: abcd
            indices: 0
            expected total comparison: 4
            failure table: [0, 0]
            comparisons: 1
            a / b / c / d
            --+---+---+---
            a / b /   /
            - / - / a / b
         */
        assertEquals(kmp1Answer,
                PatternMatching.kmp(kmp1Pattern, kmp1Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP2() {
        /*
            pattern: aaaaab
            text: aa
            indices: 0, 1, 2, 3
            expected total comparison: 7
            failure table: [0, 1]
            comparisons: 1
            a / a / a / a / a / b
            --+---+---+---+---+---
            a / a / - / - / - / -
            - / a / a / - / - / -
            - / - / a / a / - / -
            - / - / - / a / a / -
            - / - / - / - / a / a
         */
        assertEquals(kmp2Answer,
                PatternMatching.kmp(kmp2Pattern, kmp2Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 7.", 7, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMP3() {
        /*
            pattern: abcdefg
            text: zxyabcdefgzxy
            indices: 0
            expected total comparison: 16
            failure table: [0, 0, 0, 0, 0, 0, 0]
            comparisons: 6
            z / x / y / a / b / c / d / e / f / g / z / x / y
            --+---+---+---+---+---+---+---+---+---+---+---+---
            a / b / c / d / e / f / g / - / - / - / - / - / -
            - / a / b / c / d / e / f / g / - / - / - / - / -
            - / - / a / b / c / d / e / f / g / - / - / - / -
            - / - / - / a / b / c / d / e / f / g / - / - / -
         */
        assertEquals(kmp3Answer,
                PatternMatching.kmp(kmp3Pattern, kmp3Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 16.", 16, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNoMatch() {
        /*
            pattern: ababa
            text: ababbaba
            indices: -
            expected total comparison: 10
            failure table: [0, 0, 1, 2, 3]
            comparisons: 4
        a | b | a | b | b | a | b | a
        --+---+---+---+---+---+---+---
        a | b | a | b | a |   |   |
        - | - | - | - | - |   |   |     comparisons: 5
          |   | a | b | a | b | a |
          |   |   |   | - |   |   |     comparisons: 1
        comparisons: 6
         */
        assertEquals(emptyList,
                PatternMatching.kmp(kmpPattern, kmpNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 10.", 10, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPLongerText() {
        /*
            pattern: ababbaba
            text: ababa
            indices: -
            expected total comparison: 0
         */
        assertEquals(emptyList,
                PatternMatching.kmp(kmpNoMatch, kmpPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPPatternEqualsText() {
        /*
            pattern: ababa
            text: ababa
            indices: 0
            expected total comparison: 5 or 9 (if failure table is built)
         */
        assertEquals(kmpPatternEquivalencyAnswer,
                PatternMatching.kmp(kmpPattern, kmpPattern, comparator));
        assertTrue("Comparison count is different than expected",
                comparator.getComparisonCount() == 5
                        || comparator.getComparisonCount() == 9);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        /*
            pattern: sell
            last table: {s : 0, e : 1, l : 3}
         */
        Map<Character, Integer> lastTable = PatternMatching
                .buildLastTable(sellPattern);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('s', 0);
        expectedLastTable.put('e', 1);
        expectedLastTable.put('l', 3);
        assertEquals(expectedLastTable, lastTable);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreMatch() {
        /*
            pattern: sell
            text: She sells seashells by the seashore.
            indices: 4
            expected total comparisons: 20
         */
        assertEquals(sellAnswer,
                PatternMatching.boyerMoore(sellPattern, sellText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 20.", 20, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNoMatch() {
        /*
            pattern: sell
            text: sea lions trains cardinal boardwalk
            indices: -
            expected total comparisons: 9
         */
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sellPattern,
                        sellNoMatch, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreMultipleMatches() {
        /*
            pattern: ab
            text: abab
            indices: 0, 2
            expected total comparisons: 5
         */
        assertEquals(multipleAnswer,
                PatternMatching.boyerMoore(multiplePattern,
                        multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreLongerText() {
        /*
            pattern: sea lions trains cardinal boardwalk
            text: sell
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.boyerMoore(sellNoMatch,
                        sellPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpMatch() {
        /*
            pattern: sell
            text: She sells seashells by the seashore.
            indices: 4
            expected total comparisons: 4
         */
        assertEquals(sellAnswer,
                PatternMatching.rabinKarp(sellPattern, sellText, comparator));
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNoMatch() {
        /*
            pattern: sell
            text: sea lions trains cardinal boardwalk
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.rabinKarp(sellPattern,
                        sellNoMatch, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpMultipleMatches() {
        /*
            pattern: ab
            text: abab
            indices: 0, 2
            expected total comparisons: 4
         */
        assertEquals(multipleAnswer,
                PatternMatching.rabinKarp(multiplePattern,
                        multipleText, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 4.", 4, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRK1() {
        /*
            pattern: ab
            text: abcd
            indices: 0
            expected total comparison: 2
         */
        assertEquals(kmp1Answer,
                PatternMatching.rabinKarp(kmp1Pattern, kmp1Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 2.", 2, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRK2() {
        /*
            pattern: aaaaab
            text: aa
            indices: 0, 1, 2, 3
            expected total comparison: 8
         */
        assertEquals(kmp2Answer,
                PatternMatching.rabinKarp(kmp2Pattern, kmp2Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 8.", 8, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRK3() {
        /*
            pattern: abcdefg
            text: zxyabcdefgzxy
            indices: 0
            expected total comparison: 7
         */
        assertEquals(kmp3Answer,
                PatternMatching.rabinKarp(kmp3Pattern, kmp3Text, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 7.", 7, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpLongerText() {
        /*
            pattern: sea lions trains cardinal boardwalk
            text: sell
            indices: -
            expected total comparisons: 0
         */
        assertEquals(emptyList,
                PatternMatching.rabinKarp(sellNoMatch,
                        sellPattern, comparator));
        assertEquals(0, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpEqualHash() {
        /*
            These are characters with ASCII values as shown, not the actual
            characters shown. Most do not have actual characters.
            pattern: 011
            text: 00(114)011
            indices: 2
            expected total comparisons: 5
         */
        List<Integer> answer = new ArrayList<>();
        answer.add(3);
        assertEquals(answer,
                PatternMatching.rabinKarp("\u0000\u0001\u0001",
                        "\u0000\u0000\u0072\u0000\u0001\u0001", comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 5.", 5, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreGalil() {
        /*
            pattern: abacab
            text: abacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabac
         */
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= 68; i += 4) {
            arr.add(i);
        }


        assertEquals(arr,
                PatternMatching.boyerMooreGalilRule("abacab",
                        "abacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabac", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 80.", 80, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreCompareGalil() {
        /*
            pattern: abacab
            text: abacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabac
         */
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= 68; i += 4) {
            arr.add(i);
        }

        assertEquals(arr,
                PatternMatching.boyerMoore("abacab",
                        "abacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabacabac", comparator));
      //  System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 144.", 144, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreGalil2() {
        /*
            pattern: ab
            text: abababababababababababababababababababababab
         */
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= 42; i += 2) {
            arr.add(i);
        }

        assertEquals(arr,
                PatternMatching.boyerMooreGalilRule("ab",
                        "abababababababababababababababababababababab", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 45.", 45, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreCompareGalil2() {
        /*
            pattern: ab
            text: abababababababababababababababababababababab
         */
        List<Integer> arr = new ArrayList<>();
        for(int i = 0; i <= 42; i += 2) {
            arr.add(i);
        }

        assertEquals(arr,
                PatternMatching.boyerMoore("ab",
                        "abababababababababababababababababababababab", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 65.", 65, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreGalil3() {
        /*
            pattern: abc
            text: abcabcdefafjasdjabcabcdeabdabc
         */
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(3);
        arr.add(16);
        arr.add(19);
        arr.add(27);

        assertEquals(arr,
                PatternMatching.boyerMooreGalilRule("abc",
                        "abcabcdefafjasdjabcabcdeabdabc", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 23.", 23, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreCompareGalil3() {
        /*
            pattern: abc
            text: abcabcdefafjasdjabcabcdeabdabc
         */
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(3);
        arr.add(16);
        arr.add(19);
        arr.add(27);

        assertEquals(arr,
                PatternMatching.boyerMoore("abc",
                        "abcabcdefafjasdjabcabcdeabdabc", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 25.", 25, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreDontGoBackwards() {
        /*
            pattern: ababa
            text: abababaabababa
         */
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(2);
        arr.add(7);
        arr.add(9);

        assertEquals(arr,
                PatternMatching.boyerMoore("ababa",
                        "abababaabababa", comparator));
       // System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 30.", 30, comparator.getComparisonCount());
    }
    @Test(timeout = TIMEOUT)
    public void testBoyerMooreGalilDontGoBackwards() {
        /*
            pattern: ababa
            text: abababaabababa
         */
        List<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(2);
        arr.add(7);
        arr.add(9);

        assertEquals(arr,
                PatternMatching.boyerMooreGalilRule("ababa",
                        "abababaabababa", comparator));
        //System.out.println(comparator.getComparisonCount());
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);

        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 24.", 24, comparator.getComparisonCount());
    }

    /**
     * Shravan Cheekati
     */
    //EXCEPTION TESTS
    //KMP tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPExceptionNullPattern() {
        PatternMatching.kmp(null, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPExceptionPattern0len() {
        CharSequence pattern = "";
        PatternMatching.kmp(pattern, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPExceptionTextnull() {
        PatternMatching.kmp(kmpPattern, null, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPExceptionComparatornull() {
        PatternMatching.kmp(kmpPattern, kmpText, null);
    }

    //KMP - bft tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPbftExceptionPatternnull() {
        PatternMatching.buildFailureTable(null, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testKMPbftExceptionComparatornull() {
        PatternMatching.buildFailureTable(kmpPattern, null);
    }


    //BM tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMExceptionNullPattern() {
        PatternMatching.boyerMoore(null, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMExceptionPattern0len() {
        CharSequence pattern = "";
        PatternMatching.boyerMoore(pattern, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMExceptionTextnull() {
        PatternMatching.boyerMoore(bm1Pattern, null, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMExceptionComparatornull() {
        PatternMatching.boyerMoore(bm1Pattern, kmpText, null);
    }

    //BM - lot tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMlotExceptionNullPattern() {
        PatternMatching.buildLastTable(null);
    }

    //RK tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRKExceptionNullPattern() {
        PatternMatching.rabinKarp(null, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRKExceptionPattern0len() {
        CharSequence pattern = "";
        PatternMatching.rabinKarp(pattern, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRKExceptionTextnull() {
        PatternMatching.rabinKarp(kmpPattern, null, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRKExceptionComparatornull() {
        PatternMatching.rabinKarp(kmpPattern, kmpText, null);
    }

    //BM-galil tests
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMGExceptionNullPattern() {
        PatternMatching.boyerMooreGalilRule(null, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMGExceptionPattern0len() {
        CharSequence pattern = "";
        PatternMatching.boyerMooreGalilRule(pattern, test4Text, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMGExceptionTextnull() {
        PatternMatching.boyerMooreGalilRule(bm1Pattern, null, comparator);
    }
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBMGExceptionComparatornull() {
        PatternMatching.boyerMooreGalilRule(bm1Pattern, kmpText, null);
    }

    //END OF EXCEPTION TESTS

    @Test(timeout = TIMEOUT)
    public void testBFTRepeatChars() {        /*
            pattern: aaaaaaaaaa
            failure table: [0,1,2,3,4,5,6,7,8,9]
            comparisons: 9
         */
        int[] failureTable = PatternMatching
                .buildFailureTable("aaaaaaaaaa", comparator);
        int[] expected = {0,1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTableNone() {        /*
            pattern: abcdefghij
            failure table: [0,0,0,0,0,0,0,0,0,0]
            comparisons: 9
         */
        int[] failureTable = PatternMatching
                .buildFailureTable("abcdefghij", comparator);
        int[] expected = {0,0,0,0,0,0,0,0,0,0};
        assertArrayEquals(expected, failureTable);
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 9.", 9, comparator.getComparisonCount());
    }


    //long text tests for each algo
    @Test(timeout = TIMEOUT)
    public void testKMPReallyLong() {       /*
        text: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pulvinar lorem id neque volutpat lobortis consectetur
        pattern: consectetur
        comparisons expected: 135
     */

        List<Integer> ans = new ArrayList<>();
        ans.add(28);
        ans.add(112);

        assertEquals(ans, PatternMatching.kmp(longtextPattern, longtext, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 135.", 135, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBMReallyLong() {       /*
        text: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pulvinar lorem id neque volutpat lobortis consectetur
        pattern: consectetur
        comparisons expected: 39 fast!
     */

        List<Integer> ans = new ArrayList<>();
        ans.add(28);
        ans.add(112);

        assertEquals(ans, PatternMatching.boyerMoore(longtextPattern, longtext, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 39.", 39, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testRKReallyLong() {       /*
        text: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pulvinar lorem id neque volutpat lobortis consectetur
        pattern: consectetur
        comparisons expected: 22 faster!
     */

        List<Integer> ans = new ArrayList<>();
        ans.add(28);
        ans.add(112);

        assertEquals(ans, PatternMatching.rabinKarp(longtextPattern, longtext, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 22.", 22, comparator.getComparisonCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBMGReallyLong() {       /*
        text: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pulvinar lorem id neque volutpat lobortis consectetur
        pattern: consectetur
        comparisons expected: 46??
     */

        List<Integer> ans = new ArrayList<>();
        ans.add(28);
        ans.add(112);

        assertEquals(ans, PatternMatching.boyerMooreGalilRule(longtextPattern, longtext, comparator));
        assertTrue("Did not use the comparator.",
                comparator.getComparisonCount() != 0);
        assertEquals("Comparison count was " + comparator.getComparisonCount()
                + ". Should be 46.", 46, comparator.getComparisonCount());
    }

}