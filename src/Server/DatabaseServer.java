package Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Model.Alarm;
import Model.Group;
import Model.Invite;
import Model.Overlap;
import Model.PersonalAppointment;
import Model.User;

public class DatabaseServer {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/simonssl_fpgp_fp";
    static final String USER = "simonssl_fpgp";
    static final String PASS = "Vierbest";
    public String Username;
    private String Password;
    Connection conn;
    Statement stmt;

    public DatabaseServer() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Connection failed:" + e.getMessage());
        }
    }

    public boolean login(String B, String P) throws SQLException {
        if (userExist(B)) {
            String values = "";
            String sql = "SELECT Brukernavn, Passord FROM Bruker WHERE Brukernavn = '" + B + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                values += (rs.getString("Brukernavn"));
                values += ("-" + rs.getString("Passord"));
            }
            String[] sit = values.split("-");
            if (sit[0].equals(B) && sit[1].equals(P)) {
                Username = sit[0];
                Password = sit[1];
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public User getUser() throws Exception {
        User user = new User();
        String sql = "SELECT * FROM Bruker WHERE Brukernavn = '" + this.Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            user.setUsername(rs.getString("Brukernavn"));
            user.setPassword(rs.getString("Passord"));
            user.setFirstname(rs.getString("Fornavn"));
            user.setLastname(rs.getString("Etternavn"));
            user.setEmail(rs.getString("E-post"));
            user.setPhone(rs.getString("Telefon"));
        }
        return user;
    }

    public User getUser(String usernamen) throws Exception {
        User user = new User();
        String sql = "SELECT * FROM Bruker WHERE Brukernavn = '" + usernamen + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            user.setUsername(rs.getString("Brukernavn"));
            user.setPassword(rs.getString("Passord"));
            user.setFirstname(rs.getString("Fornavn"));
            user.setLastname(rs.getString("Etternavn"));
            user.setEmail(rs.getString("E-post"));
            user.setPhone(rs.getString("Telefon"));
        }
        return user;
    }

    public ArrayList<User> getUsers() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM Bruker;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("Brukernavn"));
            user.setPassword(rs.getString("Passord"));
            user.setFirstname(rs.getString("Fornavn"));
            user.setLastname(rs.getString("Etternavn"));
            user.setEmail(rs.getString("E-post"));
            user.setPhone(rs.getString("Telefon"));
            users.add(user);
        }
        return users;
    }

    public boolean addUser(User user) throws SQLException {
        if (!userExist(user.getUsername())) {
            String sql = "INSERT INTO Bruker VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFirstname() + "', '" + user.getLastname() + "', '" + user.getEmail() + "', '" + user.getPhone() + "');";
            stmt.executeUpdate(sql);
            return true;
        }
        return false;
    }

    public void editUser(User user) throws Exception {
        if (user.getUsername().equalsIgnoreCase(Username)) {
            String sql = "UPDATE Bruker SET Telefon ='" + user.getPhone() + "', Passord ='" + user.getPassword() + "' WHERE Brukernavn ='" + this.Username + "';";
            stmt.executeUpdate(sql);
            sql = "UPDATE `Bruker` SET `E-post` = '" + user.getEmail() + "' WHERE `Bruker`.`Brukernavn` = '" + this.Username + "';";
            stmt.executeUpdate(sql);
        }
    }

    public boolean userExist(String username) throws SQLException {
        String values = "";
        String sql = "SELECT Brukernavn, Passord FROM Bruker WHERE Brukernavn = '" + username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            values += (rs.getString("Brukernavn"));
            values += ("-" + rs.getString("Passord"));
        }
        values.replaceAll(" ", "");
        if (values.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean emailExist(String email) throws SQLException {
        String values = "";
        String sql = "SELECT `E-post` FROM Bruker WHERE `E-post` = '" + email + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            values += (rs.getString("E-post"));
        }
        values.replaceAll(" ", "");
        if (values.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String getPassword(String email) throws Exception {
        String password;
        password = this.Password;
        String sql = "SELECT Passord FROM Bruker WHERE `E-post` = '" + email + "' ;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            password = rs.getString("Passord");
        }
        return password;
    }

    public PersonalAppointment getSpecificAppointment(int avtaleid) throws Exception {
        PersonalAppointment pa = new PersonalAppointment();
        String sql = "SELECT * FROM Avtale WHERE AvtaleID ='" + avtaleid + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            pa.setAvtaleID(avtaleid);
            pa.setBeskrivelse(rs.getString("Beskrivelse"));
            pa.setOpprettetAv(rs.getString("Brukernavn"));
            pa.setDato(rs.getDate("Dato"));
            pa.setRomnavn(rs.getString("Romnavn"));
            pa.setStartTid(rs.getTime("Starttid"));
            pa.setSluttTid(rs.getTime("Slutttid"));
            pa.setGruppeID(rs.getInt("GruppeID"));
        }
        return pa;
    }

    public ArrayList<PersonalAppointment> getAppointment(Date date) throws Exception {
        String sql = "SELECT * FROM Avtale, Bruker WHERE Bruker.Brukernavn = '" + Username + "' AND Avtale.Dato ='" + date.toString() + "' AND Bruker.Brukernavn = Avtale.Brukernavn ORDER BY Starttid;";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
        while (rs.next()) {
            PersonalAppointment appointment = new PersonalAppointment();
            appointment.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
            appointment.setDato(Date.valueOf(rs.getString("Dato")));
            appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
            appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
            appointment.setBeskrivelse(rs.getString("Beskrivelse"));
            appointment.setRomnavn(rs.getString("Romnavn"));
            appointment.setOpprettetAv(rs.getString("Brukernavn"));
            appointment.setGruppeID(rs.getInt("GruppeID"));
            appointments.add(appointment);
        }
        return appointments;
    }

    public ArrayList<PersonalAppointment> getAppointment(Date date, Group group) throws Exception {
        ArrayList<PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
        for (User user : group.getUsers()) {
            if (!user.getUsername().equals(Username)) {
                String sql = "SELECT * FROM Avtale, Bruker WHERE Bruker.Brukernavn = '" + user.getUsername() + "' AND Avtale.Dato ='" + date.toString() + "' AND Bruker.Brukernavn = Avtale.Brukernavn ORDER BY Starttid;";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PersonalAppointment appointment = new PersonalAppointment();
                    appointment.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
                    appointment.setDato(Date.valueOf(rs.getString("Dato")));
                    appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
                    appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
                    appointment.setBeskrivelse(rs.getString("Beskrivelse"));
                    appointment.setRomnavn(rs.getString("Romnavn"));
                    appointment.setOpprettetAv(rs.getString("Brukernavn"));
                    appointment.setGruppeID(rs.getInt("GruppeID"));
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }

    public ArrayList<PersonalAppointment> comingUp(int n) throws Exception {
        if (n > 0) {
            String sql = "SELECT * FROM Avtale WHERE Dato >= NOW() AND Brukernavn = '" + Username + "' ORDER BY Dato ASC, Starttid LIMIT " + n + ";";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
            while (rs.next()) {
                PersonalAppointment appointment = new PersonalAppointment();
                appointment.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
                appointment.setDato(Date.valueOf(rs.getString("Dato")));
                appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
                appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
                appointment.setBeskrivelse(rs.getString("Beskrivelse"));
                appointment.setRomnavn(rs.getString("Romnavn"));
                appointment.setOpprettetAv(rs.getString("Brukernavn"));
                appointment.setGruppeID(rs.getInt("GruppeID"));
                appointments.add(appointment);
            }
            return appointments;
        }
        return null;
    }

    public PersonalAppointment getLastAppointment() throws Exception {
        String sql = "SELECT * FROM Avtale WHERE Brukernavn = '" + this.Username + "' ORDER BY AvtaleID DESC LIMIT 1;";
        PersonalAppointment pa = new PersonalAppointment();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            pa.setAvtaleID(rs.getInt("AvtaleID"));
            pa.setBeskrivelse(rs.getString("Beskrivelse"));
            pa.setOpprettetAv(rs.getString("Brukernavn"));
            pa.setDato(rs.getDate("Dato"));
            pa.setRomnavn(rs.getString("Romnavn"));
            pa.setStartTid(rs.getTime("Starttid"));
            pa.setSluttTid(rs.getTime("Slutttid"));
            pa.setGruppeID(rs.getInt("GruppeID"));
        }
        return pa;
    }

    public void editAppointment(PersonalAppointment pa, int gpid) throws Exception {
        String sql = "UPDATE Avtale SET `Dato` = '" + pa.getDato() + "', `Starttid` = '" + pa.getStartTid() + "',`Slutttid` = '" + pa.getSluttTid() + "',`Beskrivelse` = '" + pa.getBeskrivelse() + "',`Romnavn` = '" + pa.getRomnavn() + "', GruppeID = '" + pa.getGruppeID() + "' WHERE `Avtale`.`AvtaleID` = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
        ArrayList<User> gm = this.getGroupMembers(gpid);
        if (gm != null) {
            for (int i = 0; i < gm.size(); i++) {
                if (gm.get(i).getUsername().equalsIgnoreCase(Username)) {
                    gm.remove(i);
                }
            }
            for (User user : gm) {
                sql = "INSERT INTO `simonssl_fpgp_fp`.`Invitasjon` (`InvitasjonID`, `Brukernavn`, `AvtaleID`, `Godtatt`) VALUES (NULL, '" + user.getUsername() + "','" + pa.getAvtaleID() + "', NULL);";
                stmt.executeUpdate(sql);
            }
        }

    }


    public void addAppointment(PersonalAppointment appointment, int group) throws Exception {
        if (group != 0) {
            String sql = "INSERT INTO Avtale VALUES ( NULL,'" + appointment.getDato().toString() + "', '" + appointment.getStartTid().toString() + "', '" + appointment.getSluttTid().toString() + "', '" + appointment.getBeskrivelse() + "', '" + appointment.getRomnavn() + "', '" + Username + "'," + group + ");";
            stmt.executeUpdate(sql);
            sql = "SELECT * FROM Avtale WHERE Brukernavn = '" + Username + "' ORDER BY AvtaleID DESC LIMIT 1;";
            ResultSet rs = stmt.executeQuery(sql);
            PersonalAppointment pa = new PersonalAppointment();
            while (rs.next()) {
                pa.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
            }
            ArrayList<User> gm = this.getGroupMembers(group);
            if (gm != null) {
                for (int i = 0; i < gm.size(); i++) {
                    if (gm.get(i).getUsername().equalsIgnoreCase(Username)) {
                        gm.remove(i);
                    }
                }
                for (User user : gm) {
                    sql = "INSERT INTO `simonssl_fpgp_fp`.`Invitasjon` (`InvitasjonID`, `Brukernavn`, `AvtaleID`, `Godtatt`) VALUES (NULL, '" + user.getUsername() + "','" + pa.getAvtaleID() + "', NULL);";
                    stmt.executeUpdate(sql);
                }
            }
        } else {
            String sql = "INSERT INTO Avtale VALUES ( NULL,'" + appointment.getDato().toString() + "', '" + appointment.getStartTid().toString() + "', '" + appointment.getSluttTid().toString() + "', '" + appointment.getBeskrivelse() + "', '" + appointment.getRomnavn() + "', '" + Username + "'," + null + ");";
            stmt.executeUpdate(sql);
        }
    }


    public int createGroup(String Groupname, ArrayList<User> members) throws Exception {
        int gpid = 0;
        String sql = "INSERT INTO Gruppe VALUES(NULL,'" + Groupname + "', NULL, '" + Username + "');";
        stmt.executeUpdate(sql);
        sql = "SELECT * FROM Gruppe WHERE Gruppenavn = '" + Groupname + "' ORDER BY GruppeID DESC LIMIT 1;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            gpid = rs.getInt("GruppeID");
        }
        for (User user : members) {
            sql = "INSERT INTO Gruppemedlem VALUES('" + gpid + "', '" + user.getUsername() + "');";
            stmt.executeUpdate(sql);
        }
        return gpid;
    }

    public ArrayList<String> getAllGroups() throws SQLException {
        ArrayList<String> groupNames = new ArrayList<String>();
        String sql = "SELECT Gruppenavn FROM Gruppe;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            groupNames.add(rs.getString("Gruppenavn"));
        }
        return groupNames;

    }

    public void removeAppointment(PersonalAppointment pa) throws Exception {
        ArrayList<PersonalAppointment> childs = appointmentChilds(pa);
        for (PersonalAppointment pas : childs) {
            String sql = "DELETE FROM Underavtale WHERE OpphavsavtaleID = " + pas.getAvtaleID() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Underavtale WHERE UnderavtaleID = " + pas.getAvtaleID() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Alarm WHERE AvtaleID = " + pas.getAvtaleID() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Invitasjon WHERE AvtaleID = " + pas.getAvtaleID() + ";";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM Avtale WHERE AvtaleID = " + pas.getAvtaleID() + ";";
            stmt.executeUpdate(sql);
        }
        String sql = "DELETE FROM Underavtale WHERE OpphavsavtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Underavtale WHERE UnderavtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Alarm WHERE AvtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Invitasjon WHERE AvtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
        sql = "DELETE FROM Avtale WHERE AvtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);

    }

    public ArrayList<PersonalAppointment> appointmentChilds(PersonalAppointment pa) throws Exception {
        ArrayList<PersonalAppointment> avtaler = new ArrayList<PersonalAppointment>();
        String sql = "SELECT * FROM Underavtale WHERE OpphavsavtaleID ='" + pa.getAvtaleID() + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            PersonalAppointment mid = new PersonalAppointment();
            mid.setAvtaleID(rs.getInt("UnderavtaleID"));
            avtaler.add(mid);
        }
        return avtaler;
    }

    public boolean isChildEvent(PersonalAppointment pa) throws Exception {
        String sql = "SELECT * FROM Underavtale WHERE UnderavtaleID ='" + pa.getAvtaleID() + "';";
        ResultSet rs = stmt.executeQuery(sql);
        int ant = 0;
        while (rs.next()) {
            ant += 1;
        }
        if (ant == 0) return false;
        return true;
    }

    public PersonalAppointment getParentEvent(PersonalAppointment pa) throws Exception {
        if (isChildEvent(pa)) {
            String sql = "SELECT * FROM Underavtale WHERE UnderavtaleID ='" + pa.getAvtaleID() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            int avtid = 0;
            while (rs.next()) {
                avtid = rs.getInt("OpphavsavtaleID");
            }
            return getParentEvent(getSpecificAppointment(avtid));
        } else {
            return pa;
        }
    }

    public Invite getInvite(PersonalAppointment pap) throws Exception {
        String sql = "SELECT * FROM Invitasjon WHERE AvtaleID ='" + getParentEvent(pap).getAvtaleID() + "' AND Brukernavn = '" + Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        Invite invite = new Invite(this);
        while (rs.next()) {
            invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
            invite.setBrukernavn(rs.getString("Brukernavn"));
            invite.setAvtaleID(rs.getInt("AvtaleID"));
            invite.setGodtatt(rs.getBoolean("Godtatt"));
        }
        return invite;
    }

    public ArrayList<Invite> getMyInvites() throws Exception {
        ArrayList<Invite> invitasjoner = new ArrayList<Invite>();
        String sql = "SELECT * FROM Invitasjon WHERE Brukernavn ='" + Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Invite invite = new Invite(this);
            invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
            invite.setBrukernavn(rs.getString("Brukernavn"));
            invite.setAvtaleID(rs.getInt("AvtaleID"));
            invite.setGodtatt(rs.getBoolean("Godtatt"));
            if (rs.wasNull()) {
                invitasjoner.add(invite);
            }
        }
        return invitasjoner;
    }

    public void removeInvite(PersonalAppointment pa) throws SQLException {
        String sql = "DELETE FROM Invitasjon WHERE AvtaleID = " + pa.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
    }

    public ArrayList<Invite> invitesSent(PersonalAppointment pas) throws Exception {
        PersonalAppointment pa = pas;
        ArrayList<Invite> invitasjoner = new ArrayList<Invite>();
        String sql = "SELECT * FROM Invitasjon WHERE AvtaleID ='" + pa.getAvtaleID() + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Invite invite = new Invite(this);
            invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
            invite.setBrukernavn(rs.getString("Brukernavn"));
            invite.setAvtaleID(rs.getInt("AvtaleID"));
            invite.setGodtatt(rs.getBoolean("Godtatt"));
            invitasjoner.add(invite);
        }
        return invitasjoner;
    }

    @SuppressWarnings("rawtypes")
    public List getRoomName() throws Exception {
        List<String> romnavn = new ArrayList<String>();
        String sql = "SELECT Romnavn FROM Møterom;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            romnavn.add(rs.getString("Romnavn"));
        }
        return romnavn;
    }

    public boolean roomIsTaken(PersonalAppointment pa) throws Exception {
        String sql = "SELECT * FROM Avtale WHERE Dato ='" + pa.getDato() + "' AND Romnavn ='" + pa.getRomnavn() + "';";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
        while (rs.next()) {
            PersonalAppointment appointment = new PersonalAppointment();
            appointment.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
            appointment.setDato(Date.valueOf(rs.getString("Dato")));
            appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
            appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
            appointment.setBeskrivelse(rs.getString("Beskrivelse"));
            appointment.setRomnavn(rs.getString("Romnavn"));
            appointment.setOpprettetAv(rs.getString("Brukernavn"));
            appointments.add(appointment);
        }
        for (PersonalAppointment pap : appointments) {
            if (pap.getStartTid().before(pa.getSluttTid()) && pa.getStartTid().before(pap.getSluttTid())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Overlap> appointmentOverlap(Date dato) throws Exception {
        ArrayList<Overlap> overlap = new ArrayList<Overlap>();
        String sql = "SELECT * FROM Avtale WHERE Dato ='" + dato + "' AND Brukernavn ='" + this.Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
        while (rs.next()) {
            PersonalAppointment appointment = new PersonalAppointment();
            appointment.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
            appointment.setDato(Date.valueOf(rs.getString("Dato")));
            appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
            appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
            appointment.setBeskrivelse(rs.getString("Beskrivelse"));
            appointment.setRomnavn(rs.getString("Romnavn"));
            appointment.setOpprettetAv(rs.getString("Brukernavn"));
            appointments.add(appointment);
        }
        for (PersonalAppointment pap : appointments) {
            Overlap mid = new Overlap();
            mid.setEvent(pap);
            overlap.add(mid);
        }
        for (Overlap pa : overlap) {
            for (Overlap p : overlap) {
                if (!pa.getEvent().equals(p.getEvent())) {
                    if (p.getEvent().getStartTid().before(pa.getEvent().getSluttTid()) && pa.getEvent().getStartTid().before(p.getEvent().getSluttTid())) {
                        pa.setAntallOverlapp();
                    }
                }
            }
        }
        return overlap;
    }

    public int getGroupId(String groupname) throws Exception {
        Group en = new Group();
        String sql = "SELECT Gruppe.Gruppenavn, Gruppe.GruppeID FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = '" + Username + "' AND Gruppe.Gruppenavn = '" + groupname + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            en.setGroupID(rs.getInt("GruppeID"));
        }
        if (groupname.equalsIgnoreCase("My groups")) return 0;
        return en.getGroupID();
    }

    public Group getGroup(int gpid) throws Exception {
        Group group = new Group();
        String sql = "SELECT * FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = '" + gpid + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Group en = new Group();
            en.setGroupID(Integer.parseInt(rs.getString("GruppeID")));
            en.setGroupName(rs.getString("Gruppenavn"));
        }

        return group;
    }

    public ArrayList<Group> getGroups() throws Exception {
        ArrayList<Group> group = new ArrayList<Group>();
        String sql = "SELECT Gruppe.Gruppenavn, Gruppe.GruppeID FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = '" + Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Group en = new Group();
            en.setGroupID(Integer.parseInt(rs.getString("GruppeID")));
            en.setGroupName(rs.getString("Gruppenavn"));
            group.add(en);
        }
        for (Group gp : group) {
            ArrayList<User> users = new ArrayList<User>();
            sql = "SELECT * FROM Bruker, Gruppe, Gruppemedlem WHERE Bruker.Brukernavn = Gruppemedlem.Brukernavn AND Gruppemedlem.GruppeID = Gruppe.GruppeID AND Gruppe.GruppeID = '" + gp.getGroupID() + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("Brukernavn"));
                user.setPassword(rs.getString("Passord"));
                user.setFirstname(rs.getString("Fornavn"));
                user.setLastname(rs.getString("Etternavn"));
                user.setEmail(rs.getString("E-post"));
                user.setPhone(rs.getString("Telefon"));
                users.add(user);
            }
            gp.setUsers(users);
        }
        return group;
    }

    public ArrayList<String> getGroupNames(String username) throws Exception {
        ArrayList<String> groupNames = new ArrayList<String>();
        String sql = "SELECT Gruppenavn FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = '" + username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            groupNames.add(rs.getString("Gruppenavn"));
        }
        return groupNames;
    }

    public ArrayList<User> getGroupMembers(int gpid) throws Exception {
        for (Group group : this.getGroups()) {
            if (group.getGroupID() == gpid) {
                return group.getUsers();
            }
        }
        return null;
    }

    //	public boolean equalsMembersGroup(String gn, ArrayList<User>members) throws Exception{
    //		String sql ="";
    //		ArrayList<User> groupMembers = new ArrayList<User>();
    //		sql = "SELECT Fornavn, Etternavn FROM Gruppe, Gruppemedlem, Bruker WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = Bruker.BrukerNavn AND Gruppe.GruppeID = '" + gn + "';";
    //		ResultSet rs = stmt.executeQuery(sql);
    //		while (rs.next()){
    //			User mid = this.getUser(rs.getString("Brukernavn"));
    //			groupMembers.add(mid);
    //		}
    //		for(User mem : groupMembers){
    //			if(!members.contains(mem))return false;
    //		}
    //
    //		return true;
    //	}
    //
    //	public boolean groupExists(ArrayList<User>members) throws Exception{
    //		String sql = "SELECT Gruppe.Gruppenavn FROM Gruppe, Gruppemedlem, Bruker WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = Bruker.Brukernavn AND Bruker.Brukernavn = '" + this.Username + "' ;";
    //		ResultSet rs = stmt.executeQuery(sql);
    //		ArrayList<String> groupNames = new ArrayList<String>();
    //		while (rs.next()){
    //			String mid = "";
    //			mid += " " + (rs.getString("Gruppenavn"));
    //			groupNames.add(mid);
    //		}
    //		for(String gn : groupNames){
    //			if(!this.equalsMembersGroup(gn, members))return false;
    //		}
    //		return true;
    //	}

    public boolean groupExists(ArrayList<User> members) throws Exception {
        ArrayList<Group> groups = this.getGroups();
        boolean[] sjekket = new boolean[groups.size()];
        int ind = 0;
        for (int i = 0; i < sjekket.length; i++) {
            sjekket[i] = false;
        }
        for (Group gruppe : groups) {
            if (gruppe.getUsers().size() != members.size()) sjekket[ind] = true;
            for (User user : gruppe.getUsers()) {
                if (!members.contains(user)) {
                    sjekket[ind] = true;
                }
            }
            ind += 1;
        }
        for (boolean bol : sjekket) {
            if (!bol) return true;
        }
        return false;
    }

    public void respondOnInvite(Invite invite, boolean answer) throws Exception {
        if (answer) {
            String sql = "UPDATE Invitasjon SET Godtatt ='1' WHERE Brukernavn ='" + invite.getBrukernavn() + "' AND InvitasjonID ='" + invite.getInvitasjonsID() + "';";
            stmt.executeUpdate(sql);
            PersonalAppointment pa = this.getSpecificAppointment(invite.getAvtaleID());
            pa.setAvtaleID(0);
            this.addAppointment(pa, 0);
            pa = this.getLastAppointment();
            sql = "INSERT INTO Underavtale VALUES ('" + pa.getAvtaleID() + "','" + invite.getAvtaleID() + "');";
            stmt.executeUpdate(sql);

        } else {
            String sql = "UPDATE Invitasjon SET Godtatt ='0' WHERE Brukernavn ='" + invite.getBrukernavn() + "' AND InvitasjonID ='" + invite.getInvitasjonsID() + "';";
            stmt.executeUpdate(sql);
        }
    }

    public void setAlarm(Alarm alarm) throws Exception {
        String sql = "INSERT INTO Alarm VALUES ('" + alarm.getBrukernavn() + "','" + alarm.getAvtaleID() + "', '" + alarm.getTidspunkt() + "');";
        stmt.executeUpdate(sql);
    }

    public ArrayList<Alarm> getAlarm() throws Exception {
        ArrayList<Alarm> alarmer = new ArrayList<Alarm>();
        String sql = "SELECT * FROM Alarm WHERE Brukernavn ='" + Username + "' AND Tidspunkt < NOW() ;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Alarm alarm = new Alarm(this);
            alarm.setBrukernavn(rs.getString("Brukernavn"));
            alarm.setAvtaleID(rs.getInt("AvtaleID"));
            alarm.setTidspunkt(rs.getTimestamp("Tidspunkt"));
            alarmer.add(alarm);
        }
        return alarmer;
    }

    public void removeAlarm(Alarm alarm) throws SQLException {
        String sql = "DELETE FROM Alarm WHERE Brukernavn = '" + alarm.getBrukernavn() + "' AND AvtaleID = " + alarm.getAvtaleID() + ";";
        stmt.executeUpdate(sql);
    }

    public void hashPassword(String Password) {
        String passwordToHash = Password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
    }
}
