package testtemperature;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import temperature.ATester;
import temperature.Conversion;
import temperature.ExtMockConversion;

/*
 * 1) Sans la classe Conversion, nous avons des erreurs de compilation.
 * En rajoutant une classe Conversion fausse juste pour compiler, 
 * tout les tests échouent sauf celui du cas d'erreur.
 */

public class ATesterTestManualMock {
	
	public static ATester t;
	public static Conversion c;
	
	@BeforeClass
	public static void initialiser() {
		c = new ExtMockConversion();
		t = new ATester(c);
	}

	/*Teste la conversion -40.0 = -40.0 dans le sens F2C*/
	@Test
	public void testFarenheitMinus40() {
		double expected = -40.0;
		double temp = -40.0;
		assertEquals(expected, t.convertit(temp, "F2C"),0);
	}

	/*Teste la conversion 100.0 = 212.0 dans le sens F2C*/
	@Test
	public void testFarenheit212() {
		double expected = 100.0;
		double temp = 212.0;
		assertEquals(expected, t.convertit(temp, "F2C"),0);
	}

	/*Teste la conversion 0.0 = 32.0 dans le sens C2F*/
	@Test
	public void testCelsius0() {
		double expected = 32.0;
		double temp = 0.0;
		assertEquals(expected, t.convertit(temp, "C2F"),0);
	}
	
	/*Teste la conversion 37.0 = 98.6 dans le sens C2F*/
	@Test
	public void testCelsius37() {
		double expected = 98.6;
		double temp = 37.0;
		assertEquals(expected, t.convertit(temp, "C2F"),0);
	}
	
	/*Teste le cas d'erreur de sens de conversion*/
	@Test
	public void testErreurSens() {
		double expected = 0.0;
		double temp = 37.0;
		assertEquals(expected, t.convertit(temp, "???"),0);
	}

}
