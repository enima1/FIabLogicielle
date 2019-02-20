/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @File MyBDAppTest.java
 */
package bddTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import bdd.MyBDApp;
/*On remarque que l'on modifie la base de données
 *  pour les tests d'ajout et de suppression
 *   (ce qui n'est pas pratique sur l'application).*/
public class MyBDAppTest {
	private static MyBDApp app;
	
	/**
	 * Initialise la BD.
	 */
	@BeforeClass
	public static void initialiser() {
		app = new MyBDApp();
	}
	
	/**
	 * Test le cas normal d'une recherche d'un enregistrement existant.
	 */
	@Test
	public void testFindName() {
		String expected = "Bob";
		String requestOutput = null;
		try {
			requestOutput = app.findName(200);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/* 
			 * La requ�te produit une string contenant notre chaine attendue
			 * mais parsem�e de null, on la nettoie donc	
			 * 				
			 *  */
		String requete_nettoyee = "";
		for(int i = 0 ; i < requestOutput.length() ; i++)
			if( (int)requestOutput.charAt(i) != 0)
				requete_nettoyee += requestOutput.charAt(i);
		
		
		assertEquals(expected, requete_nettoyee);
	}
	
	/**
	 * Test le cas d'�chec d'une recherche d'un enregistrement inexistant.
	 */
	@Test
	public void testFindNameFail() {
		String expected = null;
		String name = null;
		try {
			name = app.findName(1888);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, name);
	}

	/**
	 * Test le cas normal d'un ajout d'un enregistrement inexistant.
	 */
	@Test
	public void testAddName() {
		String expected = "Bob";
		int id = 200;
		String name = "Bob";
		try {
			app.addName(id, name);
			name = app.findName(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, name);
	}

	/**
	 * Test le cas d'�chec d'un ajout d'un enregistrement d�j� existant.
	 */
	@Test
	public void testAddNameFail() {
		String expected = null;
		int id = 1;
		String name = "Bob";
		try {
			app.addName(id, name);
			name = app.findName(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(expected, name);
	}

	/**
	 * Test le cas normal d'une suppression d'un enregistrement existant.
	 */
	@Test
	public void testDeleteName() {
		String expected = null;
		int id = 42;
		String name = null;
		try {
			app.addName(id, name);
			name = app.findName(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, name);
	}
	
	/**
	 * Test le cas d'�chec d'une suppression d'un enregistrement inexistant.
	 */
	@Test
	public void testDeleteNameFail() {
		String expected = null;
		int id = 999;
		String name = null;
		try {
			app.deleteName(id);
			name = app.findName(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, name);
	}
}
