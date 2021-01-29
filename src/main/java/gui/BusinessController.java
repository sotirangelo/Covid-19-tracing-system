package gui;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.javafx.logging.Logger;

import data.Business;
import javafx.animation.FadeTransition;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
		
		@FXML
		private Label labelStatusLogIn;
		
		@FXML
		private Label labelStatusLogIn1;
		
		@FXML
		private Label label6Status;
		
		@FXML
		private Label lbl7Welcome;
		
		/* Buttons */
		
		@FXML
		private Button btn2LogIn;
		
		@FXML
		private Button btn3SignUp;
		
		@FXML
		private Button btn3Output;
		
		@FXML
		private Button btn4Exit;
		
		/* Text Fields */
		
		@FXML
		private TextField txt1BusinessID;
		
		/* Password Fields */
		
		@FXML
		private PasswordField passBusinessPass;
		
		/* Business Object */ 
		
		private Business business = null;
		
		public static String businessID;
		
		public void btn3SignInOnAction(ActionEvent event) {
			label6Status.setText("Login Status");
			if (validateSignInId()) {
				((Node)event.getSource()).getScene().getWindow().hide();
				label6Status.setTextFill(Color.GREEN);
				label6Status.setText("Login Successful");
				businessID = business.getBusinessID();
				try {
					Stage launcherStage = new Stage();
					launcherStage.initModality(Modality.APPLICATION_MODAL);
					Parent root = FXMLLoader.load(getClass().getResource("/fxml/Launcher.fxml"));
					Scene scene = new Scene(root,430,320);
					scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
					launcherStage.setScene(scene);
					launcherStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
					launcherStage.setTitle("Javavirus Covid19 Tracing App");
					launcherStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				label6Status.setTextFill(Color.RED);
				label6Status.setText("Login Failed");
			}
			}
		
		public boolean validateSignInId() {
			Pattern BusIDPatt = Pattern.compile("^[0-9]{8}$");
			Matcher BusIDmat;
			boolean match;
			boolean authenticate = false;
			String businessID = txt1BusinessID.getText();
			String password = passBusinessPass.getText();
			do {
				BusIDmat = BusIDPatt.matcher(txt1BusinessID.getText());
				if (BusIDmat.matches()) {
					labelStatusLogIn1.setTextFill(Color.GREEN);
					labelStatusLogIn1.setText("Okay");
					match = true;
					break;
				} else {
					labelStatusLogIn1.setTextFill(Color.RED);
					labelStatusLogIn1.setText("Incorrect");
					match = false;
					break;
				}
			} while (!BusIDmat.matches());
			if (match) {
				business = database.Access.authenticateBusiness(businessID, password);
				if (!(business == null)) {
					authenticate = true;
				}
			}
			return (match && authenticate);
		}
		
		
		
		public void exitButtonOnAction(ActionEvent event) {
			Stage businessStage = (Stage) btn4Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			businessStage.close();
		}
		
		public void BusinessSignUpButtonOnAction(ActionEvent event) {
			createBusinessSignUp();
		}
		
		public void createBusinessSignUp() {
			try {
			Stage businessSignUpStage = new Stage();
			businessSignUpStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/BusinessSignUp.fxml"));
			Scene scene = new Scene(root,482,600);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			businessSignUpStage.setTitle("Javavirus Covid19 Tracing App - Business Sign Up");
			businessSignUpStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
			businessSignUpStage.setScene(scene);
			businessSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			
		}
	}
		
		public void createOutput() {
			try {
			Stage employeeLoginStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployeeLogin.fxml"));
			Scene scene = new Scene(root,552,339);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			employeeLoginStage.setTitle("Javavirus Covid19 Tracing App - EODY User Validation");
			employeeLoginStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
			employeeLoginStage.setScene(scene);
			employeeLoginStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void OutputButtonOnAction(ActionEvent event) {
			((Node)event.getSource()).getScene().getWindow().hide();
			createOutput();
		}
		
		
}