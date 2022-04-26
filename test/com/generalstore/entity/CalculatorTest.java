package com.generalstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		int a = 89;
		int b =3458;
		int result = calculator.add(a,b);
		int expected = 3547;
		assertEquals(expected , result);
		
	}

}
