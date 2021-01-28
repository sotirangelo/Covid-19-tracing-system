package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerEdit {
		
		/* Labels */
		
		@FXML
		private Label lbl1CustomerRegistration;
		
		@FXML
		private Label lbl2EditStatus;
		
		@FXML
		private Label lbl3FirstName;
		
		@FXML
		private Label lbl4LastName;
		
		@FXML
		private Label lbl5UserID;
		
		@FXML
		private Label lbl6Email;
		
		@FXML
		private Label lbl7PhoneNumber;
		
		@FXML
		private Label lbl8Age;
		
		@FXML
		private Label lbl9CustomerSignUp;
		
		@FXML
		private Label lbl10FirstNameStatus;
		
		@FXML
		private Label lbl11LastNameStatus;
		
		@FXML
		private Label lbl12UserIDStatus;
		
		@FXML
		private Label lbl13EmailStatus;
		
		@FXML
		private Label lbl14PhoneNumberStatus;
		
		@FXML
		private Label lbl15AgeStatus;
			
	
		
		/* Buttons */
		
		@FXML
		private Button btn1Register;
		
		@FXML
		private Button btn2Close;
		
		
		
		
		/* Text Fields */
		
		@FXML
		private TextField txt1FirstName;
		
		@FXML
		private TextField txt2LastName;
		
		@FXML
		private TextField txt3UserID;
		
		@FXML
		private TextField txt4Email;
		
		@FXML
		private TextField txt5PhoneNumber;
		
		@FXML
		private TextField txt6Age;
		
		
		
		public void SignUpButtonOnAction() {	
			validateFirstName();
			validateLastName();
			validateUserID();
			validateEmail();
			validatePhoneNumber();
			validateAge();
			if (validateFirstName() && validateLastName() && validateUserID() && validateEmail() && validatePhoneNumber() && validateAge()) {
				lbl2EditStatus.setTextFill(Color.GREEN);
				lbl2EditStatus.setText("Edit Succesful");
				/*
				Stage customerSignUpStage = (Stage) btn1Register.getScene().getWindow();
				customerSignUpStage.close();
				*/
			}
		}
		
			
			public boolean validateFirstName() {
			Pattern FirstNamepattern = Pattern.compile("(?i)[a-z]([- ',.a-z]{0,23}[a-z])");
			Matcher FirstNamemat;
				do {
				FirstNamemat = FirstNamepattern.matcher(txt1FirstName.getText());
		        if(FirstNamemat.matches()){
		            lbl10FirstNameStatus.setTextFill(Color.GREEN);
		            lbl10FirstNameStatus.setText("Okay");
		            return true;
		        } else {
		        	lbl10FirstNameStatus.setTextFill(Color.RED);
		        	lbl10FirstNameStatus.setText("Incorrect");
		        	lbl2EditStatus.setTextFill(Color.RED);
		        	lbl2EditStatus.setText("Edit Failed");
		        	return false;
		        }
			} while(!FirstNamemat.matches());
			}
			
			
			public boolean validateLastName() {
				Pattern LastNamepattern = Pattern.compile("(?i)[a-z]([- ',.a-z]{0,23}[a-z])");
				Matcher LastNamemat;
					do {
					LastNamemat = LastNamepattern.matcher(txt2LastName.getText());
			        if(LastNamemat.matches()){
			            lbl11LastNameStatus.setTextFill(Color.GREEN);
			            lbl11LastNameStatus.setText("Okay");
			            return true;
			        } else {
			        	lbl11LastNameStatus.setTextFill(Color.RED);
			        	lbl11LastNameStatus.setText("Incorrect");
			        	lbl2EditStatus.setTextFill(Color.RED);
			        	lbl2EditStatus.setText("Edit Failed");
			        	return false;
			        }
				} while(!LastNamemat.matches());
				}
			
			
			public boolean validateUserID() {
				Pattern UserIDpattern = Pattern.compile("^[0-9]{8}$");
				Matcher UserIDmat;
					do {
						UserIDmat = UserIDpattern.matcher(txt3UserID.getText());
			        if(UserIDmat.matches()){
			            lbl12UserIDStatus.setTextFill(Color.GREEN);
			            lbl12UserIDStatus.setText("Okay");
			            return true;
			        } else {
			        	lbl12UserIDStatus.setTextFill(Color.RED);
			        	lbl12UserIDStatus.setText("Incorrect");
			        	lbl2EditStatus.setTextFill(Color.RED);
			        	lbl2EditStatus.setText("Edit Failed");
			        	return false;
			        }
				} while(!UserIDmat.matches());
				}
			
			
		
			public boolean validateEmail() {
				Pattern Emailpattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
				Matcher Emailmat;
					do {
					Emailmat = Emailpattern.matcher(txt4Email.getText());
			        if(Emailmat.matches()){
			            lbl13EmailStatus.setTextFill(Color.GREEN);
			            lbl13EmailStatus.setText("Okay");
			            return true;
			        } else {
			        	lbl13EmailStatus.setTextFill(Color.RED);
			        	lbl13EmailStatus.setText("Incorrect");
			        	lbl2EditStatus.setTextFill(Color.RED);
			        	lbl2EditStatus.setText("Edit Failed");
			        	return false;
			        }
				} while(!Emailmat.matches());
				}
	
			
			public boolean validatePhoneNumber() {
				Pattern PhoneNumberpattern = Pattern.compile("^[0-9]{10}$");
				Matcher PhoneNumat;
				do {
					PhoneNumat = PhoneNumberpattern.matcher(txt5PhoneNumber.getText());
					if(PhoneNumat.matches()) {
						lbl14PhoneNumberStatus.setTextFill(Color.GREEN);
						lbl14PhoneNumberStatus.setText("Okay");
						return true;
					} else {
						lbl14PhoneNumberStatus.setTextFill(Color.RED);
						lbl14PhoneNumberStatus.setText("Incorrect");
						lbl2EditStatus.setTextFill(Color.RED);
						lbl2EditStatus.setText("Edit Failed");
						return false;	
					}
				} while(!PhoneNumat.matches());
			}
			
			public boolean validateAge() {
				Pattern Agepattern = Pattern.compile("^[1-9][0-9]?$|^100$");
				Matcher Agemat;
					do {
						Agemat = Agepattern.matcher(txt6Age.getText());
			        if(Agemat.matches()){
			            lbl15AgeStatus.setTextFill(Color.GREEN);
			            lbl15AgeStatus.setText("Okay");
			            return true;
			        } else {
			        	lbl15AgeStatus.setTextFill(Color.RED);
			        	lbl15AgeStatus.setText("Incorrect");
			        	lbl2EditStatus.setTextFill(Color.RED);
			        	lbl2EditStatus.setText("Edit Failed");
			        	return false;
			        }
				} while(!Agemat.matches());
				}
		
		

		
		
			public void exitButtonOnAction(ActionEvent event) {
				Stage customerEditStage = (Stage) btn2Close.getScene().getWindow();
				((Node)event.getSource()).getScene().getWindow().hide();
				customerEditStage.close();
			}
		
		
		
		}
	
