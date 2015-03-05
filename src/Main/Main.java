package Main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	FXMLLoader fxmlLoader;
	Parent root;
	public Stage ps;
    @Override
    public void start(Stage primaryStage) throws IOException {
        fxmlLoader = new FXMLLoader();
        root = (Parent) fxmlLoader.load(this.getClass().getResource("/Views/loginView.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
	public static void main(String[] args) {
		launch(args);
	}
}
