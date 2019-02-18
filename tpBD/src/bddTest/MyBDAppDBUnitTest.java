/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @File MyBDAppDBUnitTest.java
 */
package bddTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bdd.MyBDApp;

public class MyBDAppDBUnitTest extends DBTestCase{

	@BeforeClass
	public static void initialiser() {
		System.out.println("hello");
	}
	
	@AfterClass
	public static void finaliser() {
		System.out.println("ciao");
	}
	
	/**
	 * Test le cas normal d'une recherche d'un enregistrement existant.
	 */
	@Test
	public void testFindName() {
		String expected = "Yannick";
		String name = null;
		
		assertEquals(expected, name);
	}
	
	/**
	 * Test le cas d'échec d'une recherche d'un enregistrement inexistant.
	 */
	@Test
	public void testFindNameFail() {
		String expected = null;
		String name = null;
		
		assertEquals(expected, name);
	}

	/**
	 * Test le cas normal d'un ajout d'un enregistrement inexistant.
	 */
	@Test
	public void testAddName() {
		String expected = null;
		int id = 200;
		String name = "Bob";
		
		assertEquals(expected, name);
	}

	/**
	 * Test le cas d'échec d'un ajout d'un enregistrement déjà existant.
	 */
	@Test
	public void testAddNameFail() {
		String expected = null;
		int id = 1;
		String name = "Bob";
		
		assertEquals(expected, name);
	}

	/**
	 * Test le cas normal d'une suppression d'un enregistrement existant.
	 */
	@Test
	public void testDeleteName() {
		String expected = null;
		int id = 42;
		String name = "Bob";
		
		assertEquals(expected, name);
	}
	
	/**
	 * Test le cas d'échec d'une suppression d'un enregistrement inexistant.
	 */
	@Test
	public void testDeleteNameFail() {
		String expected = null;
		int id = 999;
		String name = null;
		
		assertEquals(expected, name);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
