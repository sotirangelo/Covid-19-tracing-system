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
	R_S(3.46, 0.49, "Resting, Speaking"),
	/** Resting and loudly speaking*/
	R_L_S(22.2, 0.49, "Resting, Loudly Speaking"),
	S_S(4.2, 0.54, "Standing,Speaking"),
	S_L_S(23.9, 0.54, "Standing, Loudly Speaking"),
	L_E_S(9.9, 1.38, "Light Exercise, Speaking"),
	L_E_L_S(62.7, 1.38, "Light Exercise, Loudly Speaking"),
	H_E_S(23.2, 3.3, "Heavy Exercise, Speaking"),
	H_E_L_S(149, 3.3, "Heavy Exercise, Loudly Speaking);
		
	/** Quanta Emission Rate */
	private final double erq ;
	/** Inhalation Rate */
	private final double ir;
	private final String type;

	private Exertion(double erq, double ir, String type) {
		this.erq = erq;
		this.ir = ir;
		this.type = type;
	}

	public double getErq() {
		return erq;
	}
	
	public double getIr() {
		return ir;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

