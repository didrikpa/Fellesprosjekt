package Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Server.*;
import Model.*;

public class CreateEventController implements Initializable {

    PersonalAppointment personalAppointment = new PersonalAppointment();
    DatabaseServer databaseServer = new DatabaseServer();

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
    @FXML private Label roomError;
    @FXML private Label startError;
    @FXML private Label endError;
    @FXML private Label dateError;


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
            endError.setStyle("-fx-text-fill: red");
            endError.setText("End time cannot be set before start time.");
            endError.setVisible(true);
            return false;
        } else if (createEventViewStartHours.getValue() == createEventViewEndHours.getValue() && createEventViewStartMinutes.getValue() == createEventViewEndMinutes.getValue()) {
            createEventViewEndMinutes.setStyle("-fx-background-color: red");
            createEventViewEndHours.setStyle("-fx-background-color:  red");
            endError.setStyle("-fx-text-fill: red");
            endError.setText("End time and start time cannot be equal.");
            endError.setVisible(true);
            return false;
        } else {
            createEventViewEndMinutes.setStyle(" ");
            createEventViewEndHours.setStyle(" ");
            endError.setVisible(false);
            int second = 0;
            personalAppointment.setStartTid(java.sql.Time.valueOf(LocalTime.of(createEventViewStartHours.getValue(), createEventViewStartMinutes.getValue(), second)));
            personalAppointment.setSluttTid(java.sql.Time.valueOf(LocalTime.of(createEventViewEndHours.getValue(), createEventViewEndMinutes.getValue(), second)));
            return true;
        }
    }

    @FXML
    public void setRoom(){
        try{
            ObservableList<String> roomlist = FXCollections.observableArrayList(databaseServer.getRoomName());
            createEventViewRoom.setItems(roomlist);
        }
        catch (Exception e) {System.out.println(e);}
    }

    @FXML
    public void setDate(){
        personalAppointment.setDato(java.sql.Date.valueOf(createEventViewDatePicker.getValue()));

    }

    @FXML
    public boolean validateDescription(){
        if(createEventViewTextArea.getText().trim().length() == 0){
            createEventViewTextArea.setStyle("-fx-text-fill: red");
            createEventViewTextArea.setText("There has to be a description of the event.");
            return false;
        }
        else {
            personalAppointment.setBeskrivelse(createEventViewTextArea.getText());
            return true;
        }
    }

    @FXML
    public boolean validateRoom(){
        if (createEventViewRoom.getValue() == null){
            roomError.setStyle("-fx-text-fill: red");
            roomError.setText("There has to be a room to the event.");
            roomError.setVisible(true);
            return false;
        }
        else {
            roomError.setVisible(false);
            personalAppointment.setRomnavn(createEventViewRoom.getValue());
            return true;
        }
    }

    @FXML
    public void createEvent(){
        if(validateTime() && validateRoom() && validateDescription()){
            try {
                databaseServer.addAppointment(personalAppointment);
            }
            catch (Exception e) { System.out.println(e);}
        }

    }

    @FXML


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatePicker(createEventViewDatePicker);
        setHourFrom();
        setMinuteFrom();
        setHourToo();
        setMinuteToo();
        setRoom();
    }

}