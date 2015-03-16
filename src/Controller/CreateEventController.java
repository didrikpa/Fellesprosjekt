package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import Server.*;
import Model.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CreateEventController implements Initializable {
    @FXML
    AnchorPane createEventViewMainPane;
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
    CheckBox allDayCheck;
    @FXML
    ComboBox createEventViewGroup;
    @FXML
    private Label roomError;
    @FXML
    private Label startError;
    @FXML
    private Label endError;
    @FXML
    private Label dateError;

    @FXML
    Button inviteUserButton;
    @FXML
    RadioButton personalRadio;
    @FXML
    RadioButton meetingRadio;
    @FXML
    Label roomLabel;
    @FXML
    Label groupLabel;
    @FXML
    TextField notifyInt;
    @FXML
    ComboBox notifyCombo;
    PersonalAppointment personalAppointment = new PersonalAppointment();
    DatabaseServer databaseServer = new DatabaseServer();
    Alarm alarm;
    CalendarViewController parent;
    Stage stage;
    @FXML
    ListView<User> userList;
    @FXML
    ListView<User> participantList;
    ArrayList<User> selectedUsers = new ArrayList<User>();

    public CreateEventController(DatabaseServer server,
                                 CalendarViewController pt) {
        databaseServer = server;
        alarm = new Alarm(databaseServer);
        parent = pt;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDatePicker(createEventViewDatePicker);
        setHourFrom();
        setMinuteFrom();
        setHourToo();
        setMinuteToo();
        setRoom();
        appType();
//        setNotifyComboValues();
        setGroups();
    }

    @FXML
    public void setGroups(){
        try {
            ObservableList<String> groupList = FXCollections.observableArrayList(databaseServer.getGroupNames(databaseServer.Username));
            createEventViewGroup.setItems(groupList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createGroup(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/Views/createGroupView.fxml")));
            fxmlLoader.setController(new CreateGroupController(databaseServer));
            stage = new Stage();
            stage.setTitle("Create group");
            stage.setScene(new Scene((Parent) fxmlLoader.load()));
            stage.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // is true if it is a personal appointment and false it it is a meeting
    @FXML
    public boolean appType() {
        if (personalRadio.isSelected()) {
            createEventViewRoom.setVisible(false);
            roomLabel.setVisible(false);
            createEventViewGroup.setVisible(false);
            groupLabel.setVisible(false);
            createEventViewSearch.setDisable(true);
            return true;
        } else {
            createEventViewRoom.setVisible(true);
            roomLabel.setVisible(true);
            createEventViewGroup.setVisible(true);
            groupLabel.setVisible(true);
            createEventViewSearch.setDisable(false);
            return false;
        }
    }

    @FXML
    public void searchUser() throws Exception {
        userList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ArrayList<User> users = new ArrayList<User>();
        users = userSearch(createEventViewSearch.getText(),
                databaseServer.getUsers());
        userList.setItems(FXCollections.observableArrayList(users));
    }

    @FXML
    public void inviteUser(ActionEvent event) throws Exception {
        participantList.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);
        ObservableList<User> users = userList.getSelectionModel()
                .getSelectedItems();
        // Iterates through selected items and adds them to the selected user
        // list if not already there.
        for (int i = 0; i < users.size(); i++) {
            if (!selectedUsers.contains(users.get(i))) {
                selectedUsers.add(users.get(i));
            }
        }
        // Adds all the participants in the selected user list to the invited
        // user pan.
        participantList.setItems(FXCollections
                .observableArrayList(selectedUsers));
    }

    @FXML
    public void deleteInvitedUser(ActionEvent event) throws Exception {
        ObservableList<User> participants = participantList.getSelectionModel()
                .getSelectedItems();
        selectedUsers.removeAll(participants);
        participantList.setItems(FXCollections
                .observableArrayList(selectedUsers));
    }

    ArrayList<User> userSearch(String search, ArrayList<User> namesIn) {
        String searcher = search;
        searcher = searcher.toLowerCase();
        ArrayList<User> navnene = namesIn;
        ArrayList<User> namesOut = new ArrayList<User>();
        while (!searcher.equals("")) {
            for (User namn : navnene) {
                if ((namn.getFirstname() + "" + namn.getLastname())
                        .toLowerCase().contains(searcher)) {
                    if (!namesOut.contains(namn)) {
                        namesOut.add(namn);
                    }
                }
            }
            String[] con = searcher.split("");
            searcher = "";
            int ant = con.length - 1;
            for (int i = 0; i < ant; i++) {
                if (!(searcher.length() == ant) || searcher.length() < 3)
                    searcher += con[i];
            }
        }
        return namesOut;
    }

    @FXML
    public static void setDatePicker(final DatePicker calender) {
        calender.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
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
        for (int i = 0; i < 24; i++)
            someVariableName.add(i);
        ObservableList<Integer> hours = FXCollections
                .observableArrayList(someVariableName);
        createEventViewStartHours.setItems(hours);
        createEventViewStartHours.setValue(LocalTime.now().getHour() - 1);
    }

    @FXML
    public void setMinuteFrom() {
        List<Integer> someVariableName = new ArrayList();
        for (int i = 0; i < 60; i += 5)
            someVariableName.add(i);
        ObservableList<Integer> minutes = FXCollections
                .observableArrayList(someVariableName);
        createEventViewStartMinutes.setItems(minutes);
        createEventViewStartMinutes.setValue(5 * (Math.round((LocalTime.now()
                .getMinute() + 5) / 5)));
        createEventViewStartHours.setValue(LocalTime.now().getHour() + 1);
    }

    @FXML
    public void setNotifyComboValues() {
        List<String> list = new ArrayList<String>();
        list.add("minutes");
        list.add("hours");
        list.add("days");
        ObservableList<String> choices = FXCollections
                .observableArrayList(list);
        notifyCombo.setItems(choices);
        notifyCombo.setValue(choices.get(0));
    }

    @FXML
    public void setHourToo() {
        DecimalFormat formatter = new DecimalFormat("00");
        int n = createEventViewStartHours.getValue();
        List<Integer> hours = new ArrayList();
        for (int i = n; i < 24; i++)
            hours.add(i);
        ObservableList<Integer> availableHours = FXCollections
                .observableArrayList(hours);
        createEventViewEndHours.setItems(availableHours);
        createEventViewEndHours.setValue(n + 1);
    }

    @FXML
    public void setMinuteToo() {
        int j = createEventViewStartMinutes.getValue();
        List<Integer> someVariable = new ArrayList();
        for (int i = 0; i < 60; i += 5)
            someVariable.add(i);
        ObservableList<Integer> availableMinutes = FXCollections
                .observableArrayList(someVariable);
        createEventViewEndMinutes.setItems(availableMinutes);
        createEventViewEndMinutes.setValue(j);
    }

    @FXML
    public void setRoom() {
        try {
            ObservableList<String> roomlist = FXCollections
                    .observableArrayList(databaseServer.getRoomName());
            roomlist.remove("PersonalRoom");
            createEventViewRoom.setItems(roomlist);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void setDate() {
        personalAppointment.setDato(java.sql.Date
                .valueOf(createEventViewDatePicker.getValue()));
    }

    private void setDato() {
        personalAppointment.setDato(java.sql.Date
                .valueOf(createEventViewDatePicker.getValue()));
    }

    @FXML
    public boolean validateTime() {
        if (createEventViewEndHours.getValue() == createEventViewStartHours
                .getValue()
                && createEventViewEndMinutes.getValue() < createEventViewStartMinutes
                .getValue()) {
            createEventViewEndMinutes.setStyle("-fx-background-color: red");
            endError.setStyle("-fx-text-fill: red");
            endError.setText("End time cannot be set before start time.");
            endError.setVisible(true);
            return false;
        } else if (createEventViewStartHours.getValue() == createEventViewEndHours
                .getValue()
                && createEventViewStartMinutes.getValue() == createEventViewEndMinutes
                .getValue()) {
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
            Time start = new Time(createEventViewStartHours.getValue(),
                    createEventViewStartMinutes.getValue(), 00);
            Time end = new Time(createEventViewEndHours.getValue(),
                    createEventViewEndMinutes.getValue(), 00);
            personalAppointment.setStartTid(start);
            personalAppointment.setSluttTid(end);
            return true;
        }
    }

    @FXML
    public void lastsAllDay() {
        if (allDayCheck.isSelected()) {
            createEventViewStartHours.setValue(0);
            createEventViewStartMinutes.setValue(0);
            createEventViewEndHours.setValue(23);
            createEventViewEndMinutes.setValue(59);
            createEventViewEndMinutes.setDisable(true);
            createEventViewEndHours.setDisable(true);
            createEventViewStartMinutes.setDisable(true);
            createEventViewStartHours.setDisable(true);
        } else {
            createEventViewEndMinutes.setDisable(false);
            createEventViewEndHours.setDisable(false);
            createEventViewStartMinutes.setDisable(false);
            createEventViewStartHours.setDisable(false);
        }
    }

    public boolean validateNotification() {
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
        try {
            int not = Integer.parseInt(notifyInt.getText());
            if(not == 0){
                alarm.setTidspunkt(null);
                return true;
            }
            if(((String)notifyCombo.getValue()).equalsIgnoreCase("minutes")){
                ts.setMinutes(ts.getMinutes()+not);
                alarm.setTidspunkt(ts);
                return true;
            }
            if(((String)notifyCombo.getValue()).equalsIgnoreCase("hours")){
                ts.setHours(ts.getHours()+not);
                alarm.setTidspunkt(ts);
                return true;
            }
            if(((String)notifyCombo.getValue()).equalsIgnoreCase("days")){
                ts.setDate(ts.getDay()+not);
                alarm.setTidspunkt(ts);
                return true;
            }
        } catch (Exception e) {
            alarm.setTidspunkt(null);
            return true;
        }
        return false;
    }

    @FXML
    public boolean validateDescription() {
        if (createEventViewTextArea.getText().trim().length() == 0) {
            createEventViewTextArea
                    .setText("There has to be a description of the event.");
            return false;
        } else if (createEventViewTextArea.getText().equals(
                "There has to be a description of the event.")) {
            return false;
        } else {
            personalAppointment.setBeskrivelse(createEventViewTextArea
                    .getText());
            return true;
        }
    }

    @FXML
    public boolean validateGroupSelection(){
        if(createEventViewGroup.getValue().equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    @FXML
    public boolean validateRoom() throws Exception {
        if(createEventViewRoom.getValue() == null) {
            roomError.setStyle("-fx-text-fill: red");
            roomError.setText("There has to be a room to the event.");
            roomError.setVisible(true);
            return false;
        }
        personalAppointment.setRomnavn(createEventViewRoom.getValue());
        if(!validateTime()){
            roomError.setStyle("-fx-text-fill: red");
            roomError.setText("Room can not be set before time.");
            roomError.setVisible(true);
            return false;
        }
        if(databaseServer.roomIsTaken(personalAppointment)){
            roomError.setStyle("-fx-text-fill: red");
            roomError.setText("The room is taken on the given time.");
            roomError.setVisible(true);
            return false;
        }else {
            System.out.println("Funker ikke");
            roomError.setVisible(false);
            if (!appType())
                personalAppointment.setRomnavn(createEventViewRoom.getValue());
            return true;
        }
    }

    @FXML
    public void createEvent(ActionEvent event) throws Exception {
        setDato();
        if (!appType()) {
            if (validateTime() && validateRoom() && validateDescription() && validateNotification()) {
                try {
                    databaseServer.addAppointment(personalAppointment, selectedUsers);
                    alarm.setBrukernavn(databaseServer.Username);
                    alarm.setAvtaleID(databaseServer.getLastAppointment().getAvtaleID());
                    if(alarm.getTidspunkt() != null)databaseServer.setAlarm(alarm);
                    parent.monthB();
                    parent.monthF();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    sendInvitationEmail();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (appType()) {
            if (validateTime() && validateDescription() && validateNotification()) {
                personalAppointment.setRomnavn("PersonalRoom");
                try {
                    databaseServer.addAppointment(personalAppointment, null);
                    alarm.setBrukernavn(databaseServer.Username);
                    alarm.setAvtaleID(databaseServer.getLastAppointment().getAvtaleID());
                    if(alarm.getTidspunkt() != null)databaseServer.setAlarm(alarm);
                    parent.monthB();
                    parent.monthF();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void sendInvitationEmail() {
        try {
            for (int i = 0; i < selectedUsers.size(); i++) {
                // Sender's email ID needs to be mentioned
                String from = "awesome@calendar.com";

                // Assuming you are sending email from localhost
                String host = "smtp.stud.ntnu.no";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);

                // Get the default Session object.
                Session session = Session.getDefaultInstance(properties);

                try {
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(
                            Message.RecipientType.TO,
                            new InternetAddress(selectedUsers.get(i).getEmail()));

                    // Set Subject: header field
                    message.setSubject("Invitation to meeting");

                    // Now set the actual message
                    try {
                        message.setText("Your have been invited to join "
                                + databaseServer.getUser()
                                + "s meeting, which will take place at "
                                + createEventViewRoom.getValue()
                                + ", "
                                + createEventViewStartHours.getValue()
                                + ":"
                                + createEventViewStartMinutes.getValue()
                                + ". Please check your calendar to accept or decline this invitation."
                                + "\nMeeting description: \n"
                                + createEventViewTextArea.getText());

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    // Send message
                    Transport.send(message);
                    System.out.println("Sent message successfully to "
                            + selectedUsers.get(i).getEmail());

                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void cancelEvent(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}

