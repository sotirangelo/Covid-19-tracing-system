package gui;
 
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class RowPerson {
	private final SimpleStringProperty FirstName;
	private final SimpleStringProperty LastName;
   private final SimpleLongProperty PhoneNumber;
   private final SimpleStringProperty Email;
   private final SimpleDoubleProperty Probability;

public RowPerson(String FirstName, String LastName, long PhoneNumber, String Email, double Probability) {
	this.FirstName = new SimpleStringProperty(FirstName);
	this.LastName = new SimpleStringProperty(LastName);
    this.Email = new SimpleStringProperty(Email);
    this.PhoneNumber = new SimpleLongProperty(PhoneNumber);
    this.Probability = new SimpleDoubleProperty(Probability);
    }
 
   

    public String getFirstName() {
        return FirstName.get();
    }
 
    public void setFirstName(String name) {
        FirstName.set(name);
    }
    
    public String getLastName() {
        return LastName.get();
    }
 
    public void setName(String name) {
        LastName.set(name);
    }
        
    public long getPhoneNumber() {
        return PhoneNumber.get();
    }
    
    public void setPhoneNumber(long pNumber) {
        PhoneNumber.set(pNumber);
    }
    
    public String getEmail() {
        return Email.get();
    }
    
    public void setEmail(String email) {
        Email.set(email);
    }
    
    public double getProbability() {
        return Probability.get();
    }
    
    public void setProbability(double prob) {
        Probability.set(prob);
    }
}
