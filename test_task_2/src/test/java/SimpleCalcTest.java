import org.junit.Assert;
import org.junit.Test;

public class SimpleCalcTest {

    @Test
    public void testAdd(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "8.0";
        String actual = simpleCalc.compute("2+6");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testSub(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "-4.25";
        String actual = simpleCalc.compute("2.25-6.5");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testDiv(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "3.33333";
        String actual = simpleCalc.compute("10/3");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testDivByZero(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "Division by zero";
        String actual = simpleCalc.compute("6/0");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testMult(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "3.3";
        String actual = simpleCalc.compute("0.5*6.6");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPercents(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "14.0";
        String actual = simpleCalc.compute("28%50");

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testPow(){

        SimpleCalc simpleCalc = new SimpleCalc();

        String expected = "27.0";
        String actual = simpleCalc.compute("3.0^3.000000");

        Assert.assertEquals(expected,actual);
    }

}
