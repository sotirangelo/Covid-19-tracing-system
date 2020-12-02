
//implements Comparable
public class InfectedPerson extends Person { 
	private double score;
	
	public InfectedPerson(String UserID, String firstName, String lastName, String email, long phoneNumber,
			Age ageCategory) {
		super(UserID, firstName, lastName, email, phoneNumber, ageCategory);

	}

	public double getScore () { 
      		return this.score; 
      	}

    	public void setScore (double x) { 
      		this.score = x; 
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
        
