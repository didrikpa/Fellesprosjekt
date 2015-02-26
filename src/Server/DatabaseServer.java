package Server;

import java.sql.*;

public class DatabaseServer {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://mysql.stud.ntnu.no/simonssl_fpgp_fp";
	static final String USER = "simonssl_fpgp";
	static final String PASS = "Vierbest";
	public String Brukernavn;
	public String Passord;
	Connection conn;
	Statement stmt;
	public boolean gyldig;
	public DatabaseServer() {
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Connecting to the database");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		}
		catch(Exception e){
			System.out.println("Tilkoblingen feilet:" + e.getMessage());
		}
		gyldig = false;
	}
	public void valid(String Bruker, String Passord) throws SQLException{
		String values = "";
		String sql = "SELECT Brukernavn, Passord FROM Bruker WHERE Brukernavn = '" + Bruker + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			values += (rs.getString("Brukernavn"));
			values += ("-" + rs.getString("Passord"));
		}
		String[] sit = values.split("-");
		if(sit[0].equals(Bruker) && sit[1].equals(Passord)){
			gyldig = true;
			this.Brukernavn = Bruker;
			this.Passord = Passord;
		}
		else{
			gyldig = false;
			this.Brukernavn = null;
			this.Passord = null;
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

	public boolean addUser(String un, String pw, String first, String last, String em, String tp) throws SQLException{
		if(!userExist(un)){
			String sql = "INSERT INTO Bruker VALUES ('" + un + "', '" + pw +"', '" + first +"', '" + last +"', '" + em +"', '" + tp + "');";
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
	public void quit() throws SQLException{
		conn.close();
		stmt.close();
	}
	public static void main(String[] args) throws Exception {
	}
}