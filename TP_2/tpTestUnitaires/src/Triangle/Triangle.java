/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @file Triangle.java
 */

package Triangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Triangle {
	private double side_1;
	private double side_2;
	private double side_3;

	private double[] triangle;

	public Triangle(double side_1, double side_2, double side_3) {
		this.side_1 = side_1;
		this.side_2 = side_2;
		this.side_3 = side_3;
		triangle = new double[] { side_1, side_2, side_3 };
	}

	public Triangle(double[] triangle) {
		setTriangle(triangle);
	}
	
	public Triangle() {	
		double[] triangle = new double[] {0,0,0};
		setTriangle(triangle);
	}

	public boolean isEquilateral(double a, double b, double c) {
		return (a == b && b == c && a == c);
	}

	public boolean isIsocele(double a, double b, double c) {
		return (a == b || b == c || a == c);
	}

	public int findType(double a, double b, double c) {
		if (isEquilateral(a, b, c))
			return 3;
		else if (isIsocele(a, b, c))
			return 2;
		return 1;
	}

	public double getSide_1() {
		return side_1;
	}

	public double getSide_2() {
		return side_2;
	}

	public double getSide_3() {
		return side_3;
	}

	public double getSide(int indice) {

		switch (indice) {
		case 0:
			return side_1;

		case 1:
			return side_2;

		case 2:
			return side_3;

		default:
			return -1;
		}
	}

	public void setSide(double side, int indice) {

		switch (indice) {

		case 0:
			side_1 = side;
			triangle[0] = side;
			break;

		case 1:
			side_2 = side;
			triangle[1] = side;
			break;

		case 2:
			side_3 = side;
			triangle[2] = side;
			break;

		}
	}
	
	public int typeTriangle() {
		if (side_1 <= 0 || side_2 <= 0 || side_3 <= 0)
			return -1;

		if ((side_1 >= side_2) && (side_1 >= side_3)) {
			if (side_1 <= side_2 + side_3)
				return findType(side_1, side_2, side_3);
			return -1;
		}
		if ((side_2 >= side_1) && (side_2 >= side_3)) {
			if (side_2 <= side_1 + side_3)
				return findType(side_1, side_2, side_3);
			return -1;
		}

		if ((side_3 >= side_1) && (side_3 >= side_2)) {
			if (side_3 <= side_2 + side_1)
				return findType(side_1, side_2, side_3);
			return -1;
		}
		return -1;
	}

	public void error_tab() {
		setSide(-1.0, 0);
		setSide(-1.0, 1);
		setSide(-1.0, 2);
		triangle = new double[] {-1.0,-1.0,-1.0};

	}

	public void setTriangle(double[] triangle) {
		if(triangle.length != 3) return;
		this.triangle = triangle;
		setSide(triangle[0], 0);
		setSide(triangle[1], 1);
		setSide(triangle[2], 2);
	}

	public double[] getTriangle() {
		return triangle;
	}

	public double [] readData(String filename) throws IOException
	{
		if(filename == null) {
			error_tab();
			return triangle;
		}
		String[] extensions = filename.split("\\.");
		if (extensions.length != 2 || !extensions[1].equals("csv")) {
			error_tab();
			return triangle;
		}

		BufferedReader reader;
		String line;
		
		try {
			Reader r = new FileReader(filename);
			reader = new BufferedReader(r);
			line = reader.readLine();
			reader.close();
		} catch(IOException e) {
			error_tab();
			throw new FileNotFoundException();
			//return;
		}
		String[] split_line = line.split(",");

		if (split_line.length != 3) {
			error_tab();
			return triangle;
		}

		for (int i = 0; i < 3; i++) {
			try {
				double number_read = Double.parseDouble(split_line[i]);
				if (number_read <= 0) {
					error_tab();
					return triangle;
				}
				setSide(number_read, i);
			} catch (NumberFormatException e) {
				error_tab();
				return triangle;
			}
		}
		return triangle;
	}
	
	public static void main(String args[]) {
		Triangle t = new Triangle();
		try {
			t.readData("existe_pas.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
