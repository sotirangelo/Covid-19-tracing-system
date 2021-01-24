package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class CustomerSignUpController {
		
		/* Labels */
		
		@FXML
		private Label lbl1CustomerRegistration;
		
		@FXML
		private Label lbl2RegistrationStatus;
		
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
		
		

		
		
		public void closeButtonOnAction(ActionEvent event) {
			Stage customerSignUpStage = (Stage) btn2Close.getScene().getWindow();
			customerSignUpStage.close();
		}
		
		
		
		}
	




