package bddTest;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import bdd.MyBDApp;
/*On remarque que l'on modifie la base de données
 *  pour les tests d'ajout et de suppression
 *   (ce qui n'est pas pratique sur l'application).*/
public class MyBDAppTest {
	private static MyBDApp app;
	
	@BeforeClass
	public static void initialiser() {
		app = new MyBDApp();
	}
	
	@Test
	public void testFindName() {
		String expected = "Yannick";
		String name = null;
		try {
			name = app.findName(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, name);
	}
	
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

}
