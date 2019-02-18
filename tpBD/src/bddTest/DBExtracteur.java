package bddTest;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlMetadataHandler;

/*
 * classe permettant de cr�er un fichier Xml image de la base de donn�es
 */
public class DBExtracteur {
	
	@SuppressWarnings("deprecation")
	public DBExtracteur(String driver, String url, String user, String passwd, String xmlDataSetName)  {
		try {
			//se connecter � la base source
			Class.forName(driver).newInstance();
			Connection jdbcConnection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
            
			//creer la connection DbUnit	
			IDatabaseConnection dbConnection = new DatabaseConnection(jdbcConnection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			
			//cr�er le fichier xml
			IDataSet fullDataSet = dbConnection.createDataSet();
			FlatXmlDataSet.write(fullDataSet, new FileOutputStream(xmlDataSetName));
			System.out.println("fichiers g�n�r�s");

		} catch (Exception e) {
			System.out.println("Connection failed as USER :" + user + " with: URL: "+ url);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		/*String url = "jdbc:mysql://localhost/DBTest";
		String  user = "adminDBTest";
		String passwd = "passwdDBTest";*/
		String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/g15008299";
		String user = "g15008299";
		String passwd = "/vxz627E";
		
		String xmlDataSetName = "xml/fulldataset.xml";
		DBExtracteur dbExtracteur = new DBExtracteur(driver, url, user, passwd, xmlDataSetName);		
	}
}
