package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Server.DatabaseServer;

public class mainMonthViewController {
	DatabaseServer dbSession;
	Stage stage;
	public mainMonthViewController(DatabaseServer server) throws Exception{
		dbSession = server;
	}
}

//Er det mulig å ha et hovedview med flere panes som igjen inneholder views, men kjøres av samme kontroller?
