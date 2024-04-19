package zest;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Tuple;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void testLengthOfLIS_NullInput_ReturnsZero() {
        int[] input = null;
        int expected = 0;
        int actual = new LongestIncreasingSubsequence().lengthOfLIS(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLengthOfLIS_EmptyInput_ReturnsZero() {
        int[] input = {};
        int expected = 0;
        int actual = new LongestIncreasingSubsequence().lengthOfLIS(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testLengthOfLIS_ValidInput_ReturnsMaxLength() {
        int[] input = {1, 3, 2, 4, 5, 7, 6, 8};
        int expected = 6; // The longest increasing subsequence is {1, 2, 4, 5, 7, 8}
        int actual = new LongestIncreasingSubsequence().lengthOfLIS(input);
        assertEquals(expected, actual);
    }

    @Property
        void testLengthOfLIS_Property(
            @ForAll("generateListWithLIS") Tuple.Tuple2<List<Integer>, Integer> tuple
        ) 
        {
        List<Integer> numbers = tuple.get1();
        int[] nums = convertListToArray(numbers);
        int expected = tuple.get2();
        int actual = new LongestIncreasingSubsequence().lengthOfLIS(nums);
        assertEquals(expected, actual);
        }


        @Provide
        Arbitrary<Tuple.Tuple2<List<Integer>, Integer>> generateListWithLIS() {
        return Arbitraries.integers().between(1, 20).flatMap(size ->
            Arbitraries.integers().between(-100000, 100000).flatMap(LIS_start_value ->
            Arbitraries.integers().between(1, size).flatMap(duplicate ->
            Arbitraries.integers().between(0, 0).list().ofSize(size)
                .map(list -> {
                    List<Integer> list_edited = new ArrayList<>();
                    int LIS_length = size;
                    list_edited.add(LIS_start_value);
                    int increased_value = 0;
                    // first create array where LIS length is the same as the size of the array
                    for (int i = 1; i < list.size(); i++) {
                        increased_value = list_edited.get(i-1) + (int) (Math.random() * 100) + 1;
                        list_edited.add(increased_value);
                     
                    }
                    for (int i = 1; i < list.size(); i++) {
                        if (Math.random() < 0.5) {
                            //chance to decrease the value, which will decrease the LIS length
                            list_edited.set(i, list_edited.get(i-1) - ((int) (Math.random() * 100) + 1));
                            LIS_length--;
                        } else if (Math.random() < 0.2) {
                            //Small chance to change value to a duplicate, which will decrease the LIS length
                            list_edited.set(i, list_edited.get(i-1));
                            LIS_length--;
                        }
                    }
                    return Tuple.of(list_edited, LIS_length);
                })
            )
        )
        );
        }

        private int[] convertListToArray(List<Integer> numbers) {
            int[] array = numbers
            .stream()
            .mapToInt(x -> x)
            .toArray();
            return array;
            }

}