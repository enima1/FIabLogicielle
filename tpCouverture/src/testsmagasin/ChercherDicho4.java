package testsmagasin;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import magasin.Article;
import magasin.Magasin;

public class ChercherDicho4 {
	private static Magasin magasinTriee;
	private static Magasin magasinNonTriee;
	private static Article art1 = new Article("Jambon", 2, 1);
	private static Article art2 = new Article("Fromage", 5, 2);
	private static Article art3 = new Article("Pomme", 7, 3);
	private static Article art4 = new Article("Chocolat", 8, 4);
	private static Article art5 = new Article("Eau", 10, 5);
	private static Article[] articlesTriee = { art1, art2, art3, art4, art5 };
	private static Article[] articlesNonTriee = { art5, art3, art1, art4, art2 };

	@BeforeClass
	static public void initialiser() {
		magasinTriee = new Magasin(5);
		magasinTriee.setStock(articlesTriee);
		magasinNonTriee = new Magasin(5);
		magasinNonTriee.setStock(articlesNonTriee);
	}

	@AfterClass
	static public void finaliser() {
		magasinTriee = magasinNonTriee = null;
		articlesTriee = articlesNonTriee = null;
		art1 = art2 = art3 = art4 = art5 = null;
	}

	@Test(timeout=2000)
	public void test1ArticleExistant() {
		assertEquals(true, magasinTriee.chercherDicho4(art1));
	}
	

	@Test(timeout=2000)
	public void test2ArticleExistant() {
		assertEquals(true, magasinTriee.chercherDicho4(art4));
	}

	@Test(timeout=2000)
	public void test1ArticleInexistant() {
		Article artInexistant = new Article("inexistant", 42, 42);
		assertEquals(false, magasinTriee.chercherDicho4(artInexistant));
	}

	@Test
	public void testNull() {
		Article artNull = null;
		assertEquals(false, magasinTriee.chercherDicho4(artNull));
	}

	
	@Test(timeout=2000)
	public void test1ArticleExistantNonTriee() {
		assertEquals(false, magasinTriee.chercherDicho4(art1));
	}
	
	@Test(timeout=2000)
	public void test2ArticleExistantNonTriee() {
		assertEquals(false, magasinTriee.chercherDicho4(art4));
	}
}
