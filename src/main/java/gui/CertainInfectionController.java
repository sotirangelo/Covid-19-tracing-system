package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Person;
import data.DataAnalysis;
import data.InfectedPerson;
import javafx.application.Platform;
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
			lbl3Status.setTextFill(Color.GREEN);
	        lbl3Status.setText("Successful Covid Case Registration");
			String infectedid = txt1CustomerID.getText();
			Person p = database.Access.findUser(infectedid);
			if (p != null) {
				InfectedPerson case0 = new InfectedPerson(p, 100);
				database.Access.addInfected(case0);
				ArrayList<InfectedPerson> list = DataAnalysis.contactTracing(case0);
				System.out.println("Stopped contact tracing");
				OutputController.addRows(list);
				createTracingOutput();
			} else {
	        	lbl3Status.setTextFill(Color.RED);
	        	lbl3Status.setText("User not found");
	        	return;
			}
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
