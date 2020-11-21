public enum Mask {
	NONE (0,"None"),
	FABRIC (0.5, "Fabric"),
	MEDICAL (0.75,"Medical"),
	RESPIRATOR (0.95, "Respirator");

	private final double efficiency;
	private final String type;

	private Mask(double efficiency, String type) {
		this.efficiency = efficiency;
		this.type = type;
	}

	public double getEfficiency() {
		return efficiency;
	}
	
	@Override
	public String toString() {
		return type;
	}
}

