package gui;

import data.InfectedPerson;

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
	 private Button btn1Exit;
	 
	 

	 
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
		 	rows.clear();
			Stage tracingOutputStage = (Stage) btn1Exit.getScene().getWindow();
			((Node)event.getSource()).getScene().getWindow().hide();
			tracingOutputStage.close();
		}
	 
	 //private static ObservableList<RowPerson> data;
	 
	 private static ArrayList<RowPerson> rows = new ArrayList<RowPerson>();
	 
	 
	

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
			tableView.getItems().addAll(rows);
			//tableView.setItems(data);
			// TODO FIX NULL
			// tableInserts(null);
			
	}
	
	public static void addRows(ArrayList<InfectedPerson> infected) {
		System.out.println("Called addRows()");
		for (InfectedPerson i : infected) {
			System.out.println("Infected name: " + i.getFirstName());
			rows.add(new RowPerson(i.getFirstName(), i.getLastName(), i.getPhoneNumber(), i.getEmail(), i.getPropability()));
		}
		//tableView.getItems().addAll(rows);
	}
	
}
