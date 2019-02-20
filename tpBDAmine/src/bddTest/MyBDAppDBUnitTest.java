package bddTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Statement;

import org.dbunit.DBTestCase;
import org.dbunit.assertion.DbUnitAssert;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import bdd.MyBDApp;
	

/*
 * 
 * Commentaires
 * 
 * L'assert de Junit est prio sur l'assert de DBunit  -> on est obligé d'appeler  en static la méthode assertEquals de dbUnit
 * A chaque test terminé, on est obligé de recharger la BD initiale(parce qu'on est pas sur de l'ordre dans lequel sont fait les tests :
 * 
 * Les opérations doivent ainsi toutes partir d'une bd de référence -> si on fait 2 tests qui modifient la bd sans la réinitialiser :
 * le second test aura des données biaisées vu que la bd a changé à cause du premier
 * => Ceci est du au fait qu'à chaque fois on compare ENTIEREMENT la BD de référence avec la BD après opération
 * 
 * Ainsi une opération de suppression sur DBUNIT revient surtout à vérifier que le champ supprimé n'est plus là dans le DataSet résultat
 * (ou dans le fichierxml)
 * plutôt que de vérifier si le champ est "null" avec junit
 * 
 * 
 * La méthode de récupération d'un fichier XML à partir de la BD est vraiment rapide, ça nous permet de faire des comparaisons très facilement
 * 
 * Utiliser dbunit c'est interessant pour tester les opérations qui font des modifications sur des BD et ceci 
 * vu qu'on utilise des DataSet qui représentent des BD avant et après opérations 
 * 
 * Pour tester des opérations de type "get" faire des requêtes et faire des simples assertEquals avec junit suffisent
 */
class MyBDAppDBUnitTest extends DBTestCase{
	
	 static String driver = "com.mysql.jdbc.Driver";
	 static String url = "jdbc:mysql://localhost/dbtest";
	 static String user = "root";
	 static String passwd = "";
	 static Connection connection;
	 static IDatabaseConnection dbConnection ;
	
	@BeforeClass
	public static void initialiser() {
		try {
			
			/* Connexion à la BD */
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
			
			/* Connexion dbUnit */
			dbConnection = new DatabaseConnection(connection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			System.out.println("connection DBUnit établie");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/* Rétablit la BD De base */
	@AfterClass
	public static void finaliser() {
		try {
			retablir_bd();
			connection.close();
			System.out.println("BD Rétablie");
			System.out.println("Connexion DB Distante fermée");
			System.out.println("Connexion DBunit fermée");
			dbConnection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* Rétablit la BD de référence */
	
	public static void retablir_bd()  {
		
		File dataSetFile = new File("dataSetReference.xml");
		IDataSet dataSetInjecte;
		try {
			dataSetInjecte = new FlatXmlDataSetBuilder().build(dataSetFile);
			ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSetInjecte);
			DatabaseOperation.CLEAN_INSERT.execute(dbConnection, replacementDataSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Teste l'extraction de la BD dans un fichier XML
	 */
	@Test
	public void testExtraction() {

		initialiser();
		try {	
			
			/* Création du dataset à partir du fichier de référence */
			File dataSetFile = new File("dataSetReference.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
		
			
			/* Extraction du dataset de la BD */
			IDataSet dataSetExtracted= dbConnection.createDataSet();
			
			/* Comparaison des deux dataSets */
			new DbUnitAssert().assertEquals(expectedDataSet,dataSetExtracted);
			finaliser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Teste l'injection d'un fichier XML dans la base de donnée
	 */
	@Test
	public void testInjection() {
		
		initialiser();
		
		try {	
			
			/* Création du dataset attendu à partir d'un fichier */
			File dataSetFile = new File("dataSetExpected2.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
		
			/* Insertion du dataset attendu dans la BD */
			ReplacementDataSet replacementDataSet = new ReplacementDataSet(expectedDataSet);
			DatabaseOperation.CLEAN_INSERT.execute(dbConnection, replacementDataSet);
			
			/* Extraction  du dataSet de la BD */
			IDataSet dataSetExtracted = dbConnection.createDataSet();
			
			/* Comparaison des deux dataSets */
			new DbUnitAssert().assertEquals(expectedDataSet,dataSetExtracted);
			finaliser();
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Test le cas normal d'une suppression d'un élément existant.
	 */
	@Test
	public void testDeleteElement() {
		
			initialiser();
		try {

			/* Création du dataset attendu à partir d'un fichier */
			File dataSetFile = new File("dataSetExpected3.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			
			/* Suppression de l'entrée avec l'id 1 */
			String query = "delete from Personne where (id = 1)";
			java.sql.PreparedStatement st = connection.prepareStatement(query);
			st.execute();
			
			/* Extraction  du dataSet de la BD */
			IDataSet dataSetExtracted = dbConnection.createDataSet();
			
			/* Comparaison des deux dataSets */
			new DbUnitAssert().assertEquals(expectedDataSet,dataSetExtracted);
			finaliser();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	/**
	 * Test le cas normal de l'ajout d'un élément inexistant.
	 */
	@Test
	public void testaddElement() {
		
			initialiser();
		try {

			/* Création du dataset attendu à partir d'un fichier */
			File dataSetFile = new File("dataSetExpected4.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			
			/* Ajout d'une entrée */
			String query = "insert into Personne values (204,?)";
			java.sql.PreparedStatement st = connection.prepareStatement(query);
			st.setString(1,"Clown");
			st.execute();
			
			/* Extraction  du dataSet de la BD */
			IDataSet dataSetExtracted = dbConnection.createDataSet();
			
			/* Comparaison des deux dataSets */
			new DbUnitAssert().assertEquals(expectedDataSet,dataSetExtracted);
			finaliser();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
