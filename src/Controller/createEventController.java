package Controller;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.time.LocalDate;

public class createEventController {

    @FXML
    DatePicker createEventViewDatePicker;
    @FXML
    ComboBox createEventViewHours;
    @FXML
    ComboBox createEventViewMinutes;
    @FXML
    ComboBox createEventViewRoom;
    @FXML
    TextField createEventViewSearch;

    @FXML
    public static void setDatePicker(final DatePicker calender) {
        calender.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {

                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(LocalDate.now())) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: pink;");
                                }

                            }
                        };
                    }
                };

        calender.setDayCellFactory(dayCellFactory);

    }




}
