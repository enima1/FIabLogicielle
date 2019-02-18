package bddTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DBTest {

	@Test
	public void testDB() {
		DBInjecteur.main(null);
		DBExtracteur.main(null);
		assertTrue(true);
	}

}
