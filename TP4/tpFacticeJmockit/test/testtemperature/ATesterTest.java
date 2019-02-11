package testtemperature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Verifications;
import temperature.ATester;
import temperature.Conversion;

public class ATesterTest {
	ATester t;
	@Mocked Conversion mockConversion;
	

	/*Teste la conversion -40.0 = -40.0 dans le sens F2C*/
	@Test
	public void testFarenheitMinus40() {		
		double expected = -40.0;
		double temp = -40.0;
		new Expectations() {{
			t = new ATester(mockConversion);
			mockConversion.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		new Verifications() {{mockConversion.convF2C(temp); times = 1;}};
	}

	/*Teste la conversion -40.0 = -40.0 dans le sens F2C
	 * Utilise l'annotation Injectable qui simule seulement la
	 * méthode utilisé et non l'instanciation de l'objet.*/
	@Injectable Conversion injectedConversion;
	@Test
	public void testFarenheitMinus40Injected() {		
		double expected = -40.0;
		double temp = -40.0;
		new Expectations() {{
			t = new ATester(injectedConversion);
			injectedConversion.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		new Verifications() {{injectedConversion.convF2C(temp); times = 1;}};
	}

	
	
	/*Teste la conversion 100.0 = 212.0 dans le sens F2C*/
	@Test
	public void testFarenheit212() {		
		double expected = 100.0;
		double temp = 212.0;
		new Expectations() {{
			t = new ATester(mockConversion);
			mockConversion.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		
		new Verifications() {{mockConversion.convF2C(temp); times = 1;}};
	}

	/*Teste la conversion 0.0 = 32.0 dans le sens C2F*/
	@Test
	public void testCelsius0() {		
		double expected = 32.0;
		double temp = 0.0;
		new Expectations() {{
			t = new ATester(mockConversion);
			mockConversion.convF2C(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "F2C"),0);
		
		new Verifications() {{mockConversion.convF2C(temp); times = 1;}};
	
	}
	
	/*Teste la conversion 37.0 = 98.6 dans le sens C2F*/
	@Test
	public void testCelsius37() {
		double expected = 98.6;
		double temp = 37.0;
		new Expectations() {{
			t = new ATester(mockConversion);
			mockConversion.convC2F(temp); result = expected;
		}};		
		assertEquals(expected, t.convertit(temp, "C2F"),0);
		
		new Verifications() {{mockConversion.convC2F(temp); times = 1;}};
	}
	
	/*Teste le cas d'erreur de sens de conversion*/
	@Test
	public void testErreurSens() {		
		double expected = 0;
		double temp = 5226;
		new Expectations() {{
			t = new ATester(mockConversion);
			//mockConversion.convC2F(temp); result = expected;
		}};
		assertEquals(expected, t.convertit(temp, "???"),0);
		
		new Verifications() {{mockConversion.convC2F(temp); times = 0;}};
	}	

}
