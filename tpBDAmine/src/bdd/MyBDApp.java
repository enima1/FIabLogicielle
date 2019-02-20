/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @File MyBDApp.java
 */
package bdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MyBDApp {
	
	final String url = "jdbc:mysql://localhost/dbtest";
	final String user = "root";
	final String passwd = "";
	
	private Connection newConnection() throws SQLException {
		Connect conn = new Connect(url, user, passwd);
		return conn.getConnection();
	}
	
	public void addName(int id, String name) throws SQLException {
		final String query = "insert into Personne values (?,?)";
		try (Connection conn = newConnection()){
			java.sql.PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,id);
			st.setString(2, name);
			st.execute();
		}
	}
	
	public void deleteName(int id) throws SQLException {
		final String query = "delete from Personne where (id = ?)";
		try (Connection conn = newConnection()){
			java.sql.PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,id);
			st.execute();
		}
	}
	
	public String findName (int id) throws SQLException {
		final String query = "Select * from Personne where (id = ?)";
		try (Connection conn = newConnection()){
			java.sql.PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return rs.getString("Nom");
		}
		return null;
	}
	

	
	public static void main(String[] args) throws SQLException {

	}
}
