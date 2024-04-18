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
import java.util.Collections;
import java.util.List;

class FindDuplicateTest {

    @Test
    void testFindDuplicate_NullArray_ThrowsIllegalArgumentException() {
        int[] nums = null;
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
    }

    @Test
    void testFindDuplicate_ShortArray_ThrowsIllegalArgumentException() {
        int[] nums = {1};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
    }

    @Test
    void testFindDuplicate_InvalidValues_ThrowsIllegalArgumentException() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums2));
    }

    @Test
    void testFindDuplicate_ValidInput_ReturnsDuplicateValue() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 6};
        int duplicate = FindDuplicate.findDuplicate(nums);
        assertEquals(6, duplicate);
        }


 
    @Property
        void testFindDuplicate_Property(
            @ForAll("generateListWithDuplicate") Tuple.Tuple2<List<Integer>, Integer> tuple
        ) 
        {
        List<Integer> numbers = tuple.get1();
        int[] nums = convertListToArray(numbers);
        int duplicate = tuple.get2();
        assertEquals(duplicate, FindDuplicate.findDuplicate(nums));
        }


        @Provide
        Arbitrary<Tuple.Tuple2<List<Integer>, Integer>> generateListWithDuplicate() {
        return Arbitraries.integers().between(1, 20).flatMap(size ->
            Arbitraries.integers().between(0, size).flatMap(duplicate_amount ->
            Arbitraries.integers().between(1, size).flatMap(duplicate ->
            Arbitraries.integers().list().ofSize(size)
                .map(list -> {
                    List<Integer> list_edited = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        list_edited.add(i + 1);
                    }
                    Collections.shuffle(list_edited);
                    // One duplicate is always added
                    list_edited.add(duplicate);
                    for (int i = 0; i < duplicate_amount; i++) {
                                list_edited.set(i, duplicate);
                    }
                    Collections.shuffle(list_edited);
                    // System.out.println(Tuple.of(list_edited, duplicate));
                    return Tuple.of(list_edited, duplicate);
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
    
    