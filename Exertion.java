public enum Exertion {
	RESTING(0.49,"Resting"),
	STANDING(0.54,"Standing"),
	WALKING(1.38,"Walking");
	
	private final double breathingFlowRate ;
	private final String type;

	private Mask(double breathingFlowRate, String type) {
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

