package test.com.cap;

import main.com.cap.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSub() {
        assertEquals(-1, calc.Sub(2, 3));
    }

    @Test
    void testIsEven() {
        assertTrue(calc.isEven(4));
    }
    
//    @Test
//    void testDivideByZero() {
//        IllegalArgumentException ex = assertThrows(
//            IllegalArgumentException.class,() -> calc.divide(10, 0)
//        );
//    }


    @Test
    void testDivide() {
//        assertEquals(2, calc.divide(4, 2));
    	ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calc.divide(8, 0));
    	assertSame("Cannot divide by zero", ex.getMessage());
    	assertNotSame("hello", ex.getMessage());
    }
    
    @Test
    void testOdd() {
    	assertNull(calc.isOdd(6));
    	assertNotNull(calc.isOdd(5));

    }
    
    @ParameterizedTest
	@CsvSource({
		"2,3,5",
		"0,0,0",
		"-5,10,5",
		"100,200,300"
	})
	public void testPara1(int a,int b,int expected) {
		assertEquals(expected, calc.add(a, b));
	}
    
    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8,10,0,-2,-4})
    
    public void testPara2(int number) {
    	assertTrue(calc.isEven(number));
    }
    
    @ParameterizedTest
    @MethodSource("provideDivisionTestCases")
    public void testPara3(int a, int b, int expected) {
    	assertEquals(expected,calc.divide(a, b));
    }
    
    private static Stream<Arguments> provideDivisionTestCases(){
    	return Stream.of(
    			Arguments.of(20,4,5),
    			Arguments.of(15,5,3),
    			Arguments.of(0,5,0)
    			);
    }
    
    @ParameterizedTest
    @CsvFileSource(files = "testData/add.csv",numLinesToSkip=1)
    public void simpleAddTest(int a, int b, int c) {
    	System.out.println("Simple @csvFileSources test: "+a+", "+b);
    	assertEquals(c, calc.add(a, b));
    }
}
