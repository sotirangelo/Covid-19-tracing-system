/**
 * Exertion/Activity.
 * This enumeration represents the types of exertion that people make.
 * The level of exertion and consequently the breathing flow rate of each person,
 * varies, depending on their activity. Thus, each level of exertion, is defined
 * by a metre ranging from 0 to 0.5 (higher means safer against COVID-19)
 *
 * @version 0.1 21 Nov 2020
 * @author Sotiris
 */
public enum Exertion {
	/** Resting and speaking*/
	R_S(3.46,"Resting, Speaking"),
	/** Resting and loudly speaking*/
	R_L_S(22.2,"Resting, Loudly Speaking");
	S_S(4.2, "Standing,Speaking");
	S_L_S(23.9, "Standing, Loudly Speaking);
	L_E_S(9.9, "Light Exercise, Speaking");
	L_E_L_S(62.7, "Light Exercise, Loudly Speaking");
	H_E_S(23.2, "Heavy Exercise, Speaking");
	H_E_L_S(149, "Heavy Exercise, Loudly Speaking);
		
	
	private final double exertionFactor ;
	private final String type;

	private Exertion(double exertionFactor, String type) {
		this.exertionFactor = exertionFactor;
		this.type = type;
	}

	/**
	 * Returns the exertion factor in transmission.
	 * @return int  COVID-19 transmission metre based on activity/exertion. 
	 */
	public double getExertionFactor() {
		return exertionFactor;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

