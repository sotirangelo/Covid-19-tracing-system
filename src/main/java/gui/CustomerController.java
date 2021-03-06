package gui;

	import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Mask;
import data.Person;
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
		
		private String businessID = BusinessController.businessID;
		private String userID;
		private Mask maskType;
		private Date getDate() {
			Date dateNow = new Date();
			return dateNow;
		}
		
		public void CheckInButtonOnAction(ActionEvent event) {
			lbl6UserLogInStatus.setText("Record Status");
			if (validateUserID()) {
				java.util.Date utilDate = getDate();
				if (maskType != null) {
					if (database.Access.checkIn(businessID, userID, utilDate, maskType)) {
						lbl6UserLogInStatus.setTextFill(Color.GREEN);
						lbl6UserLogInStatus.setText("Check-in Successful");
					} else {
						lbl6UserLogInStatus.setTextFill(Color.RED);
						lbl6UserLogInStatus.setText("Check-in Failed");
					}
				} else {
	        			lbl6UserLogInStatus.setTextFill(Color.RED);
	        			lbl6UserLogInStatus.setText("Check-in Failed");
				}
			} else {
				lbl6UserLogInStatus.setTextFill(Color.RED);
        		lbl6UserLogInStatus.setText("Check-in Failed");
			}
		}
		
		public void CheckOutButtonOnAction(ActionEvent event) {
			lbl6UserLogInStatus.setText("Record Status");
			if (validateUserID()) {
				java.util.Date utilDate = getDate();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				if (database.Access.checkOut(businessID, userID, sqlDate)) {
					lbl6UserLogInStatus.setTextFill(Color.GREEN);
					lbl6UserLogInStatus.setText("Check-out Successful");
				} else {
					lbl6UserLogInStatus.setTextFill(Color.RED);
	        		lbl6UserLogInStatus.setText("Check-out Failed");
				}
	        } else {
	        		lbl6UserLogInStatus.setTextFill(Color.RED);
	        		lbl6UserLogInStatus.setText("Check-out Failed");
	       	}
		}
		
		public boolean validateUserID() {
			Pattern UserIDpattern = Pattern.compile("^[0-9]{8}$");
			Matcher UserIDmat;
			boolean match;
			boolean authenticate = false;
			userID = txt1UserID.getText();
			if (comb1.getValue() != null) {
				maskType = Mask.valueOf(comb1.getSelectionModel().getSelectedItem().toString());
			}
			do {
				UserIDmat = UserIDpattern.matcher(txt1UserID.getText());
	        	if(UserIDmat.matches()){
	        		match = true;
	        		break;
	        	} else {
	        		match = false;
	        		break;
	        	}
			} while(!UserIDmat.matches());
			if (match) {
				Person user = database.Access.findUser(userID);
				if (user != null) {
					authenticate = true;
				}
			}
			return (match && authenticate);
		}
		
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<String> list = FXCollections.observableArrayList("NONE", "FABRIC", "MEDICAL", "RESPIRATOR");
		comb1.setItems(list);
	}
}
