package gui;

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
import javafx.scene.Node;
import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

	public class CustomerController implements Initializable {
		
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
		private Button btnCheckIn;
		
		@FXML
		private Button btnCheckOut;
		
		@FXML
		private Button btn3Exit;
		
		@FXML
		private Button btn4Edit;
		
		
		/* Text Fields */
		
		@FXML
		private TextField txt1UserID;
		
		
		// Combobox
		
		@FXML
		private ComboBox<String> comb1;
		
		

		public void exitButtonOnAction(ActionEvent event) {
			Stage customerStage = (Stage) btn3Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			customerStage.close();
		}
		
		public void CustomerSignUpButtonOnAction(ActionEvent event) {
			createCustomerSignUp();
		}
		
		public void CustomerEditButtonOnAction(ActionEvent event) {
			createCustomerPasswordEdit();
		}
		
		
		public void createCustomerPasswordEdit() {
			try {
			Stage customerPassEditStage = new Stage();
			customerPassEditStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/CustomerPasswordEdit.fxml"));
			Scene scene = new Scene(root,552,339);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			customerPassEditStage.setTitle("Javavirus Covid19 Tracing App - User Account Edit (Validation)");
			customerPassEditStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
			customerPassEditStage.setScene(scene);
			customerPassEditStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void createCustomerSignUp() {
			try {
			Stage customerSignUpStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/CustomerSignUp.fxml"));
			Scene scene = new Scene(root,482,600);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			customerSignUpStage.setTitle("Javavirus Covid19 Tracing App - User Sign Up");
			customerSignUpStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
			customerSignUpStage.setScene(scene);
			customerSignUpStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		
		
		public void CheckInButtonOnAction(ActionEvent event) {
		lbl6UserLogInStatus.setText("Record Status ");
		validateUserID();
		}
		
		public void CheckOutButtonOnAction(ActionEvent event) {
			lbl6UserLogInStatus.setText("Record Status ");
			validateUserID();
		}
		
		
		public void validateUserID() {
		Pattern UserIDpattern = Pattern.compile("^[0-9]{8}$");
		Matcher UserIDmat;
			do {
			UserIDmat = UserIDpattern.matcher(txt1UserID.getText());
	        if(UserIDmat.matches()){
	            lbl6UserLogInStatus.setTextFill(Color.GREEN);
	            lbl6UserLogInStatus.setText("Check-in Successful");
	            break;
	        } else {
	        	 lbl6UserLogInStatus.setTextFill(Color.RED);
		         lbl6UserLogInStatus.setText("Check-in Failed");
		         break;
	        }
		} while(!UserIDmat.matches());
	
	}
		
		public void initialize(URL url, ResourceBundle rb) {
			ObservableList<String> list = FXCollections.observableArrayList("None", "Fabric", "Medical","Respirator");
			comb1.setItems(list);
		}
	}
		
	
	


