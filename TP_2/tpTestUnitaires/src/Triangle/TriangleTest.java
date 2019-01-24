package Triangle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class TriangleTest {

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

	/* TESTS LECTURE DE FICHIER */
	@Test
	public void testGetSides() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double result = triangle.getSide(0);
		assertEquals(1.1, result, 0);
	}

	@Test
	public void testSetSides() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.setSides(3.0, 0);
		double[] ExpectedResult = new double[] { 3.0, 2.2, 3.3 };
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void testErrorTab() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.error_tab();
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test(expected=FileNotFoundException.class)
	public void testFileNotFoundException() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		triangle.readData("existe_pas.csv");
		
	}

	@Test
	public void testFileNotFound_tab_value() {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		try {
			triangle.readData("existe_pas.csv");
		} catch (IOException e) {
			assertArrayEquals(ExpectedResult, triangle.triangle, 0);
		}
	}

	@Test
	public void testFileNull() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData(null);
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void testFileNoExtension() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("executable");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void testFileWrongExtension() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("virus.txt");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void testFileSeveralExtensions() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("test.py.csv");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	
	@Test
	public void test_TooMuchFields() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("file_toomuch_fields.csv");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void test_TooFewFields() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("file_toofew_fields.csv");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void test_NegVal() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("file_negval.csv");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

	@Test
	public void test_NonDigit() throws Exception {
		Triangle triangle = new Triangle(1.1, 2.2, 3.3);
		double[] ExpectedResult = new double[] { -1.0, -1.0, -1.0 };
		triangle.readData("file_non_digitval.csv");
		assertArrayEquals(ExpectedResult, triangle.triangle, 0);
	}

}
