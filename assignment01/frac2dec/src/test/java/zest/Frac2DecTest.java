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
  Test 0 input. Combinations of either or both
  FOUND BUG: denominator = 0 does not return null
   */
  @Test
  public void testZero() {
    assertEquals(Frac2Dec.fractionToDecimal(0,0), "0");
    assertEquals(Frac2Dec.fractionToDecimal(0,4), "0");
    assertNull(Frac2Dec.fractionToDecimal(4,0));
  }

}