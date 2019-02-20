/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @File ConnectTest.java
 */
package bddTest;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import bdd.Connect;

public class ConnectTest {
	private static Connect connexion;
	
	private static String url;
	private static String user;
	private static String passwd;
	
	/**
	 * Initialise les identifiants de connexion.
	 */
	@BeforeClass
	public static void initialiser() {
		url = "jdbc:mysql://localhost/dbtest";
		user = "root";
		passwd = "";
	}
	
	/**
	 * Teste le cas normal de connexion.
	 */
	@Test
	public void testConnectRight() {
		connexion = new Connect(url, user, passwd);
		assertEquals(true, connexion.isConnected());
		connexion.disconnect();
	}
	
	/**
	 * Teste le cas d'échec de connexion.
	 */
	@Test
	public void testConnectWrong() {
		connexion = new Connect(url, user, "WrongPasswd");
		assertEquals(false, connexion.isConnected());
	}
	
	/**
	 * Teste le cas normal de déconnexion.
	 */
	@Test
	public void testDisconnect() {
		connexion = new Connect(url, user, passwd);
		connexion.disconnect();
		assertEquals(false, connexion.isConnected());
		
	}

}
