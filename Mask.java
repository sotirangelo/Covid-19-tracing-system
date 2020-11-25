/**
 * Mask types used.
 * This enumeration represents the types of mask that can be used.
 * Each type of mask is defined by an efficiency metre, ranging from 0 to 3, depending on the mask type.
 * (higher means safer against COVID-19)
 *
 * @version 0.1 21 Nov 2020
 * @author Sotiris
 */
public enum Mask {
	/** No mask used*/
	NONE (0,"None"),

	/** Fabric mask*/
	FABRIC (1, "Fabric"),
	
	/** Medical mask*/
	MEDICAL (2,"Medical"),
	
	/** Respirator*/
	RESPIRATOR (3, "Respirator");

	private final int efficiency;
	private final String type;

	private Mask(int efficiency, String type) {
		this.efficiency = efficiency;
		this.type = type;
	}

	/**
	 * Returns the efficiency of the specified mask type.
	 * @param  int  COVID-19 transmission metre based on mask usage.
	 */
	public double getEfficiency() {
		return efficiency;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

