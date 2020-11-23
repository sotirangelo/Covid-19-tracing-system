/**
 * Business Types
 * This enumeration represents the different public-space/business types.
 * Each type of business is defined by
 *
 * @version 0.1 23 Nov 2020
 * @author Sotiris
 */
public enum BusinessType {
	RESTAURANT("Restaurant"),
	BAR("Bar"),
	OFFICE("Office"),
	STORE("Store"),
	MARKET("Market");

	private final String type;

	private BusinessType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
	
