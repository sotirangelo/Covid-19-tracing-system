/*
 * Business
 *
 * Copyright (not) 2020 Javavirus
 */

import java.util.ArrayList;

/**
 * Business class.
 * This class represents a business, which is defined by its total floor area,
 * and its type (for now). 
 *
 * @version 0.1 26 Nov 2020
 * @author Sotiris
 */
public class Business {
	private double space;
	private BusinessType businessType;
	public ArrayList<Recording> recordings = new ArrayList<Recording>();

	public Business(double space, BusinessType businessType) {
		this.space = space;
		this.businessType = businessType;
	}

	public double getSpace() {
		return space;
	}

	public void setSpace(double space) {
		this.space = space;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}
}
