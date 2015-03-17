package Controller;

import Model.User;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public interface EventController {
	public void setGroups();
	ListView<User>participantList = new ListView<User>();
	ComboBox<String> createEventViewGroup = new ComboBox<String>();
}
