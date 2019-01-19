package Essaie;

import org.junit.Test;

import junit.framework.TestCase;

public class EssaieTest extends TestCase {
	
	
	
	
	public void testAjouter() throws Exception{
		Essaie essai1 = new Essaie(0.0);
		assertEquals(1.0,essai1.ajouterVal(1));
	}
	
	public void testGet() throws Exception{
		Essaie essai1 = new Essaie(0.0);
		assertEquals(0.0,essai1.getVal());
	}
	
	public void testSet() throws Exception{
		Essaie essai1 = new Essaie(0.0);
		essai1.setVal(3.3);
		assertEquals(3.3,essai1.getVal());
	}
	
	public void testConstructeur() throws Exception{
		Essaie essai1 = new Essaie(6.6);
		assertEquals(6.6,essai1.getVal());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentException() throws Exception{
		Essaie essai1 = new Essaie(0.0);
		essai1.inverserVal();
	}
	
	

	
}
