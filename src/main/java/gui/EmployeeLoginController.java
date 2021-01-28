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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeLoginController {
	
/* Labels */
	
	@FXML
	private Label lbl1Title;
	
	@FXML
	private Label lbl2Status;
	
	@FXML
	private Label lbl3CustomerID;
	
	@FXML
	private Label lbl4Password;
	
	@FXML
	private Label lbl5CustomerValidation;
	
	@FXML
	private Label lbl6SignUp;
	
	
	
	/* Text */
	
	@FXML
	private TextField txt1Email;
	
	/* Password */
	
	@FXML
	private PasswordField passCustomerPass;
	
	/* Buttons */
	@FXML
	private Button btn1Validate;
	
	@FXML
	private Button btn4Exit;
	
	@FXML
	private Button btn3SignUp;
	
	
			
	public boolean validateEmail() {
		Pattern Emailpattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher Emailmat;
			do {
				Emailmat = Emailpattern.matcher(txt1Email.getText());
	        if(Emailmat.matches()){
	            lbl2Status.setTextFill(Color.GREEN);
	            lbl2Status.setText("Validation Successful");
	            return true;
	        } else {
	        	 lbl2Status.setTextFill(Color.RED);
		         lbl2Status.setText("Validation Failed");
		         return false;
	        }
		} while(!Emailmat.matches());
	
	}

	public void exitButtonOnAction(ActionEvent event) {
		Stage employeeLogInStage = (Stage) btn4Exit.getScene().getWindow();
		((Node)event.getSource()).getScene().getWindow().hide();
		employeeLogInStage.close();
	}

	public void ValidateOnAction(ActionEvent event) {
		lbl2Status.setText("Validation Status");
		if (validateEmail()) {
			createOutput();
		}
	}
	
	public void signUpButtonOnAction(ActionEvent event) {
		createEmployeeSignUp();
	}
	
	
	public void createEmployeeSignUp() {
		try {
		Stage createEmployeeSignUpStage = new Stage();
		createEmployeeSignUpStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployeeSignUp.fxml"));
		Scene scene = new Scene(root,482,600);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		createEmployeeSignUpStage.setTitle("Javavirus Covid19 Tracing App - EODY User Sign Up");
		createEmployeeSignUpStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		createEmployeeSignUpStage.setScene(scene);
		createEmployeeSignUpStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOutput() {
		try {
		Stage createcertainInfectionStage = new Stage();
		createcertainInfectionStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/CertainInfection.fxml"));
		Scene scene = new Scene(root,552,339);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		createcertainInfectionStage.setTitle("Javavirus Covid19 Tracing App - Covid-19 Case");
		createcertainInfectionStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		createcertainInfectionStage.setScene(scene);
		createcertainInfectionStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
