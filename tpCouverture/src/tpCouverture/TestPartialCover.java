package tpCouverture;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

//Ne pas rajouter de tests couverture maximale sans modifier le code
//Le code possède une partie qui ne sera jamais faite.
public class TestPartialCover {
	private PartialCover obj = new PartialCover();

	@Before
	public void initTest() {
		@SuppressWarnings("unused")
		PartialCover obj = new PartialCover();
	}

	@Test
	public void  testZeroZero() {
		int x = 0;
		int y = 0;
		int expected = 0;
		int value = obj.returnZeroOrOne(x, y);
		assertTrue(value == expected);
	}

	@Test
	public void  testOneZero() {
		int x = 1;
		int y = 0;
		int expected = 0;
		int value = obj.returnZeroOrOne(x, y);
		assertTrue(value == expected);
	}

	@Test
	public void  testZerOne() {
		int x = 0;
		int y = 1;
		int expected = 0;
		int value = obj.returnZeroOrOne(x, y);
		assertTrue(value == expected);
	}

	@Test
	public void  testOneOne() {
		int x = 1;
		int y = 1;
		int expected = 0;
		int value = obj.returnZeroOrOne(x, y);
		assertTrue(value == expected);
	}

}