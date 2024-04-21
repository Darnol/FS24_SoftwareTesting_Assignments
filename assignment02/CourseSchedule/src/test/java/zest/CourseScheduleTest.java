package zest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Combinators;

class CourseScheduleTest {
    private CourseSchedule cs = new CourseSchedule();
    int[][] prereqNoCycle = {{1,0}, {1,2}};
    int[][] prereqHasCycle = {{1,0}, {0, 2}, {2, 3}, {3, 1}};
    int[][] prereqEmpty = {};
    int[][] prereqNull = null;
    int[][] prereqSelfDependency = {{1,0}, {0, 2}, {2, 2}};

    @Test()
    @DisplayName("If numCourses is negative, must throw exception")
    void testNumCoursesNegative() {
        assertThrows(RuntimeException.class, () -> {
            cs.canFinish(-20, prereqNoCycle);
        });
    }

    @Test()
    @DisplayName("If numCourses is 0, must throw exception")
    void testNumCoursesZero() {
        assertThrows(RuntimeException.class, () -> {
            cs.canFinish(0, prereqNoCycle);
        });
    }

    @Test()
    @DisplayName("If numCourses is positive an prerequisites have no cycle, must return true")
    void testNumCoursesPositive() {
        assertTrue(cs.canFinish(4, prereqNoCycle));
    }

/*    @Test
    @DisplayName("If n is not an integer, must throw exception")
    void testNonInteger() {
        assertThrows(IllegalArgumentException.class, () -> {
            cs.canFinish(12.4, prereqNoCycle);
        });
    }*/

    @Test()
    @DisplayName("If numCourses is positive an prerequisites have cycles, must return false")
    void testPrerequisitesWithCycle() {
        assertFalse(cs.canFinish(4, prereqHasCycle));
    }

    @Test()
    @DisplayName("If prerequisites is null, must throw exception")
    void testPrerequisitesNull() {
        assertThrows(RuntimeException.class, () -> {
            cs.canFinish(4, prereqNull);
        });
    }

    @Test()
    @DisplayName("If prerequisites is empty, must return true")
    void testPrerequisitesEmpty() {
        assertTrue(cs.canFinish(4, prereqEmpty));
    }

    @Test()
    @DisplayName("If prerequisites has self dependency, must throw exception")
    void testPrerequisitesSelfDependency() {
        assertThrows(RuntimeException.class, () -> {
            cs.canFinish(4, prereqSelfDependency);
        });
    }

    @Test()
    @DisplayName("If prerequisites has invalid prerequisite pair, must throw exception")
    void testPrerequisiteInvalidPair() {
        assertThrows(RuntimeException.class, () -> {
            cs.canFinish(2, prereqNoCycle);
        });
    }

    @Property
    void returnsTrueIfNoCycles(
            @ForAll("prerequisitesWithNoCycles")
            int[][] prereqs) {
        boolean result = cs.canFinish(50, prereqs);
        assertTrue(result, "The result must always be true");
    }

    @Property
    void returnsFalseIfCycles(
            @ForAll("prerequisitesWithCycles")
            int[][] prereqs) {
        boolean result = cs.canFinish(50, prereqs);
        assertFalse(result, "The result must always be false");
    }

    @Provide
    Arbitrary<int[][]> prerequisitesWithNoCycles() {
        Arbitrary<Integer> course1 = Arbitraries.integers().between(0, 24);
        Arbitrary<Integer> course2 = Arbitraries.integers().between(25, 49);
        return Combinators.combine(course1, course2).as((c1, c2) -> new int[][]{{c1, c2}});
    }

    @Provide
    Arbitrary<int[][]> prerequisitesWithCycles() {
        Arbitrary<Integer> course1 = Arbitraries.integers().between(0, 24);
        Arbitrary<Integer> course2 = Arbitraries.integers().between(25, 49);
        return Combinators.combine(course1, course2).as((c1, c2) -> new int[][]{{c1, c2}, {c2, c1}});
    }

}
