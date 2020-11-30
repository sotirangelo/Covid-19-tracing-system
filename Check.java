/*
 * Check types used.
 * This enumeration represents the check types (Check in/Check out) that can be used.
 *
 * @version 0.1 30 Nov 2020
 * @author Ioannis
 */

public enum Check {
	
    /** Check In*/
	In ("CheckIn"),
	
	/** Check Out*/
	Out ("CheckOut");


	
	private final String type;

	private Check(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
