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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
	
	
	
	public boolean validateCaseID() {
		Pattern CaseIDpattern = Pattern.compile("^[0-9]{8}$");
		Matcher CaseIDmat;
			do {
				CaseIDmat = CaseIDpattern.matcher(txt1CustomerID.getText());
	        if(CaseIDmat.matches()){
	            lbl3Status.setTextFill(Color.GREEN);
	            lbl3Status.setText("Submission Successful");
	            createTracingOutput();
	            return true;
	        } else {
	        	lbl3Status.setTextFill(Color.RED);
	        	lbl3Status.setText("Submission Failed");
		         return false;
	        }
		} while(!CaseIDmat.matches());
	
	}
	
	
	public void exitButtonOnAction(ActionEvent event) {
		Stage certainInfectionStage = (Stage) btn2Exit.getScene().getWindow();
		((Node)event.getSource()).getScene().getWindow().hide();
		certainInfectionStage.close();
	}
	
	
	public void sumbitOnAction(ActionEvent event) {
		lbl3Status.setText("Submission Status");
		if (validateCaseID()) {
			((Node)event.getSource()).getScene().getWindow().hide();
		}
	}
	
	public void createTracingOutput() {
		try {
		Stage tracingOutputStage = new Stage();
		tracingOutputStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/TracingOutput.fxml"));
		Scene scene = new Scene(root,699,472);
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		tracingOutputStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
		tracingOutputStage.setTitle("Javavirus Covid19 Tracing App - Contact Tracing Results");
		tracingOutputStage.setScene(scene);
		tracingOutputStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
