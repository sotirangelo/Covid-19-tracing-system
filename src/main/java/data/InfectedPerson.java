package data;

public class InfectedPerson extends Person { 
	private double infectionProbability;
	
	public InfectedPerson(Person p, double infectionProbability) {
		super(p.getUserID(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhoneNumber(), p.getPassword());
		this.infectionProbability = infectionProbability;
	}

	public double getPropability () { 
      		return this.infectionProbability; 
      	}

    	public void setProbability (double x) { 
      		infectionProbability = x; 
      	}
    	
    	public void increaseProbability(double prob) {
    		infectionProbability += prob;
    	}

}
        
