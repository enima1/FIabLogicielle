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

public class DBExtracteur {

	
	public DBExtracteur(String driver,String url,String user,String passwd,String xmlDataSetName) {
		
		try {	
			/* Connexion à la BD distante */
			Class.forName(driver).newInstance();
			Connection jdbcConnection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
			
			/* Connexion dbUnit */
			IDatabaseConnection dbConnection = new DatabaseConnection(jdbcConnection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			
			/* Création du fichier XML */
			IDataSet fullDataSet = dbConnection.createDataSet();
			FlatXmlDataSet.write(fullDataSet, new FileOutputStream(xmlDataSetName));
			System.out.println("fichiers générés");
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/dbtest";
		String user = "root";
		String passwd = "";
		String xmlDataSetName = "fulldataset.xml";
		
		DBExtracteur dbExtracteur = new DBExtracteur(driver, url, user, passwd, xmlDataSetName);		
		
		
	}
}
