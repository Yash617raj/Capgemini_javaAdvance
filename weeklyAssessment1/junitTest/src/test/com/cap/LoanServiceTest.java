package test.com.cap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.com.cap.LoanService;

public class LoanServiceTest {
	LoanService loan;
	
	@BeforeEach
	void setUp() {
		loan = new LoanService();
	}
	
	@Test
	void testValidEligible() {
		assertTrue(loan.isEligible(32, 28000));
	}
	
	@Test
	void testInvalidAge() {
		assertFalse(loan.isEligible(20, 28000));
	}
	
	@Test
	void testInvalidSalary() {
		assertFalse(loan.isEligible(32, 10000));
	}
	
	@Test
	void testEmiCalculation() {
		double emi = loan.calculateEMI(12000, 1);
		assertEquals(emi, 1000);
	}
	
	 @Test
	 void testInvalidLoanAmount() {
	     assertThrows(IllegalArgumentException.class, () -> {
	     loan.calculateEMI(0, 5);
	     });
	 }
	 
	 @Test
	 void testInvalidTenureYear() {
		 assertThrows(IllegalArgumentException.class, ()->{
			 loan.calculateEMI(1000, 0);
		 });
	 }
	 
	 @Test
     void testPremiumCategory() {
	     assertEquals("Premium", loan.getLoanCategory(800));
	 }
	 
	 @Test
	 void testStandardCategory() {
	     assertEquals("Standard", loan.getLoanCategory(700));
	 }

	 
	 @Test
	 void testHighRiskCategory() {
	     assertEquals("High Risk", loan.getLoanCategory(500));
	 }
	 
	 @Test
	 void testBoundaryCase() {
		 assertTrue(loan.isEligible(21,25000));
	 }
	 
	 @Test
	 void testGroupedAssertions() {
	      assertAll("Loan Tests",
	              () -> assertTrue(loan.isEligible(35, 40000)),
	              () -> assertEquals("Premium", loan.getLoanCategory(760)),
	              () -> assertNotNull(loan.calculateEMI(60000, 2))
	      );
	 }
	    
	 
}







