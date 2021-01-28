package gui;
	
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;




public class Main extends Application {
	
	@Override
	public void start(Stage businessStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/Business.fxml"));
			Scene scene = new Scene(root,552,339);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			businessStage.setScene(scene);
			BufferedImage icon = ImageIO.read(getClass().getResourceAsStream("/images/Javavirus Logo.png"));
			businessStage.getIcons().add(SwingFXUtils.toFXImage(icon, null));
			businessStage.setTitle("Javavirus� Covid19 Tracing App - Business Log In");
			businessStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
