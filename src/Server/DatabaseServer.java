package Server;

import java.sql.*;
import java.util.ArrayList;
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
	
	public String getAll(String table) throws Exception {
		String values = "";
		String sql = "SELECT * FROM " + table + ";";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			values += (rs.getString("Brukernavn"));
			values += (rs.getString("Passord"));
			values += (rs.getString("Fornavn"));
			values += (rs.getString("Etternavn"));
			values += (rs.getString("E-post"));
			values += (rs.getString("Telefon"));
			values += " ";
		}
		return values;
	}

	public boolean addUser(User user) throws SQLException{
		if(!userExist(user.getUsername())){
			String sql = "INSERT INTO Bruker VALUES ('" + user.getUsername() + "', '" + user.getPassword() +"', '" + user.getFirstname() +"', '" + user.getLastname() +"', '" + user.getEmail() +"', '" + user.getPhone() + "');";
			stmt.executeUpdate(sql);
			return true;
		}
		return false;
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
	
	public void addAppointment(PersonalAppointment appointment) throws Exception {
		String sql = "INSERT INTO Avtale VALUES ( NULL,'" + appointment.getDato().toString() + "', '" + appointment.toString() +"', '" + appointment.getSluttTid().toString() +"', '" + appointment.getBeskrivelse() +"', '" + appointment.getRomnavn() +"', '" + Username + "'," + null + ");";
		stmt.executeUpdate(sql);
	}
	
	public void quit() throws SQLException{
		conn.close();
		stmt.close();
	}
}
