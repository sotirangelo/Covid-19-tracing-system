package gui;


/**
 * Customer's age group.
 * This enumeration represents the customer's age group that can be used.
 * Each age group is defined by an efficiency metre, ranging from 0 to 3, depending on the mask type.
 * (higher means more vulnerable against COVID-19)
 *
 * @version 0.1 26 Nov 2020
 * @author Serafeim
 */
 
 
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
	
	/** Age 31-60*/
	THIRTY (2,"Above thirty"),
	
	/** Age 61-100*/
	SIXTY (3, "Above sixty");

	private final int vulnerability;
	private final String age;

	private Age(int vulnerability, String age) {
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
	
	public int findYourVulnerability(String age) {
		int x = 0;
		if (Integer.parseInt(age) >= 1 && Integer.parseInt(age) <= 18) {
			x =0;
		}else if (Integer.parseInt(age) <= 30) {
			x = 1;
		}else if (Integer.parseInt(age) <= 60) {
			x = 2;
		}else if (Integer.parseInt(age) <=100) {
			x = 3;
		}
		return x;
	}
	
	@Override
	public String toString() {
		return age;
	}
}