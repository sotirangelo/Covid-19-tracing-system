/**
 * Customer's age group.
 * This enumeration represents the customer's age group that can be used.
 * Each age group is defined by an efficiency metre, ranging from 0 to 3, depending on the mask type.
 * (higher means more vulnerable against COVID-19)
 *
 * @version 0.1 26 Nov 2020
 * @author Serafeim
 */
 
 
public enum Age {
	/** Age 1-18*/
	UNDERAGE (0,"Underage"),

	/** Age 19-30*/
	EIGHTEEN (1, "Above eighteen"),
	
	/** Age 31-50*/
	THIRTY (2,"Above thirty"),
	
	/** Age 51-100*/
	FIFTY (3, "Above fifty");

	private final int vulnerability;
	private final int age;

	private (int vulnerability, int age) {
		this.vulnerability = vulnerability;
		this.age = age;
	}

	/**
	 * Returns the vulnerability of the customer to COVID 19.
	 * @param  int  COVID-19 transmission metre based on mask usage.
	 */
	public double getVulnerability() {
		return vulnerability;
	}
	
	@Override
	public String toString() {
		return age;
	}
}
