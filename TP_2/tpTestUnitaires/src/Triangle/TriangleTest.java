package Triangle;

import java.io.FileNotFoundException;

import org.junit.Test;
import Essaie.Essaie;
import junit.framework.TestCase;
import org.junit.Assert;

	
public class TriangleTest extends TestCase {

	
					/* TESTS OPERATION SUR LES TRIANGLES */
	
	
	public void testisEquilateral() throws Exception{
		Triangle triangle = new Triangle(3.0,3.0,3.0);
		boolean resultat =  triangle.isEquilateral(triangle.getSide_1(),triangle.getSide_2(),triangle.getSide_3());
		assertEquals(true,resultat);
	}
	
	public void testisIsocele() throws Exception{
		Triangle triangle = new Triangle(3.0,3.0,1.0);
		boolean resultat =  triangle.isIsocele(triangle.getSide_1(),triangle.getSide_2(),triangle.getSide_3());
		assertEquals(true,resultat);
	}
	
	public void testisScalene() throws Exception{
		Triangle triangle = new Triangle(2.0,3.0,4.0);
		assertEquals(1,triangle.typeTriangle());
	}
	
	public void testTypeTriangle_nul_val() throws Exception{
		Triangle triangle = new Triangle(0.0,3.0,4.0);
		assertEquals(-1,triangle.typeTriangle());
		
	}
	
	public void testTypeTriangle_neg_val() throws Exception{
		Triangle triangle = new Triangle(-1,3.0,4.0);
		assertEquals(-1,triangle.typeTriangle());
		
	}
	
	public void testTypeTriangle_not_a_triangle() throws Exception{
		Triangle triangle = new Triangle(1.1,1.1,4.0);
		assertEquals(-1,triangle.typeTriangle());
		
	}
	
					/* TESTS LECTURE DE FICHIER */
	public void testGetSides() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double result = triangle.getSide(0);
		assertEquals(1.1, result);
	}
	
	public void testSetSides() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		triangle.setSides(3.0, 0);
		double[] ExpectedResult = new double[] {3.0,2.2,3.3};
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void testErrorTab() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		triangle.error_tab();
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testFileNotFoundException() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		triangle.readData("existe_pas");
		}
	
	public void testFileNotFound_tab_value() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData("existe_pas");
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void testFileNull() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData(null);
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void test_TooMuchFields() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData("file_toomuch_fields.csv");
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void test_TooFewFields() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData("file_toofew_fields.csv");
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void test_NegVal() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData("file_negval.csv");
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}
	
	public void test_NonDigit() throws Exception{
		Triangle triangle = new Triangle(1.1,2.2,3.3);
		double[] ExpectedResult = new double[] {-1.0,-1.0,-1.0};
		triangle.readData("file_nondigitval.csv");
		Assert.assertArrayEquals(ExpectedResult, triangle.triangle,0);
	}	
	
}

