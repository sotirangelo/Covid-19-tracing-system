package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;




public class Main extends Application {
	
	@Override
	public void start(Stage splashScreenStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/SplashScreen.fxml"));
			Scene scene = new Scene(root,200,200);
			splashScreenStage.initStyle(StageStyle.UNDECORATED);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			splashScreenStage.setScene(scene);
			splashScreenStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
