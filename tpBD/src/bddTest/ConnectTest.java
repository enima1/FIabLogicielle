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
	
	@BeforeClass
	public static void initialiser() {
		url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/g15008299";
		user = "g15008299";
		passwd = "/vxz627E";
	}
	
	@Test
	public void testConnectRight() {
		connexion = new Connect(url, user, passwd);
		assertEquals(true, connexion.isConnected());
		connexion.disconnect();
	}
	
	@Test
	public void testConnectWrong() {
		connexion = new Connect(url, user, "WrongPasswd");
		assertEquals(false, connexion.isConnected());
	}
	
	@Test
	public void testDisconnect() {
		connexion = new Connect(url, user, passwd);
		connexion.disconnect();
		assertEquals(false, connexion.isConnected());
		
	}

}
