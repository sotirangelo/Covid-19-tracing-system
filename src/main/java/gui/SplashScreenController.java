package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {
    
    @FXML AnchorPane anchor;
      
    class Splash extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);
                Platform.runLater(() -> {
                    try {
    					Stage businessStage = new Stage();
    					businessStage.initModality(Modality.APPLICATION_MODAL);
    					Parent root = FXMLLoader.load(getClass().getResource("/fxml/Business.fxml"));
    					Scene scene = new Scene(root,552,339);	
    					scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
    					businessStage.setScene(scene);
    					businessStage.getIcons().add(new Image("/images/Javavirus Logo.png"));
    					businessStage.setTitle("Javavirus Covid19 Tracing App - Business Log In");
    					businessStage.show();
    				} catch(Exception e) {
    					e.printStackTrace();
    				}
                    anchor.getScene().getWindow().hide();
                });                
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Splash().start();
    }   
}