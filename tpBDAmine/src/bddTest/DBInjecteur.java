package bddTest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;


public class DBInjecteur {
		
	public static void main(String[] args) {
		
		/*Champs pour la connection à la BD*/
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/dbtest";
		String user = "root";
		String passwd = "";
		
		
		
		try {
			
			/* Connexion à la BD */
			
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
			
			/* Création de la connexion dbUnit */
			IDatabaseConnection dbConnection = new DatabaseConnection(connection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			
			/* Injection dans la BD du fichier XML  appelé fulldataset.xml */
			
			File dataSetFile = new File("fulldataset.xml");
			IDataSet newDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			ReplacementDataSet replacementDataSet = new ReplacementDataSet(newDataSet);
			DatabaseOperation.CLEAN_INSERT.execute(dbConnection, replacementDataSet);
			
           
			System.out.println("fichier injecté");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
}
