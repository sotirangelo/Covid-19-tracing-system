package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	public class BusinessSignUpController {
		
		/* Labels */
		
		@FXML
		private Label lbl1Title;
		
		@FXML
		private Label lbl2BusinessID;
		
		@FXML
		private Label lbl3Name;
		
		@FXML
		private Label lbl4Space;
		
		@FXML
		private Label lbl5Height;
		
		@FXML
		private Label lbl6Type;
		
		@FXML
		private Label lbl7Email;
		
		@FXML
		private Label lbl8Password;
		
		@FXML
		private Label lbl9AER;
		
		@FXML
		private Label lbl10BusinessSignUp;
	
	
		
		/* Buttons */
		
		@FXML
		private Button btn1SignUp;
		
		@FXML
		private Button btn2Close;
		
		
		
		
		/* Text Fields */
		
		@FXML
		private TextField txt1ID;
		
		@FXML
		private TextField txt2Name;
		
		@FXML
		private TextField txt3Space;
		
		@FXML
		private TextField txt4Height;
		
		@FXML
		private TextField txt5Type;
		
		@FXML
		private TextField txt6Email;
		
		@FXML
		private TextField txt7AER;
		
		
		/* Password Fields */
		
		@FXML
		private PasswordField pass1Email;
		
		

		public void BusinessSignUp(ActionEvent event) throws Exception {
			Stage businessSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,384,189);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			businessSignUpStage.setTitle("Business Sign Up");
			businessSignUpStage.setFullScreen(true);
			businessSignUpStage.setScene(scene);
			businessSignUpStage.show();
		}

		
		public void closeButtonOnAction(ActionEvent event) {
			Stage businessSignUpStage = (Stage) btn2Close.getScene().getWindow();
			businessSignUpStage.close();
		}
		
		
		
		}
	