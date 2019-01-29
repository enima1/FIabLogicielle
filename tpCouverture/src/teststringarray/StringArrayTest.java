package teststringarray;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stringarray.StringArray;

/*1)le test StringArray a deux asserts alors
 *  qu'il n'en faut qu'un.
 */
/*2)La fontion getString n'est pas testée.
 * La variable dupl du constructeur n'est jamais
 * modifié donc on ne test pas l'un des
 * principaux cas. 
 *3)Test getString cas normal.
 *4)Rajout du test de duplication, le taux de 
 *couverture du constructeur est désormais à 90,1%.
 *5)Aucun changement, la couverture n'est pas totale.
 *Le test StringArray4 ne démontre rien.
 *6) TestStringEquality
 *7) la méthode getString ne gère pas les cas d'erreurs
 *Dans le constructeur la variable fill à -1 cause une erreur,
 *pour la corriger il faut mettre le fill à 1.
 *On peut en conclure que la couverture ne dispense pas forcément
 *d'avoir les spécification. En effet, on suppose que cette
 *classe doit éliminer les doublons dans un tableau de chaine de
 *caractères. Cependant, cela n'est fait que si ceux si sont consécutifs.
 * 
 */
public class StringArrayTest{

	private static String[] slist1={
			"a","b","ccc","ccd","d","e","f","g"
	};
	private static String[] slist2={
		"ab","ccd","ccc","g","f","e","d"
	};
	private static String[] slist3={
		"ab","ab"
	};
	private static String[] slist4={
		"ab","c","ab"
	};
	
	private static String[] slist5={
		"ab","ab","c","ab"
	};
			
	private StringArray array1;
	private StringArray array2;

	@Before
	public void setUp() throws Exception {
		System.out.println("debut du test");
	}

	@After
	public void tearDown() {
		System.out.println("fini");
	}

	@Test
	public void test1StringArray() {
		array1 = new StringArray(slist1);
		assertEquals(8, array1.size());
	}

	@Test
	public void test2StringArray() {
		array2 = new StringArray(slist2);
		assertEquals(7, array2.size());
	}

	@Test
	public void test3StringArray() {
		array1 = new StringArray(slist3);
		assertEquals(1, array1.size());
	}

	@Test
	public void test4StringArray() {
		array1 = new StringArray(slist4);
		assertEquals(3, array1.size());
	}

	@Test
	public void test5StringArray() {
		array1 = new StringArray(slist5);
		assertEquals(3, array1.size());
	}

	@Test
	public void testStringEquality() {
		array1 = new StringArray(slist1);
		int i = 3;
		String strList = slist1[i];
		String strArray = array1.getString(i);
		assertEquals(0, strList.compareTo(strArray));
	}

	@Test
	public void testGetString() {
		array2 = new StringArray(slist2);
		assertEquals(slist2[2], array2.getString(2));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test2GetString() {
		array2 = new StringArray(slist2);
		assertEquals(slist2[2], array2.getString(50));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void test3GetString() {
		array2 = new StringArray(slist2);
		assertEquals(slist2[2], array2.getString(-1));
	}

	@Test
	public void test1IndexOf() {
		array2 = new StringArray(slist2);
		assertEquals(-1, array2.IndexOf("ee"));
	}

	@Test
	public void test2IndexOf() {
		array2 = new StringArray(slist2);
		assertEquals(3, array2.IndexOf("d"));
	}
		
}