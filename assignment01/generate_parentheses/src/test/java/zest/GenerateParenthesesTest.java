package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GenerateParenthesesTest {

//  @Test
//  public void test_someInputs() {
//    System.out.println(GenerateParentheses.generateParentheses(1));
//    System.out.println(GenerateParentheses.generateParentheses(2));
////    System.out.println(GenerateParentheses.generateParentheses(10));
//    System.out.println(GenerateParentheses.generateParentheses(0));
//    System.out.println(GenerateParentheses.generateParentheses(-1));
//    System.out.println(GenerateParentheses.generateParentheses(-2));
//  }

  @Test
  public void T1_() {

  }

  List<String> emptyList = new ArrayList<>();
  List<String> result1 = Arrays.asList("()");
  List<String> result2 = Arrays.asList("(())","()()");
  int sizeResult8 = 1430;

  @Test
  public void T1_neg_off_point_first_partition() {
    assertEquals(emptyList, GenerateParentheses.generateParentheses(-5));
  }

  @Test
  public void T2_neg_off_point_at_zero_first_partition() {
    assertEquals(emptyList, GenerateParentheses.generateParentheses(-1));
  }

  @Test
  public void T3_off_point_first_partition() {
    assertEquals(emptyList, GenerateParentheses.generateParentheses(0));
  }

  @Test
  public void T4_on_point_first_partition() {
    assertEquals(result1, GenerateParentheses.generateParentheses(1));
  }

  @Test
  public void T5_in_point_first_partition() {
    assertEquals(result2, GenerateParentheses.generateParentheses(2));
  }

  @Test
  public void T6_off_point_second_partition() {
    assertEquals(sizeResult8, GenerateParentheses.generateParentheses(8).size());
  }

  @Test
  public void T7_on_point_second_partition() {
    assertNull(GenerateParentheses.generateParentheses(9));
  }

  @Test
  public void T8_in_point_second_partition() {
    assertNull(GenerateParentheses.generateParentheses(15));
  }

}