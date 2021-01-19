public enum AER {
	
    NATURAL(0.35, "Natural Ventilation"),
    
    OPEN(3, "Open doors and windows");

    private final double value;
    private final String type;

    private AER(double value, String type) {
        this.value = value;
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type;
    }
}
