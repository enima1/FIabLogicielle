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
/*
 * permet d'injecter un fichier xml dans la base de donn�e
 * sp�cifi�e 
 */
public class DBInjecteur {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*attributs pour la base de donn�es*/
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/g15008299";
		String user = "g15008299";
		String passwd = "/vxz627E";
		
		/*String url = "jdbc:mysql://localhost/DBTest";
		String  user = "adminDBTest";
		String passwd = "passwdDBTest";*/

		try {
			//se connecter � la base source
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);

			//creer la connection DbUnit	
			IDatabaseConnection dbConnection = new DatabaseConnection(connection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());

			//injecter un fichier xml appel� newdataset.xml
			File dataSetFile = new File("xml/fulldataset.xml");
			IDataSet newDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			ReplacementDataSet replacementDataSet = new ReplacementDataSet(newDataSet);
			DatabaseOperation.CLEAN_INSERT.execute(dbConnection, replacementDataSet);
            //signaler que tout s'est bien pass�
			System.out.println("fichier inject�");

		} catch (Exception e) {
			System.out.println("Connection failed as USER :" + user + " with: URL: "+ url);
			e.printStackTrace();
		}
	}
}