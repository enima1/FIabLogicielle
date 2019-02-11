package testtemperature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Verifications;
import temperature.ATesterWithInterface;
import temperature.IConversion;

public class ATesterWithInterfaceTest {

	public static ATesterWithInterface t;
	@Injectable IConversion c;
	
	
	/*Teste la conversion -40.0 = -40.0 dans le sens F2C*/
	@Test
	public void testFarenheitMinus40() {		
		double expected = -40.0;
		double temp = -40.0;
		new Expectations() {{
			t = new ATesterWithInterface(c);
			c.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		new Verifications() {{c.convF2C(temp); times = 1;}};
	}
	
	/*Teste la conversion 100.0 = 212.0 dans le sens F2C*/
	@Test
	public void testFarenheit212() {		
		double expected = 100.0;
		double temp = 212.0;
		new Expectations() {{
			t = new ATesterWithInterface(c);
			c.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		
		new Verifications() {{c.convF2C(temp); times = 1;}};
	}

	/*Teste la conversion 0.0 = 32.0 dans le sens C2F*/
	@Test
	public void testCelsius0() {		
		double expected = 32.0;
		double temp = 0.0;
		new Expectations() {{
			t = new ATesterWithInterface(c);
			c.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		
		new Verifications() {{c.convF2C(temp); times = 1;}};
	
	}
	
	/*Teste la conversion 37.0 = 98.6 dans le sens C2F*/
	@Test
	public void testCelsius37() {
		double expected = 98.6;
		double temp = 37.0;
		new Expectations() {{
			t = new ATesterWithInterface(c);
			c.convC2F(temp); result = expected;
		}};		
		assertEquals(expected, t.convertit(temp, "C2F"),0);
		
		new Verifications() {{c.convC2F(temp); times = 1;}};
	}
	
	/*Teste le cas d'erreur de sens de conversion*/
	@Test
	public void testErreurSens() {		
		double expected = 0;
		double temp = 5226;
		new Expectations() {{
			t = new ATesterWithInterface(c);
		}};
		assertEquals(expected, t.convertit(temp, "???"),0);
		
		new Verifications() {{c.convC2F(temp); times = 0;}};
	}	

}
