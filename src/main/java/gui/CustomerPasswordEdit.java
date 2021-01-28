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

public class CustomerPasswordEdit {
	
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
	
	/* Text */
	
	@FXML
	private TextField txt1CustomerID;
	
	/* Password */
	
	@FXML
	private PasswordField passCustomerPass;
	
	/* Buttons */
	@FXML
	private Button btn1Validate;
	
	@FXML
	private Button btn2Exit;
	
	
			
	public boolean validateUserID() {
		Pattern UserIDpattern = Pattern.compile("^[0-9]{8}$");
		Matcher UserIDmat;
			do {
			UserIDmat = UserIDpattern.matcher(txt1CustomerID.getText());
	        if(UserIDmat.matches()){
	            lbl2Status.setTextFill(Color.GREEN);
	            lbl2Status.setText("Validation Successful");
	            createCustomerEdit();
	            return true;
	        } else {
	        	 lbl2Status.setTextFill(Color.RED);
		         lbl2Status.setText("Validation Failed");
		         return false;
	        }
		} while(!UserIDmat.matches());
	
	}

	public void exitButtonOnAction(ActionEvent event) {
		Stage customerPassEditStage = (Stage) btn2Exit.getScene().getWindow();
		((Node)event.getSource()).getScene().getWindow().hide();
		customerPassEditStage.close();
	}

	public void ValidateOnAction(ActionEvent event) {
		lbl2Status.setText("Validation Status");
		if (validateUserID()) {
			((Node)event.getSource()).getScene().getWindow().hide();
		}
	}
	
	
	public void createCustomerEdit() {
		try {
		Stage customerEditStage = new Stage();
		customerEditStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/CustomerEdit.fxml"));
		Scene scene = new Scene(root,482,600);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		customerEditStage.setTitle("Javavirus� Covid19 Tracing App - Customer Edit Account");
		customerEditStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		customerEditStage.setScene(scene);
		customerEditStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
