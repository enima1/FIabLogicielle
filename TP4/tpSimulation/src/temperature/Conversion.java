package temperature;

public class Conversion {

	public double convF2C(double temperature) {
		return (temperature - 32.0) * 5.0 / 9.0;
	}

	public double convC2F(double temperature) {
		return temperature * 9.0 / 5.0 + 32.0;
	}

}
