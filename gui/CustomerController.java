package gui;

	import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class CustomerController {
		
		/* Labels */
		
		@FXML
		private Label lbl1Title;
		
		@FXML
		private Label lbl3UserID;
		
		@FXML
		private Label lbl4NewHere;
		
		@FXML
		private Label lbl5CustomerLogIn;
		
		@FXML
		private Label lbl6UserLogInStatus;
		
		
	
	
		
		/* Buttons */
		
		@FXML
		private Button btn1LogIn;
		
		@FXML
		private Button btn2SignUp;
		
		@FXML
		private Button btn3Exit;
		
		@FXML
		private Button btn4Edit;
		
		
		/* Text Fields */
		
		@FXML
		private TextField txt1UserID;
		
		

		public void exitButtonOnAction(ActionEvent event) {
			Stage customerStage = (Stage) btn3Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			customerStage.close();
		}
		
		public void CustomerSignUpButtonOnAction(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();
			createCustomerSignUp();
		}
		
		public void CustomerEditButtonOnAction(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();
			createCustomerPasswordEdit();
			
		}
		
		
		public void createCustomerPasswordEdit() {
			try {
			Stage customerPassEditStage = new Stage();
			customerPassEditStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerPasswordEdit.fxml"));
			Scene scene = new Scene(root,552,339);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerPassEditStage.setTitle("Javavirus® Covid19 Tracing App - Customer Edit Account (Validation)");
			customerPassEditStage.getIcons().add(new Image("/application/Javavirus GUI images/Javavirus Logo.png"));
			customerPassEditStage.setScene(scene);
			customerPassEditStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void createCustomerSignUp() {
			try {
			Stage customerSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,482,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerSignUpStage.setTitle("Javavirus® Covid19 Tracing App - Customer Sign Up");
			customerSignUpStage.getIcons().add(new Image("/application/Javavirus GUI images/Javavirus Logo.png"));
			customerSignUpStage.setScene(scene);
			customerSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		
		
		public void LogInButtonOnAction(ActionEvent event) {
		lbl6UserLogInStatus.setText("Record Status");
		validateUserID();
		/*
			Stage customerStage = (Stage) btn1LogIn.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			customerStage.close();*/
			
		}
		
		
		public void validateUserID() {
		Pattern UserIDpattern = Pattern.compile("^[0-9]{8}$");
		Matcher UserIDmat;
			do {
			UserIDmat = UserIDpattern.matcher(txt1UserID.getText());
	        if(UserIDmat.matches()){
	            lbl6UserLogInStatus.setTextFill(Color.GREEN);
	            lbl6UserLogInStatus.setText("Record Successful");
	            break;
	        } else {
	        	 lbl6UserLogInStatus.setTextFill(Color.RED);
		         lbl6UserLogInStatus.setText("Record Failed");
		         break;
	        }
		} while(!UserIDmat.matches());
	
	}
	}
		
	
	


