package application;

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
import javafx.scene.paint.Color;
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
		
		public void createCustomerSignUp() {
			try {
			Stage customerSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerSignUpStage.setFullScreen(true);
			customerSignUpStage.setTitle("Customer Sign Up");
			customerSignUpStage.setScene(scene);
			customerSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
		
