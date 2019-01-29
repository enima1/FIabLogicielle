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
		Article artNul = new Article(null, 0, 0);
	}
	
	@Test(expected=AssertionError.class)
	public void test2Article() {
		Article artNul = new Article("", -2, -2);
	}
	
	@Test
	public void test1IsEqual() {
		assertEquals(false,art1.isEqual(art2));
	}
	
	@Test
	public void test2IsEqual() {
		assertEquals(true,art1.isEqual(art1));
	}

}
