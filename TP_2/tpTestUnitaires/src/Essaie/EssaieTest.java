package Essaie;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EssaieTest {
	
	@BeforeClass
	public static void init() {
		System.out.println("Before class");
	}
	
	@AfterClass
	public static void fin() {
		System.out.println("After class");
	}
	
	@Before
	public void initTest() {
		System.out.println("Before");
	}
	
	@After
	public void finTest() {
		System.out.println("After");
	}
	
	
	@Test
	public void testAjouter() {
		Essaie essai1 = new Essaie(0.0);
		assertEquals(1.0,essai1.ajouterVal(1),0);
	}
	
	@Test
	public void testGet() {
		Essaie essai1 = new Essaie(0.0);
		assertEquals(0.0,essai1.getVal(),0);
	}
	
	@Test
	public void testSet() {
		Essaie essai1 = new Essaie(0.0);
		essai1.setVal(3.3);
		assertEquals(3.3,essai1.getVal(),0);
	}
	
	@Test
	public void testConstructeur() {
		Essaie essai1 = new Essaie(6.6);
		assertEquals(6.6,essai1.getVal(),0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentException() throws Exception{
		Essaie essai1 = new Essaie(0.0);
		essai1.inverserVal();
	}
	
	

	
}
