package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;




public class Main extends Application {
	
	@Override
	public void start(Stage businessStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Business.fxml"));
			Scene scene = new Scene(root,552,339);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			businessStage.setScene(scene);
			businessStage.getIcons().add(new Image("/application/Javavirus GUI images/Javavirus Logo.png"));
			businessStage.setTitle("Javavirus® Covid19 Tracing App - Business Log In");
			businessStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
