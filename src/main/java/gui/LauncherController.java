package gui;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LauncherController {
	
	/* Labels */
	
	@FXML
	private Label lbl1AppName;
	
	@FXML
	private Label lbl2Welcome;
	
	@FXML
	private Label lbl3Choose;
	
	
	/* Buttons */
	
	@FXML
	private Button btn1Customer;
	
	@FXML
	private Button btn2Business;
	
	@FXML
	private Button btn3Output;
	
	@FXML
	private Button btn4Exit;

	
	public void exitButtonOnAction(ActionEvent event) {
		Stage primaryStage = (Stage) btn4Exit.getScene().getWindow();
		primaryStage.close();
	}
	
	
	
	public void CustomerButtonOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createCustomer();
	}
	
	public void BusinessButtonOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createBusinessSignUp();
	}
	
	public void createCustomer() {
		try {
		Stage customerStage = new Stage();
		customerStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Customer.fxml"));
		Scene scene = new Scene(root,552,339);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		customerStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		customerStage.setTitle("Javavirus� Covid19 Tracing App - Customer Log In");
		customerStage.setScene(scene);
		customerStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void createBusinessSignUp() {
		try {
		Stage businessEditStage = new Stage();
		businessEditStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BusinessEdit.fxml"));
		Scene scene = new Scene(root,482,600);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		businessEditStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		businessEditStage.setTitle("Javavirus� Covid19 Tracing App - Business Edit");
		businessEditStage.setScene(scene);
		businessEditStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
