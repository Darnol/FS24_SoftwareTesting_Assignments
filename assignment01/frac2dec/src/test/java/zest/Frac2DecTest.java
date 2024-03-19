package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Frac2DecTest {

  @Test
  public void testSomeInputs(){
    Integer num;
    Integer denom;
    num = 1;
    denom = 4;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = -1;
    denom = 4;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = -1;
    denom = -4;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = 3;
    denom = 13;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = 1;
    denom = 3;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = 0;
    denom = 3;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));

    num = 0;
    denom = 0;
    System.out.println(Frac2Dec.fractionToDecimal(num,denom));
  }

  /*
  Test NULL not needed
   */

  /*
  FOUND BUG: denominator = 0 does not return null
   */
  @Test
  public void T1_test_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(0,0), "0");
    assertEquals(Frac2Dec.fractionToDecimal(0,4), "0");
    assertEquals(Frac2Dec.fractionToDecimal(0,-4), "0");
    assertNull(Frac2Dec.fractionToDecimal(4,0));
  }

  @Test
  public void T2_test_both_positive() {
    assertEquals(Frac2Dec.fractionToDecimal(2,5), "0.4");
  }

  @Test
  public void T3_test_both_negative() {
    assertEquals(Frac2Dec.fractionToDecimal(-2,-5), "0.4");
  }

  @Test
  public void T4_test_different_signs() {
    assertEquals(Frac2Dec.fractionToDecimal(2,-5), "-0.4");
    assertEquals(Frac2Dec.fractionToDecimal(-2,5), "-0.4");
  }

  @Test
  public void T5_test_integer_greater_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(2,1), "2");
    assertEquals(Frac2Dec.fractionToDecimal(9,3), "3");
  }

  @Test
  public void T6_test_integer_smaller_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(-4,1), "-4");
    assertEquals(Frac2Dec.fractionToDecimal(300,-30), "-10");
  }

  @Test
  public void T7_test_nonrep_fraction_greater_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(3,2), "1.5");
    assertEquals(Frac2Dec.fractionToDecimal(1001,100), "10.01");
    assertEquals(Frac2Dec.fractionToDecimal(12375,10000), "1.2375");
  }

  @Test
  public void T8_test_nonrep_fraction_between_zero_one() {
    assertEquals(Frac2Dec.fractionToDecimal(1,2), "0.5");
    assertEquals(Frac2Dec.fractionToDecimal(9999999,10000000), "0.9999999");
  }

  @Test
  public void T9_test_nonrep_fraction_smaller_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(-1,2), "-0.5");
    assertEquals(Frac2Dec.fractionToDecimal(123456789,-10), "-12345678.9");
  }

  @Test
  public void T10_test_rep_fraction_greater_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(4,3), "1.(3)");
    assertEquals(Frac2Dec.fractionToDecimal(100,99), "1.(01)");
    assertEquals(Frac2Dec.fractionToDecimal(10,7), "1.(428571)");
  }

  @Test
  public void T11_test_rep_fraction_between_zero_one() {
    assertEquals(Frac2Dec.fractionToDecimal(1,3), "0.(3)");
    assertEquals(Frac2Dec.fractionToDecimal(1,99), "0.(01)");
    assertEquals(Frac2Dec.fractionToDecimal(1,7), "0.(142857)");
  }

  @Test
  public void T12_test_rep_fraction_smaller_zero() {
    assertEquals(Frac2Dec.fractionToDecimal(1,-3), "-0.(3)");
    assertEquals(Frac2Dec.fractionToDecimal(-1,99), "-0.(01)");
    assertEquals(Frac2Dec.fractionToDecimal(10,-7), "-1.(428571)");
  }

  @Test
  public void T13_same_input_both_signs() {
    assertEquals(Frac2Dec.fractionToDecimal(1,1), "1");
    assertEquals(Frac2Dec.fractionToDecimal(5000,5000), "1");
    assertEquals(Frac2Dec.fractionToDecimal(-1,-1), "1");
    assertEquals(Frac2Dec.fractionToDecimal(-5000,-5000), "1");
    assertEquals(Frac2Dec.fractionToDecimal(-1,1), "-1");
    assertEquals(Frac2Dec.fractionToDecimal(1,-1), "-1");
  }

  @Test
  public void T14_octal_input() {
    assertEquals(Frac2Dec.fractionToDecimal(01,01), "1");
    assertEquals(Frac2Dec.fractionToDecimal(010,010), "1");
    // 012 in octal is 10, 036 in octal is 30
    assertEquals(Frac2Dec.fractionToDecimal(012,036), "0.(3)");
  }



}