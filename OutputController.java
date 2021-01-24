package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OutputController {

	/* Labels */
	
	 @FXML
	 private Label lbl1Title;
	 
	 @FXML
	 private Label lbl2Output;
	 
	 
	/* Table View */
	 
	 @FXML
	 private TableView <InfectedPerson> tableView;
	 
	 @FXML
	 private TableColumn <InfectedPerson, String> nameColumn;
	 
	 @FXML
	 private TableColumn <InfectedPerson, Integer> ageColumn;
	 
	 @FXML
	 private TableColumn <InfectedPerson, String> emailColumn;
	 
	 @FXML
	 private TableColumn <InfectedPerson, String> scoreColumn;
	 
	 

		
	public ObservableList<InfectedPerson> infectedPeople = FXCollections.observableArrayList()
		return infectedPeople;
		}
	 
	public void initializeListView() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<InfectedPerson, String>("Name"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<InfectedPerson, Integer>("Age"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<InfectedPerson, String>("E-mail"));
		scoreColumn.setCellValueFactory(new PropertyValueFactory<InfectedPerson, String>("Score"));
		tableView.setItems(infectedPeople);
		 }
	 
}