package testsmagasin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import magasin.Article;

public class ArticleTest {

	private static Article art1 =
			new Article("Chips", 3, 1);
	private static Article art2 =
			new Article("Tomate", 2, 2);
	
	@Test(expected=NullPointerException.class)
	public void test1Article() {
		@SuppressWarnings("unused")
		Article artNul = new Article(null, 0, 0);
	}
	
	@Test(expected=AssertionError.class)
	public void test2Article() {
		@SuppressWarnings("unused")
		Article artNul = new Article("", -2, -2);
	}
	
	@Test(expected=AssertionError.class)
	public void test3Article() {
		@SuppressWarnings("unused")
		Article artNul = new Article("Faux", -2, -2);
	}
	
	@Test
	public void test1IsEqual() {
		assertEquals(false,art1.isEqual(art2));
	}
	
	@Test
	public void test2IsEqual() {
		assertEquals(true,art1.isEqual(art1));
	}
	
	@Test
	public void test3IsEqual() {
		Article chips = new Article("Chips", 5, 1);
		assertEquals(false,chips.isEqual(art1));
	}
	
	@Test
	public void test4IsEqual() {
		Article chips = new Article("Chips", 3, 3);
		assertEquals(false,chips.isEqual(art1));
	}
	
	@Test
	public void test1SmallerThan() {
		assertEquals(true,art2.smallerThan(art1));
	}
	
	@Test
	public void test2SmallerThan() {
		assertEquals(false,art1.smallerThan(art2));
	}

	@Test
	public void test3SmallerThan() {
		assertEquals(false,art2.smallerThan(art2));
	}
}
