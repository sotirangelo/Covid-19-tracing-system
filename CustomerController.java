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

	public class CustomerController {
		
		/* Labels */
		
		@FXML
		private Label lbl1Title;
		
		@FXML
		private Label lbl2EnterID;
		
		@FXML
		private Label lbl3UserID;
		
		@FXML
		private Label lbl4NewHere;
	
	
		
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
		
		


		public void Customer(ActionEvent event) throws Exception {
			Stage customerStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerStage.setScene(scene);
			customerStage.show();
		}
		
		public void exitButtonOnAction(ActionEvent event) {
			Stage customerStage = (Stage) btn3Exit.getScene().getWindow();
			customerStage.close();
		}
		
		public void CustomerSignUpButtonOnAction(ActionEvent event) {
			createCustomerSignUp();
		}
		
		public void createCustomerSignUp() {
			try {
			Stage customerSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			customerSignUpStage.setScene(scene);
			customerSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
		}
		
		}
	

	}

