package application;

//implements Comparable
public class InfectedPerson extends Person { 
	private double infectionPropability;
	
	public InfectedPerson(Person p, double infectionPropability) {
		super(p.getUserID(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhoneNumber(), p.getAgeCategory(), p.getPassword());
		this.infectionPropability = infectionPropability;
	}

	public double getPropability () { 
    		return this.infectionPropability; 
    	}

  	public void setPropability (double x) { 
    		infectionPropability = x; 
    	}
/*
	@Override
	public int compareTo(InfectedPerson comparestu) {
		int comparescore = ((InfectedPerson)comparestu).getScore();
		return compareage-this.comparescore;
	}
	
	}
*/
}