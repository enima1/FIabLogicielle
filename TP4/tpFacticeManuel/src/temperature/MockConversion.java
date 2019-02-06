package temperature;

public class MockConversion implements IConversion {

	@Override
	public double convF2C(double temperature) {
		if(temperature == -40.0) return -40.0;
		if(temperature == 212.0) return 100.0;
		return 0.0;
	}

	@Override
	public double convC2F(double temperature) {
		if(temperature == 0.0) return 32.0;
		if(temperature == 37.0) return 98.6;
		return 0.0;
	}

}
