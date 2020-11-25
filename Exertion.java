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
	/** Resting */
	RESTING(0.5,"Resting"),
	/** Standing, as a low-level activity*/
	STANDING(0,"Standing");
	
	private final int exertionFactor ;
	private final String type;

	private Exertion(double exertionFactor, String type) {
		this.exertionFactor = exertionFactor;
		this.type = type;
	}

	/**
	 * Returns the exertion factor in transmission.
	 * @return int  COVID-19 transmission metre based on activity/exertion. 
	 */
	public int getExertionFactor() {
		return exertionFactor;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

