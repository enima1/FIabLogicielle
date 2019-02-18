/**
 * @author Amine Boudraa
 * @author Yannick Gosset
 * @File Connect.java
 * Classe r�cup�rer sur ametice
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*classe de connection à une base de donnée mysql locale
 *Les opérations de cette classes ne servent qu'à la connection
 *pour mieux séparer connection/deconnection des opérations
 *sur la BD et donc de bien identifier les tests.
 */

public class Connect {
	/* la connection */
	private Connection connection = null;
	/* driver à choisir selon le type de la base mysql, posgres,... */
	private String DRIVER = "com.mysql.jdbc.Driver";
	/* les éléments pour accéder à une base comme utilisateur */
	/*
	 * mettre le mot de passe en dur dans une application est toléré pour ce TP mais
	 * interdit pour un vrai developpement
	 */
	private String url;
	private String user;
	// vaudra "lugiez";
	private String passwd;

	public Connect(String url, String user, String passwd) {
		try {
			this.connect(url, user, passwd);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	@SuppressWarnings("deprecation")
	public void connect(String url, String user, String passwd) throws SQLException {
		this.url = url;
		this.user = user;
		this.passwd = passwd;
		try {
			Class.forName(this.DRIVER).newInstance();
			this.connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		/*
		 * test simple pour vérifier la connection servira pour écrire des tests
		 */
		return this.connection != null;
	}

	public void disconnect() {
		/*
		 * Se deconnecter de la base proprement
		 */
		try {
			this.connection.close();
			this.connection = null;
			System.out.println("USER: " + user + " disconnected from URL: " + url);
		} catch (SQLException e) {
			System.out.print("Error: not connected");
		} catch (Exception e) {
			System.out.println("Unexpected exception");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/g15008299";
		String user = "g15008299";
		String passwd = "/vxz627E";
		Connect connect = new Connect(url, user, passwd);
		System.out.println("connecté");
		connect.disconnect();
		System.out.println("deconnecté");
	}

	public Connection getConnection() {
		return connection;
	}
}
