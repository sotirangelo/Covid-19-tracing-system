package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
		private Label lbl6Email;
		
		@FXML
		private Label lbl7PhoneNumber;
		
		@FXML
		private Label lbl8Password;
		
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
	
		/* Buttons */
		
		@FXML
		private Button btn1Register;
		
		@FXML
		private Button btn2Close;

		/* Text Fields */
			
		@FXML
		private TextField txt4Email;
		
		@FXML
		private TextField txt5PhoneNumber;

		/* Password Field */
		
		@FXML
		private PasswordField pass1UserPassword;
		
		private Person user = CustomerPasswordEdit.user;
		
		public void SignUpButtonOnAction() {
			validateEmail();
			validatePhoneNumber();
			if (validateEmail() && validatePhoneNumber()) {
				database.Access.editUserEmail(user.getUserID(), txt4Email.getText());
				database.Access.editUserPhoneNumber(user.getUserID(), Long.parseLong(txt5PhoneNumber.getText()));
				if (pass1UserPassword != null && !pass1UserPassword.getText().trim().isEmpty()) {
					database.Access.editBusinessPassword(user.getUserID(), pass1UserPassword.getText());				
				}
				lbl2EditStatus.setTextFill(Color.GREEN);
				lbl2EditStatus.setText("Edit Succesful");
			}
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
			
		
			public void exitButtonOnAction(ActionEvent event) {
				Stage customerEditStage = (Stage) btn2Close.getScene().getWindow();
				((Node)event.getSource()).getScene().getWindow().hide();
				customerEditStage.close();
			}
		
		
		
		}
	
