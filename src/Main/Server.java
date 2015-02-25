package Cliser;
import java.sql.*; 
public class Server {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://LocalHost:3306/fp"; 
	static final String USER = "root";
	static final String PASS = "lol";
	Connection conn;
	Statement stmt;
	boolean gyldig;
	public Server() {
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Connecting to the database");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		}
		catch(Exception e){
			System.out.println("Tilkoblingen feilet:" + e.getMessage());
		}
	}

	private boolean valid(String B, String P) throws SQLException{
		String values = "";
		String sql = "SELECT Brukernavn, Passord FROM Ansatt WHERE Brukernavn = '" + B + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			values += (rs.getString("Brukernavn"));
			values += ("-" + rs.getString("Passord"));
		}
		String[] sit = values.split("-");
		if(sit[0].equals(B) && sit[1].equals(P)){
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
public static void main(String[] args) throws Exception {
	Server en = new Server();
	System.out.println(en.getAll("Ansatt"));
	System.out.println(en.valid("simonssl", "lolol"));

}
}