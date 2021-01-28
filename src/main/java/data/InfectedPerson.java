package data;

public class InfectedPerson extends Person { 
	private double infectionPropability;
	
	public InfectedPerson(Person p, double infectionPropability) {
		super(p.getUserID(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhoneNumber(), p.getPassword());
		this.infectionPropability = infectionPropability;
	}

	public double getPropability () { 
      		return this.infectionPropability; 
      	}

    	public void setPropability (double x) { 
      		infectionPropability = x; 
      	}

}
        
