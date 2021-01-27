package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CertainInfectionController {
	
	/* Label */
	@FXML
	private Label lbl1Title;
	
	@FXML
	private Label lbl2CertainInfection;
	
	@FXML
	private Label lbl3Status;
	
	@FXML
	private Label lbl4CustomerID;
	
	
	/* Button */
	@FXML
	private Button btn1Sumbit;
	
	@FXML
	private Button btn2Exit;
	
	/* Text Fields */
	@FXML
	private TextField txt1CustomerID;
	
	
	public void exitButtonOnAction(ActionEvent event) {
		Stage certainInfectionStage = (Stage) btn2Exit.getScene().getWindow();
		((Node)event.getSource()).getScene().getWindow().hide();
		certainInfectionStage.close();
	}
	
	
	public void sumbitOnAction(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
		createTracingOutput();
	}
	
	public void createTracingOutput() {
		try {
		Stage tracingOutputStage = new Stage();
		tracingOutputStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/application/TracingOutput.fxml"));
		Scene scene = new Scene(root,669,472);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		tracingOutputStage.getIcons().add(new Image("/application/Javavirus GUI images/Javavirus Logo.png"));
		tracingOutputStage.setTitle("Javavirus® Covid19 Tracing App - Customer Edit Account (Validation)");
		tracingOutputStage.setScene(scene);
		tracingOutputStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
