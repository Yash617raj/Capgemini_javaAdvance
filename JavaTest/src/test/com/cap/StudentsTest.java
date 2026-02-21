package test.com.cap;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import main.com.cap.Students;

public class StudentsTest {
	Students students;
	
	@BeforeEach
	void setUp() {
		students = new Students();
	}
	
	@Test
    void testCalculateGrade_Distinction() {
        assertEquals("Distinction", students.calculateGrade(80));
    }

    @Test
    void testCalculateGrade_FirstClass() {
        assertEquals("First Class", students.calculateGrade(65));
    }

    @Test
    void testCalculateGrade_SecondClass() {
        assertEquals("Second Class", students.calculateGrade(55));
    }

    @Test
    void testCalculateGrade_Fail() {
        assertEquals("Fail", students.calculateGrade(40));
    }
	
    @Test
    void testIsPassed_True() {
        assertTrue(students.isPassed(75));
    }

    @Test
    void testIsPassed_False() {
        assertFalse(students.isPassed(45));
    }
    

    @Test
    void testCalculateGrade_InvalidMarks_Negative() {
        assertThrows(IllegalArgumentException.class, () -> {
        	students.calculateGrade(-10);
        });
    }

    @Test
    void testCalculateGrade_InvalidMarks_AboveHundred() {
        assertThrows(IllegalArgumentException.class, () -> {
        	students.calculateGrade(120);
        });
    }
    
    @Test
    void testCalculateGrade_NotNull() {
        assertNotNull(students.calculateGrade(70));
    }
	
}
