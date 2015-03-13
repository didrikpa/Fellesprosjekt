package Server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Alarm;
import Model.Group;
import Model.Invite;
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
		String sql = "SELECT * FROM Avtale, Bruker WHERE Bruker.Brukernavn = '" + Username + "' AND Avtale.Dato ='" + date.toString() + "' AND Bruker.Brukernavn = Avtale.Brukernavn ORDER BY Starttid;";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
		while(rs.next()){
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
		return appointments;
	}

	public ArrayList<PersonalAppointment> getAppointment(Date date, Group group) throws Exception{
		ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
		for(User user:group.getUsers()){
			if(!user.getUsername().equals(Username)){
				String sql = "SELECT * FROM Avtale, Bruker WHERE Bruker.Brukernavn = '" + user.getUsername() + "' AND Avtale.Dato ='" + date.toString() + "' AND Bruker.Brukernavn = Avtale.Brukernavn ORDER BY Starttid;";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
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
			}
		}
		return appointments;
	}

	//Henter n-antall nærmeste avtaler
	public ArrayList<PersonalAppointment> comingUp(int n) throws Exception{
		if(n > 0){
			String sql = "SELECT * FROM Avtale WHERE Dato >= NOW() AND Brukernavn = '" + Username + "' ORDER BY Dato ASC, Starttid LIMIT " + n + ";";
			ResultSet rs = stmt.executeQuery(sql);
			ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
			while(rs.next()){
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
			return appointments;
		}
		return null;
	}

	public void addAppointment(PersonalAppointment appointment, ArrayList<User> invitedUsers) throws Exception {
		String sql = "INSERT INTO Avtale VALUES ( NULL,'" + appointment.getDato().toString() + "', '" + appointment.getStartTid().toString() +"', '" + appointment.getSluttTid().toString() +"', '" + appointment.getBeskrivelse() +"', '" + appointment.getRomnavn() +"', '" + Username + "'," + null + ");";
		stmt.executeUpdate(sql);
		if(invitedUsers != null){
			sql = "SELECT * FROM Avtale WHERE Brukernavn = '" + Username + "' ORDER BY AvtaleID DESC LIMIT 1;";
			ResultSet rs = stmt.executeQuery(sql);
			PersonalAppointment pa = new PersonalAppointment();
			while(rs.next()){
				pa.setAvtaleID(Integer.parseInt(rs.getString("AvtaleID")));
			}
			for(User user:invitedUsers){
				sql = "INSERT INTO `simonssl_fpgp_fp`.`Invitasjon` (`InvitasjonID`, `Brukernavn`, `AvtaleID`, `Godtatt`) VALUES (NULL, '" + user.getUsername() + "','" + pa.getAvtaleID() + "', NULL);";
				stmt.executeUpdate(sql);
			}
		}
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

	@SuppressWarnings("rawtypes")
	public List getRoomName() throws Exception {
		List<String> romnavn = new ArrayList<String>();
		String sql = "SELECT Romnavn FROM Møterom;";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			romnavn.add(rs.getString("Romnavn"));
		}
		return romnavn;
	}

	public ArrayList<Group> getGroups() throws Exception{
		ArrayList<Group> group = new ArrayList<Group>();
		String sql = "SELECT Gruppe.Gruppenavn, Gruppe.GruppeID FROM Gruppe, Gruppemedlem WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = '" + Username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()){
			Group en = new Group();
			en.setGroupID(Integer.parseInt(rs.getString("GruppeID")));
			en.setGroupName(rs.getString("Gruppenavn"));
			group.add(en);
		}
		for(Group gp:group){
			ArrayList<User>users = new ArrayList<User>();
			sql = "SELECT * FROM Bruker, Gruppe, Gruppemedlem WHERE Bruker.Brukernavn = Gruppemedlem.Brukernavn AND Gruppemedlem.GruppeID = Gruppe.GruppeID AND Gruppe.GruppeID = '" + gp.getGroupID() + "';";
			rs = stmt.executeQuery(sql);
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
			gp.setUsers(users);
		}
		return group;
	}

	@SuppressWarnings("rawtypes")
	public List getGroupMembers(String groupName) throws Exception{
		List<String> groupMembers = new ArrayList<String>();
		String sql = "SELECT Fornavn, Etternavn FROM Gruppe, Gruppemedlem, Bruker WHERE Gruppe.GruppeID = Gruppemedlem.GruppeID AND Gruppemedlem.Brukernavn = Bruker.BrukerNavn AND Gruppe.GruppeID = '" + groupName + "';";
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

	public ArrayList<Invite> getInvites() throws Exception {
		ArrayList<Invite> invitasjoner = new ArrayList<Invite>();
		String sql = "SELECT * FROM Invitasjon WHERE Brukernavn ='" + Username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			Invite invite = new Invite(this);
			invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
			invite.setBrukernavn(rs.getString("Brukernavn"));
			invite.setAvtaleID(rs.getInt("AvtaleID"));
			invite.setGodtatt(rs.getBoolean("Godtatt"));
			if(rs.wasNull()){
				invitasjoner.add(invite);
			}
		}
		return invitasjoner;
	}
	
	public ArrayList<Alarm> getAlarm() throws Exception {
		ArrayList<Alarm> alarmer = new ArrayList<Alarm>();
		String sql = "SELECT * FROM Alarm WHERE Brukernavn ='" + Username + "' AND Tidspunkt < NOW() ;";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			 Alarm alarm = new Alarm(this);
			alarm.setBrukernavn(rs.getString("Brukernavn"));
			alarm.setAvtaleID(rs.getInt("AvtaleID"));
			alarm.setTidspunkt(rs.getTimestamp("Tidspunkt"));
			alarmer.add(alarm);
		}
		return alarmer;
	}
	

	public PersonalAppointment specificAppointment(int avtaleid) throws Exception{
		PersonalAppointment pa = new PersonalAppointment();
		String sql = "SELECT * FROM Avtale WHERE AvtaleID ='" + avtaleid + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			pa.setAvtaleID(avtaleid);
			pa.setBeskrivelse(rs.getString("Beskrivelse"));
			pa.setOpprettetAv(rs.getString("Brukernavn"));
			pa.setDato(rs.getDate("Dato"));
			pa.setRomnavn(rs.getString("Romnavn"));
			pa.setStartTid(rs.getTime("Starttid"));
			pa.setSluttTid(rs.getTime("Slutttid"));
		}
		return pa;
	}

	public PersonalAppointment getLastAppointment() throws Exception{
		String sql = "SELECT * FROM Avtale WHERE Brukernavn = '" + this.Username + "' ORDER BY AvtaleID DESC LIMIT 1;";
		PersonalAppointment pa = new PersonalAppointment();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			pa.setAvtaleID(rs.getInt("AvtaleID"));
			pa.setBeskrivelse(rs.getString("Beskrivelse"));
			pa.setOpprettetAv(rs.getString("Brukernavn"));
			pa.setDato(rs.getDate("Dato"));
			pa.setRomnavn(rs.getString("Romnavn"));
			pa.setStartTid(rs.getTime("Starttid"));
			pa.setSluttTid(rs.getTime("Slutttid"));
		}
		return pa;
	}
	
	public void respond(Invite invite, boolean answer) throws Exception{
		if(answer){
			String sql = "UPDATE Invitasjon SET Godtatt ='1' WHERE Brukernavn ='" + invite.getBrukernavn() + "' AND InvitasjonID ='" + invite.getInvitasjonsID() + "';";
			stmt.executeUpdate(sql);
			PersonalAppointment pa = this.specificAppointment(invite.getAvtaleID());
			pa.setAvtaleID(0);
			this.addAppointment(pa, null);
			pa = this.getLastAppointment();
			sql = "INSERT INTO Underavtale VALUES ('" + pa.getAvtaleID() + "','" + invite.getAvtaleID() + "');";
			stmt.executeUpdate(sql);

		}
		else{
			String sql = "UPDATE Invitasjon SET Godtatt ='0' WHERE Brukernavn ='" + invite.getBrukernavn() + "' AND InvitasjonID ='" + invite.getInvitasjonsID() + "';";
			stmt.executeUpdate(sql);
		}
	}
	
	public void setAlarm(Alarm alarm) throws Exception {
		String sql = "INSERT INTO Alarm VALUES ('" + alarm.getBrukernavn() + "','" + alarm.getAvtaleID() + "', '" + alarm.getTidspunkt() + "');";
		stmt.executeUpdate(sql);
	}
	public void removeAlarm(Alarm alarm) throws SQLException{
		String sql = "DELETE FROM Alarm WHERE Brukernavn = '" + alarm.getBrukernavn() + "' AND AvtaleID = " + alarm.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
	}
	public boolean roomIsTaken(PersonalAppointment pa) throws Exception{
		String sql = "SELECT * FROM Avtale WHERE Dato ='" + pa.getDato() + "' AND Romnavn ='" + pa.getRomnavn() + "';";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList <PersonalAppointment> appointments = new ArrayList<PersonalAppointment>();
		while(rs.next()){
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
		for(PersonalAppointment pap : appointments){
			if(pap.getStartTid().before(pa.getSluttTid()) && pa.getStartTid().before(pap.getSluttTid())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isChildEvent(PersonalAppointment pa) throws Exception{
		String sql = "SELECT * FROM Underavtale WHERE UnderavtaleID ='" + pa.getAvtaleID() + "';";
		ResultSet rs = stmt.executeQuery(sql);
		int ant = 0;
		while(rs.next()){
			ant += 1;
		}
		if(ant==0)return false;
		return true;
	}
	
	public ArrayList<PersonalAppointment> appointmentChilds(PersonalAppointment pa) throws Exception{
		ArrayList<PersonalAppointment> avtaler = new ArrayList<PersonalAppointment>();
		String sql = "SELECT * FROM Underavtale WHERE OpphavsavtaleID ='" + pa.getAvtaleID() + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			PersonalAppointment mid = new PersonalAppointment();
			mid.setAvtaleID(rs.getInt("UnderavtaleID"));
		}
		return avtaler;
	}
	
	public void removeAppointment(PersonalAppointment pa) throws Exception{
		ArrayList<PersonalAppointment>childs = appointmentChilds(pa);
		String sql = "DELETE FROM Underavtale WHERE OpphavsavtaleID = " + pa.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
		sql = "DELETE FROM Underavtale WHERE UnderavtaleID = " + pa.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
		sql = "DELETE FROM Avtale WHERE AvtaleID = " + pa.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
		sql = "DELETE FROM Alarm WHERE AvtaleID = " + pa.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
		sql = "DELETE FROM Invitasjon WHERE AvtaleID = " + pa.getAvtaleID() + ";";
		stmt.executeUpdate(sql);
		for(PersonalAppointment pas:childs){
			removeAppointment(pas);
		}
	}
	
	public Invite getInvite(PersonalAppointment pap) throws Exception{
		String sql = "SELECT * FROM Invitasjon WHERE AvtaleID ='" + getParentEvent(pap) + "' AND Brukernavn = '" + Username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		Invite invite = new Invite(this);
		while(rs.next()){
			invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
			invite.setBrukernavn(rs.getString("Brukernavn"));
			invite.setAvtaleID(rs.getInt("AvtaleID"));
			invite.setGodtatt(rs.getBoolean("Godtatt"));
		}
		return invite;
	}
	
	public ArrayList<Invite> getInvited(PersonalAppointment pas) throws Exception {
		PersonalAppointment pa = pas;
		ArrayList<Invite> invitasjoner = new ArrayList<Invite>();
		String sql = "SELECT * FROM Invitasjon WHERE AvtaleID ='" + pa.getAvtaleID() + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			Invite invite = new Invite(this);
			invite.setInvitasjonsID(rs.getInt("InvitasjonID"));
			invite.setBrukernavn(rs.getString("Brukernavn"));
			invite.setAvtaleID(rs.getInt("AvtaleID"));
			invite.setGodtatt(rs.getBoolean("Godtatt"));
			invitasjoner.add(invite);
		}
		return invitasjoner;
	}
	
	public PersonalAppointment getParentEvent(PersonalAppointment pa) throws Exception{
		if(isChildEvent(pa)){
			String sql = "SELECT * FROM Underavtale WHERE UnderavtaleID ='" + pa.getAvtaleID() + "';";
			ResultSet rs = stmt.executeQuery(sql);
			int avtid = 0;
			while(rs.next()){
				avtid = rs.getInt("OpphavsavtaleID");
			}
			return getParentEvent(specificAppointment(avtid));
		}
		else{
			return pa;
		}
	}
}
