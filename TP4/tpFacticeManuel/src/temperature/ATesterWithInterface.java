package temperature;

public class ATesterWithInterface {
	private IConversion conversion;

	public ATesterWithInterface(IConversion conversion) {
		super();
		this.conversion = conversion;
	}
	
	public double convertit(double temperature, String sens) {
		if(sens.equals("C2F")) return conversion.convC2F(temperature);
		if(sens.equals("F2C")) return conversion.convF2C(temperature);
		return 0.0;
		
	}
}
