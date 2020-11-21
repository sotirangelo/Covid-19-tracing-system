/**
 * Exertion/Activity.
 * This enumeration represents the types of exertion that people make.
 * The level of exertion and consequently the breathing flow rate of each person,
 * varies, depending on their activity. Thus, each level of exertion/activity is defined
 * by a volumetric breathing flow rate, expressed in cubic metres per hour.
 *
 * @version 0.1 21 Nov 2020
 * @author Sotiris
 */
public enum Exertion {
	/** Resting */
	RESTING(0.49,"Resting"),
	/** Standing, as a low-level activity*/
	STANDING(0.54,"Standing");
	
	private final double breathingFlowRate ;
	private final String type;

	private Exertion(double breathingFlowRate, String type) {
		this.breathingFlowRate = breathingFlowRate;
		this.type = type;
	}

	public double getBreathingFlowRate() {
		return breathingFlowRate;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

