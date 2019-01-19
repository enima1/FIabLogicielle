package Recherche;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Essaie.Essaie;
import junit.framework.TestCase;

public class RechercheTest extends TestCase {
	
	public void test_array_null() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau = null;
		int resultat = recherche.chercherElt(0, tableau);
		assertEquals(-1,resultat);
		
	}
	
	public void test_array_empty() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau = new int[0];
		int resultat = recherche.chercherElt(1, tableau);
		assertEquals(-1,resultat);
		
	}
	
	public void test_element_not_found() throws Exception{
			Recherche recherche = new Recherche();
			int[] tableau =  {1,2,3,4};
			int resultat = recherche.chercherElt(-1, tableau);
			assertEquals(-1,resultat);
			
	}
	
	public void test_element_found() throws Exception{
			Recherche recherche = new Recherche();
			int[] tableau =  {1,2,3,4};
			int resultat = recherche.chercherElt(1, tableau);
			assertEquals(0,resultat);
			
	}
	/*			Quand on cherche un élément supérieur à 2 qui est dans la premiere case ça foire
	 * 			sinon je crois que ça marche
	public void test_chercher1_element_present() throws Exception{
			Recherche recherche = new Recherche();
			int[] tableau =  {1,2,3,4};
			boolean resultat = recherche.chercher1(1, tableau);
			assertEquals(true,resultat);
		    
	}
			
						BOUCLE INFINIE 
	
	public void test_chercher2_element_present() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau =  {1,2,3,4};
		boolean resultat = recherche.chercher2(1, tableau);
		assertEquals(true,resultat);
	    
	}										
	
	public void test_chercher3_element_present() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau =  {1,2,3,4};
		boolean resultat = recherche.chercher3(4, tableau);
		assertEquals(true,resultat);
	    
	}
						
	public void test_chercher4_element_present() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau =  {1,2,3,4};
		boolean resultat = recherche.chercher4(4, tableau);
		assertEquals(true,resultat);
	    
	}		
	
	public void test_chercher5_element_present() throws Exception{
		Recherche recherche = new Recherche();
		int[] tableau =  {1,2,3,4};
		boolean resultat = recherche.chercher4(4, tableau);
		assertEquals(true,resultat);
	    
	}	*/
	
	

}
