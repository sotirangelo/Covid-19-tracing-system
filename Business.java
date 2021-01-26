/*
 * Business
 *
 * Copyright (not) 2020 Javavirus
 */

/**
 * Business class.
 * This class represents a business, which is defined by its businessID, email, name, total floor area,
 * and its type (for now). 
 *
 * @version 0.1 26 Nov 2020
 * @author Sotiris
 */
public class Business {
	private String businessID;
	private String name;
	private double space;
	private double height;
    private BusinessType businessType;
	private String email;
	private String password;
    /** Air Exchange Rate */
    private AER ventilation; 


	public Business(String businessID, String email, String password, String name, double space, double height, BusinessType businessType, AER ventilation) {
		this.businessID = businessID;
		this.email = email;
		this.password = password;
		this.name = name;
		this.space = space;
		this.height = height;
		this.businessType = businessType;
		this.ventilation = ventilation;
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}
	
	public void setVentilation(AER ventilation) {
		this.ventilation = ventilation;
	}
	
	public AER getVentilation() {
		return ventilation;
	}
	
	public double getAER() {
		return ventilation.getValue();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSpace() {
		return space;
	}
	
	public double getHeight() {
		return height;
	}

	public void setSpace(double space) {
		this.space = space;
	}

    public void setHeight(double height) {
        this.height = height;
    }

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
  	/**
	*Calculates and returns the volume of a business
	*/
   	 public double getVolume() {
       	         return height * space;   
         }
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 *Finds and returns the Exertion of a healthy customer
	 */
	public double getHealthyCustExertion() {
		return businessType.getCustActivity().getIr();
	}
	/**
	 *Finds and returns the Exertion of an infected customer
	 */
	public double getInfectedCustExertion() {
		return businessType.getCustActivity().getErq();
	}
	/**
	 *Finds and returns the Exertion of a healthy employee
	 */
	public double getHealthyEmpExertion() {
		return businessType.getEmplActivity().getIr();
	}
	/**
	 *Finds and returns the Exertion of an infected employee
	 */
	public double getInfectedEmpExertion() {
		return businessType.getEmplActivity().getErq();
	}
	/** 
	 *Calculates and returns the total viral removal rate 
	 */
	public double getIVRR() {
		return (getAER() + 0.24 + 0.63);
	}		 
	@Override
	public String toString() {
		return "Business ID : " + businessID +
				", Business Name :" + name +
				", Email : " + email +
				", Floor Area : " + space +
				" m2, Business Type : " + businessType;
	}
}
