package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;




public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Launcher.fxml"));
			Scene scene = new Scene(root,430,320);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Javavirus Covid Tracing App");
			primaryStage.setFullScreen(true);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
