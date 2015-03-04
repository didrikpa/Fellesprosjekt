package Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.*;
import Model.*;

public class createEventController implements Initializable {

    PersonalAppointment personalAppointment;

    @FXML
    DatePicker createEventViewDatePicker;
    @FXML
    ComboBox<Integer> createEventViewStartHours;
    @FXML
    ComboBox<Integer> createEventViewStartMinutes;
    @FXML
    ComboBox<Integer> createEventViewEndHours;
    @FXML
    ComboBox<Integer> createEventViewEndMinutes;
    @FXML
    ComboBox<String> createEventViewRoom;
    @FXML
    TextField createEventViewSearch;
    @FXML
    TextArea createEventViewTextArea;

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


    @FXML
    public void setHourFrom() {
        List<Integer> someVariableName = new ArrayList();
        for (int i = 0; i < 24; i++) someVariableName.add(i);
        ObservableList<Integer> hours = FXCollections.observableArrayList(someVariableName);
        createEventViewStartHours.setItems(hours);
        createEventViewStartHours.setValue(LocalTime.now().getHour());
    }

    @FXML
    public void setMinuteFrom() {
        List<Integer> someVariableName = new ArrayList();
        for (int i=0; i<60; i+=5) someVariableName.add(i);
        ObservableList<Integer> minutes = FXCollections.observableArrayList(someVariableName);
        createEventViewStartMinutes.setItems(minutes);
        createEventViewStartMinutes.setValue(5 * (Math.round((LocalTime.now().getMinute() + 5) / 5)));
        createEventViewStartHours.setValue(LocalTime.now().getHour() + 1);
    }


    @FXML
    public void setHourToo(){
        DecimalFormat formatter = new DecimalFormat("00");
        int n = createEventViewStartHours.getValue();
        List<Integer> hours = new ArrayList();
        for (int i=n; i<24; i++) hours.add(i);
        ObservableList<Integer> availableHours = FXCollections.observableArrayList(hours);
        createEventViewEndHours.setItems(availableHours);
        createEventViewEndHours.setValue(n + 1);
    }

    @FXML
    public void setMinuteToo() {
        int j = createEventViewStartMinutes.getValue();
        List<Integer> someVariable = new ArrayList();
        for (int i=0; i<60; i+=5) someVariable.add(i);
        ObservableList<Integer> availableMinutes = FXCollections.observableArrayList(someVariable);
        createEventViewEndMinutes.setItems(availableMinutes);
        createEventViewEndMinutes.setValue(j);
    }

    @FXML
    public boolean validateTime(){
        if (createEventViewEndHours.getValue() == createEventViewStartHours.getValue() && createEventViewEndMinutes.getValue() < createEventViewStartMinutes.getValue()) {
            createEventViewEndMinutes.setStyle("-fx-background-color: red");
            //timeError.setText("Insert error");
            //timeError.setVisible(true);
            return false;
        } else if (createEventViewStartHours.getValue() == createEventViewEndHours.getValue() && createEventViewStartMinutes.getValue() == createEventViewEndMinutes.getValue()) {
            createEventViewEndMinutes.setStyle("-fx-background-color: red");
            createEventViewEndHours.setStyle("-fx-background-color:  red");
            //timeError.setVisible(true);
            //timeError.setText("Insert error");
            return false;
        } else {
            createEventViewEndMinutes.setStyle(" ");
            createEventViewEndHours.setStyle(" ");
            //timeError.setVisible(false);
            return true;
        }
    }

    @FXML
    public void setDate(){
        personalAppointment.setDato(createEventViewDatePicker.getValue());
    }

    @FXML
    public boolean validateTextArea(){
        if(createEventViewTextArea.getText().trim().length() == 0){
            //textError.setVisible(true);
            return false;
        }
        else {
            personalAppointment.setBeskrivelse(createEventViewTextArea.getText());
            //textError.setVisible(false);
            return true;
        }
    }

    @FXML
    public boolean validateRoom(){
        if (createEventViewRoom.getValue() == null){
            //roomError.setVisible(true);
            return false;
        }
        else {
            //roomError.setVisible(false);
            return true;
        }
    }

    @FXML
    public void createEvent(){
        DatabaseServer databaseServer = new DatabaseServer();
        if(validateTime() && validateRoom()){

        }

    }

    @FXML


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatePicker(createEventViewDatePicker);
        setHourFrom();
        setMinuteFrom();
        setHourToo();
        setMinuteToo();
    }

}
