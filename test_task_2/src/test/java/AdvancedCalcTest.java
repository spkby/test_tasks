import org.junit.Assert;
import org.junit.Test;

public class AdvancedCalcTest {


    @Test
    public void checkDivByZero(){

        AdvancedCalc calc = new AdvancedCalc();

        String expected = "Division by zero";
        String actual = calc.compute("10.22+43 / 0 + 123");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testCompute1(){

        AdvancedCalc calc = new AdvancedCalc();

        String expected = "142.22012";
        String actual = calc.compute("5*2+10.22-43 / 43 + 123.00012");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testCompute2(){

        AdvancedCalc calc = new AdvancedCalc();

        String expected = "2.99923";
        String actual = calc.compute("1+2-3*4/5^6");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testComputeWithParentheses(){

        AdvancedCalc calc = new AdvancedCalc();

        String expected = "10.47904";
        String actual = calc.compute("10.22+43 / (43 + 123.00012)");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testComputeWithSqrt(){

        AdvancedCalc calc = new AdvancedCalc();

        /*

        symbol '√' conflicts with gradle

         */

/*/
        String expected = "3836.8997";
        String actual = calc.compute("10.22+43^2 / sin(43 + 123.00012)");
/*/
        String expected = "153.73018";
        String actual = calc.compute("10.22+43^2 / √(43 + 123.00012)");
//*/
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testComputeWithSqrtNegative(){

        AdvancedCalc calc = new AdvancedCalc();

        /*

        symbol '√' conflicts with gradle

         */

//*/

        String expected = "Root is negative";
        String actual = calc.compute("10.22+√(43 - 123.00012)");
/*/
        String expected = "11.2139";
        String actual = calc.compute("10.22+sin(43 - 123.00012)");
//*/
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testComputeWithTrigonometry(){

        AdvancedCalc calc = new AdvancedCalc();

        String expected = "10.23808";
        String actual = calc.compute("10.22+tg(4+3)^2 / (43 + sin(123.2 + cos(-12)))");

        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalSymbol(){
        AdvancedCalc calc = new AdvancedCalc();
        calc.compute("10+f+9");
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalParenthesesMoreOpening(){
        AdvancedCalc calc = new AdvancedCalc();
        calc.compute("10+(1+(9)");
    }

    @Test(expected = IllegalStateException.class)
    public void testIllegalParenthesesMoreClosing(){
        AdvancedCalc calc = new AdvancedCalc();
        calc.compute("10+(1+(9))+12)");
    }


}
