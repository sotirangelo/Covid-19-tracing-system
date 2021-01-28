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
	RESTAURANT(Exertion.R_S, "Restaurant"),
	BAR(Exertion.R_L_S, "Bar"),
	OFFICE(Exertion.R_S, "Office"),
	STORE(Exertion.L_E_S, "Store"),
	MARKET(Exertion.L_E_S, "Market");

	private final String type;

	/** Square metres distance per person */
	private final Exertion customerActivity;

	private BusinessType(Exertion customerActivity, String type) {
		this.type = type;
		this.customerActivity = customerActivity;
	}

	@Override
	public String toString() {
		return type;
	}

	public Exertion getCustActivity() {
		return customerActivity;
	}
}

