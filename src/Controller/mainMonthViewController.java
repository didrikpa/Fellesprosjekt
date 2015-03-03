package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Server.DatabaseServer;

public class mainMonthViewController {
	@FXML
    Pane mainMonthViewPane;
	@FXML
    GridPane gridPane;
	DatabaseServer dbSession;
	Stage stage;
	public mainMonthViewController(DatabaseServer server) throws Exception{
		dbSession = server;
		initScene();
		stage = (Stage) mainMonthViewPane.getScene().getWindow();		
	}
	void initScene() throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("monthViewPane.fxml"));
		fxmlLoader.setRoot(gridPane);
		fxmlLoader.setController(new MonthViewController(dbSession));
		fxmlLoader.load();
	}
}
