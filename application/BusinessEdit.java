package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BusinessEdit implements Initializable {
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
	
	@FXML
	private Label labelVer1;
	
	@FXML
	private Label labelVer2;
	
	@FXML
	private Label labelVer3;
	
	@FXML
	private Label labelVer4;
	
	@FXML
	private Label labelVer5;
	
	@FXML
	private Label labelVer6;
	
	@FXML
	private Label labelVer7;
	
	@FXML
	private Label labelVer8;
	
	@FXML
	private Label RegStatus;
	
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
	
	
	/* ComboBoxes */
	
	@FXML
	private ComboBox<String> comb1;
	
	@FXML
	private ComboBox<String> comb2;
	
	
	
	
	public boolean validateBusid() {
		Pattern BussIdpattern = Pattern.compile("^[0-9]{8}$");
		Matcher BussIdMatcher;
		do {
			BussIdMatcher = BussIdpattern.matcher(txt1ID.getText());
			if (BussIdMatcher.matches()) {
				 labelVer1.setTextFill(Color.GREEN);
		         labelVer1.setText("Okay");
		         return true;
			} else {
				labelVer1.setTextFill(Color.RED);
	        	labelVer1.setText("Incorrect");
	        	RegStatus.setTextFill(Color.RED);
	        	RegStatus.setText("Edit Failed");
		         return false;
			}
		} while (!BussIdMatcher.matches());
	}
	
	public boolean validateBname() {
		Pattern Busnamepattern = Pattern.compile("(?i)[a-z]([- ',.a-z]{0,23}[a-z])");
		Matcher BusNameMat;
		do {
			BusNameMat = Busnamepattern.matcher(txt2Name.getText());
			if (BusNameMat.matches()) {
				labelVer2.setTextFill(Color.GREEN);
				labelVer2.setText("Okay");
				return true;
			} else {
				labelVer2.setTextFill(Color.RED);
				labelVer2.setText("Incorrect");
				RegStatus.setTextFill(Color.RED);
				RegStatus.setText("Edit Failed");
				return false;
			}
		} while (!BusNameMat.matches());
		
	}
	
	public boolean validateBspace() {
		Pattern BusSpacepatt = Pattern.compile("^[0-9]{1,6}-{0}$");
		Matcher BspaceMat;
		do {
			BspaceMat = BusSpacepatt.matcher(txt3Space.getText());
			if (BspaceMat.matches()) {
				labelVer3.setTextFill(Color.GREEN);
				labelVer3.setText("Okay");
				return true;
			} else {
				labelVer3.setTextFill(Color.RED);
				labelVer3.setText("Incorrect");
				RegStatus.setTextFill(Color.RED);
				RegStatus.setText("Edit Failed");
				return false;
			}
		} while (!BspaceMat.matches());
	}
	
	public boolean validateHeight () {
		Pattern BHeightPatt = Pattern.compile("^[0-9]{1,4}-{0}$");
		Matcher BHeightMat;
		do {
			BHeightMat = BHeightPatt.matcher(txt4Height.getText());
			if (BHeightMat.matches()) {
				labelVer4.setTextFill(Color.GREEN);
				labelVer4.setText("Okay");
				return true;
			} else {
				labelVer4.setTextFill(Color.RED);
				labelVer4.setText("Incorrect");
				RegStatus.setTextFill(Color.RED);
				RegStatus.setText("Edit Failed");
				return false;
			}
		} while (!BHeightMat.matches());
	}
	
	public boolean validateEmail() {
		Pattern BemailPatt =  Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher EmailMat;
		do {
			EmailMat = BemailPatt.matcher(txt6Email.getText());
			if (EmailMat.matches()) {
				labelVer6.setTextFill(Color.GREEN);
				labelVer6.setText("Okay");
				return true;
			} else {
				labelVer6.setTextFill(Color.RED);
				labelVer6.setText("Incorrect");
				RegStatus.setTextFill(Color.RED);
				RegStatus.setText("Edit Failed");
				return false;
			}
		} while(!EmailMat.matches());
		
	}
	
	
	
	

	public void BusinessEdit(ActionEvent event) throws Exception {
		Stage businessEditStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/BusinessEdit.fxml"));
		Scene scene = new Scene(root,384,189);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		businessEditStage.getIcons().add(new Image("/application/Javavirus GUI images/Javavirus Logo.png"));
		businessEditStage.setTitle("Javavirus® Covid19 Tracing App - Business Sign Up");
		businessEditStage.setScene(scene);
		businessEditStage.show();
	}

	
	public void closeButtonOnAction(ActionEvent event) {
		Stage businessSignUpStage = (Stage) btn2Close.getScene().getWindow();
		businessSignUpStage.close();
	}



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> list = FXCollections.observableArrayList("Restaurant", "Bar" , "Office", "Store", "Market");
		comb1.setItems(list);
		ObservableList<String> list1 = FXCollections.observableArrayList("Natural", "Open");
		comb2.setItems(list1);
	}
	
	public void SignUpButtonOnAction1() {
		validateBusid();
		validateBname();
		validateBspace();
		validateHeight();
		validateEmail();
		if (validateBusid() && validateBname() && validateBspace() && validateHeight() && validateEmail()) {
			RegStatus.setTextFill(Color.GREEN);
			RegStatus.setText("Edit Succesful");
		}
		
	}
	
	

}
