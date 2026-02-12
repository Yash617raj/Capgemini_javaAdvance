package test.com.cap;

import main.com.cap.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {
	StudentService service;
	
	@BeforeEach
	void setUp() {
		service = new StudentService();
	}
	
	@Test
	void isAgeEligible() {
		assertTrue(service.isEligible(20));
	}
	
	@Test
	void isAgeNotEligible() {
		assertFalse(service.isEligible(5));
	}
	
	@Test
	void isAgeEquals() {
		assertEquals(true,service.isEligible(45));
	}
	
	@Test
	void isAgeNotEquals() {
		assertNotEquals(false,service.isEligible(45));
	}
	
	@Test
	void isAgeNotnull() {
		assertNotNull(service);
	}
	
	@Test
	void isAgeSame() {
		Boolean flag1 = true;
		Boolean flag2 = service.isEligible(33);
		assertSame(flag1, flag2);
	}
	
	@Test
	void isAgeNotSame() {
		Boolean flag1 = false;
		boolean flag2 = service.isEligible(33);
		assertNotSame(flag1, flag2);
	}
	
	@Test
	void isMultipleAge() {
		 assertAll(
				 ()-> assertTrue(service.isEligible(48)),
				 ()-> assertFalse(service.isEligible(9)),
				 ()-> assertNotNull(service)
		);
	}
	
	@Test
	void isAgeValid() {
		assertThrows(IllegalArgumentException.class, ()->service.isEligible(-9));
	}
	
	
	
	@Test
	void testFailUsage() {
	    try {
	        service.isEligible(-1);
	    } catch (IllegalArgumentException e) {
	        return; 
	    }
	    fail("Exception expected for negative age");
	}
	
}
