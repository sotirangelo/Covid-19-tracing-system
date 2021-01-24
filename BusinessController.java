package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BusinessController {
		
		/* Labels */
		
		@FXML
		private Label lbl1Title;
		
		@FXML
		private Label lbl3BusinessID;
		
		@FXML
		private Label lbl4Password;
		
		@FXML
		private Label lbl5SignUpText;
		
		@FXML
		private Label lbl5BusinessLogIn;
		
		
		
		/* Buttons */
		
		@FXML
		private Button btn2LogIn;
		
		@FXML
		private Button btn3SignUp;
		
		@FXML
		private Button btn4Exit;
		
		
		/* Text Fields */
		
		@FXML
		private TextField txt1BusinessID;
		
		
		/* Password Fields */
		
		@FXML
		private PasswordField passBusinessPass;
		
		
		
		public void exitButtonOnAction(ActionEvent event) {
			Stage businessStage = (Stage) btn4Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			businessStage.close();
		}
		
		public void BusinessSignUpButtonOnAction(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();
			createBusinessSignUp();
		}
		
		public void createBusinessSignUp() {
			try {
			Stage businessSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/BusinessSignUp.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			businessSignUpStage.setFullScreen(true);
			businessSignUpStage.setTitle("Business Sign Up");
			businessSignUpStage.setScene(scene);
			businessSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
		}
	}
}
