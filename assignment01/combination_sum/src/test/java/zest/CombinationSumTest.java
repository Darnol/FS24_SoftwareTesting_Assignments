package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class CombinationSumTest {
    @Test
    public void example1() {
        int[] in = {2, 3, 6, 7};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(2, 2, 3),
                Arrays.asList(7));
        assertEquals(out, CombinationSum.combinationSum(in, 7));
    }

    @Test
    public void example2() {
        int[] in = {2, 3, 5};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(2, 2, 2, 2),
                Arrays.asList(2, 3, 3),
                Arrays.asList(3, 5));
        assertEquals(out, CombinationSum.combinationSum(in, 8));
    }

    @Test
    public void example3() {
        int[] in = {2};
        List<List<Integer>> out = Arrays.asList();
        assertEquals(out, CombinationSum.combinationSum(in, 1));
    }

    @Test
    public void nrOfElements() {
        int[] in = {5};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(5)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 5));

        in = new int[]{2, 5};
        assertEquals(out, CombinationSum.combinationSum(in, 5));
    }

    @Test
    public void noPossibleCombination() {
        int[] in = {3, 7, 9};
        List<List<Integer>> out = Arrays.asList();
        assertEquals(out, CombinationSum.combinationSum(in, 11));
    }

    @Test
    public void singleCombination() {
        int[] in = {3, 7, 9};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(3, 7)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 10));
    }

    @Test
    public void multipleCombinations() {
        int[] in = {3, 7, 9};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(3, 3, 3, 3, 3, 3),
                Arrays.asList(3, 3, 3, 9),
                Arrays.asList(9, 9)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 18));
    }

    @Test
    public void smallerThanAll() {
        int[] in = {5, 7, 9};
        List<List<Integer>> out = Arrays.asList();
        assertEquals(out, CombinationSum.combinationSum(in, 2));
    }

    @Test
    public void smallerThanSome() {
        int[] in = {3, 5, 14};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(3, 3, 5)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 11));
    }

    @Test
    public void biggerThanAllCombined() {
        int[] in = {3, 7, 9};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3),
                Arrays.asList(3, 3, 3, 3, 3, 9),
                Arrays.asList(3, 3, 9, 9),
                Arrays.asList(3, 7, 7, 7)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 24));
    }

    @Test
    public void zeroTarget() {
        int[] in = {3, 7};
        List<List<Integer>> out = Arrays.asList();
        assertEquals(out, CombinationSum.combinationSum(in, -1));
    }

    @Test
    public void reverse() {
        int[] in = {14, 5, 3};
        List<List<Integer>> out = Arrays.asList(
                Arrays.asList(3, 3, 5)
        );
        assertEquals(out, CombinationSum.combinationSum(in, 11));
    }
}