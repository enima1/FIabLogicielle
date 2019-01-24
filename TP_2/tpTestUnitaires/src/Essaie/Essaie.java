package Essaie;

public class Essaie {
	
	public double val;
	public Essaie(double val) {
		this.val = val;
	}
	
	public double getVal() {
		return this.val;
	}
	
	public void setVal(double val) {
		this.val = val;
	}
	
	public double ajouterVal(double val) {
		this.val += val;
		return val;
	}
	
	public double inverserVal() throws IllegalArgumentException {
		if(val == 0) throw new IllegalArgumentException();
		return 1/this.val;
	}
	

}
