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
	RESTAURANT(-1, "Restaurant"),
	BAR(-1, "Bar"),
	OFFICE(10, "Office"),
	STORE(8, "Store"),
	MARKET(15, "Market");

	private final String type;

	/** Square metres distance per person */
	private final int capacity;

	private BusinessType(int capacity, String type) {
		this.type = type;
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return type;
	}

	/** Capacity according to business type.
	 * This method returns the capacity of a business type,
	 * expressed in square metres per person.
	 *
	 * @return int Square metres
	 */
	public int getCapacity() {
		return capacity;
	}
}

