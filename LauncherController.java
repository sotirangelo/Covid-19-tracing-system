package application;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LauncherController {
	
	/* Labels */
	
	@FXML
	private Label lbl1AppName1;
	
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

	public void Launcher(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
		Scene scene = new Scene(root,417,79);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	public void exitButtonOnAction(ActionEvent event) {
		Stage primaryStage = (Stage) btn4Exit.getScene().getWindow();
		primaryStage.close();
	}
	
	public void CustomerButtonOnAction(ActionEvent event) {
		createCustomer();
	}
	
	public void createCustomer() {
		try {
		Stage customerStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		customerStage.setScene(scene);
		customerStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		
	}
	
	}
}
