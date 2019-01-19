package Triangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Triangle {
	private double side_1;
	private double side_2;
	private double side_3;
	
	double[] triangle ;
	
	public Triangle(double side_1,double side_2,double side_3) {
		this.side_1 = side_1;
		this.side_2 = side_2;
		this.side_3 = side_3;
		triangle = new double[] {side_1,side_2,side_3};
	}
	
	public boolean isEquilateral(double a,double b,double c) {
		if( a == b && b == c && a == c) 
	        return true;
		
	   return false;
	}
	
	public boolean isIsocele(double a,double b,double c) {
		if( a == b || b == c || a == c) 
	        return true;
		
	   return false;
	}
	
	public int findType(double a,double b,double c) {
		if(isEquilateral(a,b,c)) return 3;
		else if(isIsocele(a,b,c)) return 2;
		
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
		
		switch(indice) {
		case 0 : 
			return side_1;
		
		case 1: 
			return side_2;
		
		case 2 :
			return side_3;

		default : return -1;
		}
	}
	
	public void setSides(double side,int indice) {
		
		switch(indice) {
		
		case 0: side_1=  side;
		triangle[0] = side;
		break;
		
		case 1: side_2=  side;
		triangle[1] = side;
		break;
		
		case 2: side_3=  side;
		triangle[2] = side;
		break;
			
		}
	}
	
	public int typeTriangle() {
		if( side_1 <= 0 || side_2 <= 0 ||	side_3<=0)
	         return -1;
	
		if( (side_1 >= side_2) && (side_1 >= side_3) ) {
	        if( side_1 <= side_2 + side_3 ) 
	            return findType(side_1,side_2,side_3);
	        return -1;
		}
		if( (side_2 >= side_1) && (side_2 >= side_3) ) {
	        if( side_2 <= side_1 + side_3 ) 
	            return findType(side_1,side_2,side_3);
	        return -1;
		}
	
		if( (side_3 >= side_1) && (side_3 >= side_2) ) {
	        if( side_3 <= side_2 + side_1 ) 
	            return findType(side_1,side_2,side_3);
	        return -1;
		}
		return -1;
	}
	
	public void error_tab() {
				
		setSides(-1.0,0);
		setSides(-1.0,1);
		setSides(-1.0,2);
		
		}
  

	public void readData(String filename) throws FileNotFoundException{
		
			boolean error_detected = false;
		    
			try {
				BufferedReader reader;
				reader = new BufferedReader(new FileReader(
							filename));
				String line = reader.readLine();
				String[] split_line = line.split(",");
				
				if(split_line.length > 3 || split_line.length < 3) 
					error_detected = true;
				
				for(int i = 0 ; i < 3 ; i ++) {
					
						try {
							double number_read = Double.parseDouble(split_line[i]);
							if(number_read <= 0) 
								error_detected = true;
							setSides(number_read,i);
						}
						catch(NumberFormatException e ) {
							error_tab();
						}
				 }
					if(error_detected) error_tab();
				}
				catch( IOException e) {
				error_tab();
				}
				catch( NullPointerException e) {
				error_tab();
			}
		}
	}
	