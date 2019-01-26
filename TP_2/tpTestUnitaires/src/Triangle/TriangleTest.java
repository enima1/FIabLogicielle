/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @file TriangleTest.java
 */

package Triangle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class TriangleTest {

	// TEST TRIANGLE
	@Test
	public void testIsEquilateral() {
		Triangle triangle = new Triangle(3.0, 3.0, 3.0);
		boolean resultat = triangle.isEquilateral(triangle.getSide_1(),
				triangle.getSide_2(), triangle.getSide_3());
		assertEquals(true, resultat);
	}

	@Test
	public void testIsNotEquilateral() {
		Triangle triangle = new Triangle(2.0, 3.0, 3.0);
		boolean resultat = triangle.isEquilateral(triangle.getSide_1(),
				triangle.getSide_2(), triangle.getSide_3());
		assertEquals(false, resultat);
	}

	@Test
	public void testIsIsocele() {
		Triangle triangle = new Triangle(3.0, 3.0, 1.0);
		boolean resultat = triangle.isIsocele(triangle.getSide_1(),
				triangle.getSide_2(), triangle.getSide_3());
		assertEquals(true, resultat);
	}

	@Test
	public void testIsNotIsocele() {
		Triangle triangle = new Triangle(2.0, 3.0, 1.0);
		boolean resultat = triangle.isIsocele(triangle.getSide_1(),
				triangle.getSide_2(), triangle.getSide_3());
		assertEquals(false, resultat);
	}

	@Test
	public void testTriangleEquilateral() {
		Triangle triangle = new Triangle(2.0, 2.0, 2.0);
		assertEquals(3, triangle.typeTriangle());
	}

	@Test
	public void testTriangleIsocele() {
		Triangle triangle = new Triangle(3.0, 3.0, 4.0);
		assertEquals(2, triangle.typeTriangle());
	}

	@Test
	public void testTriangleScalene() {
		Triangle triangle = new Triangle(2.0, 3.0, 4.0);
		assertEquals(1, triangle.typeTriangle());
	}

	@Test
	public void testTypeTriangle_nul_val() {
		Triangle triangle = new Triangle(0.0, 3.0, 4.0);
		assertEquals(-1, triangle.typeTriangle());

	}

	@Test
	public void testTypeTriangle_neg_val() {
		Triangle triangle = new Triangle(-1, 3.0, 4.0);
		assertEquals(-1, triangle.typeTriangle());

	}

	@Test
	public void testTypeTriangle_not_a_triangle() {
		Triangle triangle = new Triangle(1.1, 1.1, 4.0);
		assertEquals(-1, triangle.typeTriangle());

	}

	/* TESTS GETTERS ET SETTERS CONSTRUCTEURS*/
	@Test
	public void testConstructeur1() {
		double[] expectedResult = new double[] {1.1, 2.2, 3.3};
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		assertEquals(1.1, triangle.getSide(0), 0);
		assertEquals(2.2, triangle.getSide(1), 0);
		assertEquals(3.3, triangle.getSide(2), 0);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testConstructeur2() {
		double[] expectedResult = new double[] {1.1, 2.2, 3.3};
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		assertEquals(1.1, triangle.getSide(0), 0);
		assertEquals(2.2, triangle.getSide(1), 0);
		assertEquals(3.3, triangle.getSide(2), 0);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testConstructeur3() {
		double[] expectedResult = new double[] {0,0,0};
		Triangle triangle = new Triangle();
		assertEquals(0.0, triangle.getSide(0), 0);
		assertEquals(0.0, triangle.getSide(1), 0);
		assertEquals(0.0, triangle.getSide(2), 0);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testGetSides() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double result = triangle.getSide(0);
		assertEquals(1.1, result, 0);
	}

	@Test
	public void testSetSide() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.setSide(3.0, 0);
		double[] expectedResult = new double[] { 3.0, 2.2, 3.3 };
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testSetTriangle() {
		Triangle triangle = new Triangle();
		double[] expectedResult = new double[] { 3.0, 2.2, 3.3 };
		triangle.setTriangle(expectedResult);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testSetTriangleTooFew() {
		double[] expectedResult = new double[] { 3.0, 2.2, 3.3 };
		Triangle triangle = new Triangle(expectedResult);
		double[] tooFew = new double[] {1.0,2.0};
		triangle.setTriangle(tooFew);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	@Test
	public void testSetTriangleTooMuch() {
		double[] expectedResult = new double[] { 3.0, 2.2, 3.3 };
		Triangle triangle = new Triangle(expectedResult);
		double[] tooMuch = new double[] {1.0, 2.0, 1.0 ,2.0};
		triangle.setTriangle(tooMuch);
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}
	@Test
	public void testErrorTab() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.error_tab();
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.getTriangle(), 0);
	}

	// TEST LECTURE DE FICHIER 
	
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFoundException() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.readData("existe_pas.csv");
		
	}

	@Test
	public void testFileNotFound_tab_value() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		try {
			assertArrayEquals(expectedResult, triangle.readData("existe_pas.csv"), 0);
		} catch (IOException e) {}
	}

	@Test
	public void testFileNull() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData(null), 0);
	}

	@Test
	public void testFileNoExtension() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("executable"), 0);
	}

	@Test
	public void testFileWrongExtension() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("virus.txt"), 0);
	}

	@Test
	public void testFileSeveralExtensions() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("test.py.csv"), 0);
	}

	
	@Test
	public void test_TooMuchFields() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("file_toomuch_fields.csv"), 0);
	}

	@Test
	public void test_TooFewFields() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("file_toofew_fields.csv"), 0);
	}

	@Test
	public void test_NegVal() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("file_negval.csv"), 0);
	}

	@Test
	public void test_NonDigit() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] expectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(expectedResult, triangle.readData("file_non_digitval.csv"), 0);
	}

}
