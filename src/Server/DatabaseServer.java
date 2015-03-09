package Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.PersonalAppointment;
import Model.User;

public class DatabaseServer {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/simonssl_fpgp_fp";
	static final String USER = "simonssl_fpgp";
	static final String PASS = "Vierbest";
	private String Username;
	private String Password;
	Connection conn;
	Statement stmt;
	
	public DatabaseServer() {
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		}
		catch(Exception e){
			System.out.println("Connection failed:" + e.getMessage());
		}
	}
	
	public boolean login(String B, String P) throws SQLException{
		if(userExist(B)){
			String values = "";
			String sql = "SELECT Brukernavn, Passord FROM Bruker WHERE Brukernavn = '" + B + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				values += (rs.getString("Brukernavn"));
				values += ("-" + rs.getString("Passord"));
			}
			String[] sit = values.split("-");
			if(sit[0].equals(B) && sit[1].equals(P)){
				Username = sit[0];
				Password = sit[1];
				return true;
			}
			else{
				return false; 
			}
		}
		return false;
	}
	
	public User getUser() throws Exception {
		User user = new User();
		String sql = "SELECT * FROM Bruker WHERE Brukernavn = '" + this.Username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
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
		ArrayList<User> users= new ArrayList<User>();
		String sql = "SELECT * FROM Bruker;";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
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

	public boolean addUser(User user) throws SQLException{
		if(!userExist(user.getUsername())){
			String sql = "INSERT INTO Bruker VALUES ('" + user.getUsername() + "', '" + user.getPassword() +"', '" + user.getFirstname() +"', '" + user.getLastname() +"', '" + user.getEmail() +"', '" + user.getPhone() + "');";
			stmt.executeUpdate(sql);
			return true;
		}
		return false;
	}
	
	public void editUser(User user) throws Exception {
		if(user.getUsername().equalsIgnoreCase(Username)){
			String sql = "UPDATE Bruker SET Telefon ='" + user.getPhone() + "', Passord ='" + user.getPassword() + "' WHERE Brukernavn ='" + this.Username + "';";
			stmt.executeUpdate(sql);
			sql = "UPDATE `Bruker` SET `E-post` = '" + user.getEmail() + "' WHERE `Bruker`.`Brukernavn` = '" + this.Username + "';";
			stmt.executeUpdate(sql);
		}
	}

	public boolean userExist(String username) throws SQLException{
		String values = "";
		String sql = "SELECT Brukernavn, Passord FROM Bruker WHERE Brukernavn = '" + username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			values += (rs.getString("Brukernavn"));
			values += ("-" + rs.getString("Passord"));
		}
		values.replaceAll(" ", "");
		if(values.equals("")){
			return false;
		}
		else{
			return true;
		}
	}
	
	public ArrayList<PersonalAppointment> getAppointment(Date date) throws Exception{
		String sql = "SELECT * FROM Avtale, Bruker WHERE Bruker.Brukernavn = '" + Username + "' AND Avtale.Dato ='" + date.toString() + "' AND Bruker.Brukernavn = Avtale.Brukernavn;";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
		while(rs.next()){
			PersonalAppointment appointment = new PersonalAppointment();
			appointment.setDato(Date.valueOf(rs.getString("Dato")));
			appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
			appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
			appointment.setBeskrivelse(rs.getString("Beskrivelse"));
			appointment.setRomnavn(rs.getString("Romnavn"));
			appointments.add(appointment);
		}
		return appointments;
	}
	
	//Henter n-antall nærmeste avtaler
	public ArrayList<PersonalAppointment> comingUp(int n) throws Exception{
		if(n > 0){
			String sql = "SELECT * FROM Avtale WHERE Dato >= CURDATE() AND Brukernavn = '" + Username + "' ORDER BY Dato ASC LIMIT " + n + ";";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
			while(rs.next()){
				PersonalAppointment appointment = new PersonalAppointment();
				appointment.setDato(Date.valueOf(rs.getString("Dato")));
				appointment.setStartTid(Time.valueOf(rs.getString("Starttid")));
				appointment.setSluttTid(Time.valueOf(rs.getString("Slutttid")));
				appointment.setBeskrivelse(rs.getString("Beskrivelse"));
				appointment.setRomnavn(rs.getString("Romnavn"));
				appointments.add(appointment);
			}
			return appointments;
		}
		return null;
	}


	
	public void addAppointment(PersonalAppointment appointment) throws Exception {
		String sql = "INSERT INTO Avtale VALUES ( NULL,'" + appointment.getDato().toString() + "', '" + appointment.getStartTid().toString() +"', '" + appointment.getSluttTid().toString() +"', '" + appointment.getBeskrivelse() +"', '" + appointment.getRomnavn() +"', '" + Username + "'," + null + ");";
		stmt.executeUpdate(sql);
	}

    public boolean emailExist(String email) throws SQLException{
        String values = "";
        String sql = "SELECT `E-post` FROM Bruker WHERE `E-post` = '" + email + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            values += (rs.getString("E-post"));
        }
        values.replaceAll(" ", "");
        if(values.equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    public String getPassword(String email) throws Exception {
        String password;
        password = this.Password;
        String sql = "SELECT Passord FROM Bruker WHERE `E-post` = '" + email + "' ;";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            password = rs.getString("Passord");
        }
        return password;
    }

    public List getRoomName() throws Exception {
        List<String> romnavn = new ArrayList<String>();
        String sql = "SELECT Romnavn FROM Møterom;";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            romnavn.add(rs.getString("Romnavn"));
        }
        return romnavn;
    }

	public List getGroups() throws Exception{
        List<String> groupNames = new ArrayList<String>();
        String sql = "SELECT Gruppenavn FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = '" + Username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            groupNames.add(rs.getString("Gruppenavn"));
        }
        return groupNames;
    }

    public List getGroupMembers(String groupName) throws Exception{
        List<String> groupMembers = new ArrayList<String>();
        String sql = "SELECT Fornavn, Etternavn FROM Gruppe, Gruppemedlem, Bruker WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = Bruker.BrukerNavn AND Gruppe.GruppeNavn = '" + groupName + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            groupMembers.add(rs.getString("Fornavn"));
            groupMembers.add(rs.getString("Etternavn"));
        }
        return groupMembers;
    }


	public void quit() throws SQLException{
		conn.close();
		stmt.close();
	}

    public void hashPassword(String Password)
    {
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
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
    }
}
