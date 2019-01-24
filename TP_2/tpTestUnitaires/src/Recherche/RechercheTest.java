package Recherche;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RechercheTest {

	@Test
	public void test_array_null() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = null;
		int resultat = recherche.chercherElt(0, tableau);
		assertEquals(-1, resultat);

	}

	@Test
	public void test_array_empty() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = new int[0];
		int resultat = recherche.chercherElt(1, tableau);
		assertEquals(-1, resultat);

	}

	@Test
	public void test_element_not_found() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		int resultat = recherche.chercherElt(-1, tableau);
		assertEquals(-1, resultat);

	}

	@Test
	public void test_element_found() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		int resultat = recherche.chercherElt(1, tableau);
		assertEquals(0, resultat);
	}

	@Test(timeout = 2000)
	public void test_chercher1_element_present() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		boolean resultat = recherche.chercher1(1, tableau);
		assertEquals(true, resultat);

	}

	@Test(timeout = 2000)
	public void test_chercher2_element_present() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		boolean resultat = recherche.chercher2(1, tableau);
		assertEquals(true, resultat);

	}

	@Test(timeout = 2000)
	public void test_chercher3_element_present() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		boolean resultat = recherche.chercher3(4, tableau);
		assertEquals(true, resultat);

	}

	@Test(timeout = 2000)
	public void test_chercher4_element_present() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		boolean resultat = recherche.chercher4(4, tableau);
		assertEquals(false, resultat);

	}

	@Test(timeout=2000)
	public void test_chercher5_element_present() throws Exception {
		Recherche recherche = new Recherche();
		int[] tableau = { 1, 2, 3, 4 };
		boolean resultat = recherche.chercher4(4, tableau);
		assertEquals(false, resultat);

	}

}
