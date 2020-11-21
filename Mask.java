/**
 * Mask types used.
 * This enumeration represents the types of mask that can be used.
 * Each type of mask is defined by an efficiency percentage.
 */
public enum Mask {
	/** No mask used*/
	NONE (0,"None"),

	/** Fabric mask*/
	FABRIC (0.5, "Fabric"),
	
	/** Medical mask*/
	MEDICAL (0.75,"Medical"),
	
	/** Respirator*/
	RESPIRATOR (0.95, "Respirator");

	private final double efficiency;
	private final String type;

	private Mask(double efficiency, String type) {
		this.efficiency = efficiency;
		this.type = type;
	}

	/**
	 * Returns the efficiency of the specified mask type.
	 * @param  double  percentage representing efficiency
	 */
	public double getEfficiency() {
		return efficiency;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

