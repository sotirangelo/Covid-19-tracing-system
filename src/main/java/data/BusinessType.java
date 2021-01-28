package data;
/**
 * Business Types
 * This enumeration represents the different public-space/business types.
 * Each type of business is defined by a capacity, expressed in square metres
 * per person.
 *
 * @version 0.1 23 Nov 2020
 * @author Sotiris
 */
public enum BusinessType {
	//TODO fix restaurant and bar capacity
	RESTAURANT(Exertion.R_S, Exertion.L_E_S, "Restaurant"),
	BAR(Exertion.R_L_S, Exertion.S_L_S, "Bar"),
	OFFICE(Exertion.R_S, Exertion.S_S, "Office"),
	STORE(Exertion.L_E_S, Exertion.S_S, "Store"),
	MARKET(Exertion.L_E_S, Exertion.S_S, "Market");

	private final String type;

	/** Square metres distance per person */
	private final Exertion customerActivity;
	private final Exertion employeeActivity;

	private BusinessType(Exertion customerActivity, Exertion employeeActivity, String type) {
		this.type = type;
		this.customerActivity = customerActivity;
		this.employeeActivity = employeeActivity;
	}

	@Override
	public String toString() {
		return type;
	}

	public Exertion getCustActivity() {
		return customerActivity;
	}
	
	public Exertion getEmplActivity() {
		return employeeActivity;
	}
}

