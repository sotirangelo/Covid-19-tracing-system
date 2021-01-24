package application;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	public void OutputButtonOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createOutput();
	}
	
	public void CustomerButtonOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createCustomer();
	}
	
	public void BusinessButtonOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createBusiness();
	}
	
	public void createCustomer() {
		try {
		Stage customerStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		customerStage.setTitle("Customer Log In");
		customerStage.setFullScreen(true);
		customerStage.setScene(scene);
		customerStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void createBusiness() {
		try {
		Stage businessStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Business.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		businessStage.setTitle("Business Log In");
		businessStage.setFullScreen(true);
		businessStage.setScene(scene);
		businessStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOutput() {
		try {
		Stage outputStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Output.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		outputStage.setTitle("Output");
		outputStage.setFullScreen(true);
		outputStage.setScene(scene);
		outputStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
