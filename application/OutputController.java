package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OutputController extends Thread implements Initializable{
	

Scanner scan = new Scanner(System.in);
	
	 @FXML
	 private Label lbl1Title;
	 
	 @FXML
	 private Label lbl2Output;
	 
	 
	 @FXML
	 private Button btn3Exit;
	 
	 

	 
	 @FXML
	 private TableView <RowPerson> tableView;
	 
	 @FXML
	 private TableColumn<RowPerson, String> columnFirstName ;
	 
	 @FXML
	 private TableColumn<RowPerson, String> columnLastName ;

	 @FXML
	 private TableColumn<RowPerson, String> columnPhoneNum;
	 
	 @FXML
	 private TableColumn<RowPerson, String> columnEmail;
	 
	 @FXML
	 private TableColumn<RowPerson, String> columnProbability;
	 
	 
	 public void exitButtonOnAction(ActionEvent event) {
			Stage OutputStage = (Stage) btn3Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			OutputStage.close();
		}
	 
	 private final ObservableList<RowPerson> data = FXCollections.observableArrayList();
	 
	 private ArrayList<RowPerson> rows = new ArrayList<RowPerson>();
	 
	 
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			columnFirstName.setCellValueFactory(
			    new PropertyValueFactory<RowPerson,String>("FirstName"));
			columnLastName.setCellValueFactory(
				    new PropertyValueFactory<RowPerson,String>("LastName"));
			columnPhoneNum.setCellValueFactory(
			    new PropertyValueFactory<RowPerson,String>("PhoneNumber"));
			columnEmail.setCellValueFactory(
			    new PropertyValueFactory<RowPerson,String>("Email"));
			columnProbability.setCellValueFactory(
				new PropertyValueFactory<RowPerson,String>("Probability"));
			tableView.setItems(data);
			// TODO FIX NULL
			// tableInserts(null);
			
	}
	
	public void tableInserts(ArrayList<InfectedPerson> infected) {
		ArrayList<RowPerson> rows = new ArrayList<RowPerson>();
		for (InfectedPerson i : infected) {
			rows.add(new RowPerson(i.getFirstName(), i.getLastName(), i.getPhoneNumber(), i.getEmail(), i.getPropability()));
		}
		tableView.getItems().addAll(rows);
	}
	
}
