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
 * L'assert de Junit est prio sur l'assert de DBunit  -> on est oblig� d'appeler  en static la m�thode assertEquals de dbUnit
 * A chaque test termin�, on est oblig� de recharger la BD initiale(parce qu'on est pas sur de l'ordre dans lequel sont fait les tests :
 * 
 * Les op�rations doivent ainsi toutes partir d'une bd de r�f�rence -> si on fait 2 tests qui modifient la bd sans la r�initialiser :
 * le second test aura des donn�es biais�es vu que la bd a chang� � cause du premier
 * => Ceci est du au fait qu'� chaque fois on compare ENTIEREMENT la BD de r�f�rence avec la BD apr�s op�ration
 * 
 * Ainsi une op�ration de suppression sur DBUNIT revient surtout � v�rifier que le champ supprim� n'est plus l� dans le DataSet r�sultat
 * (ou dans le fichierxml)
 * plut�t que de v�rifier si le champ est "null" avec junit
 * 
 * 
 * La m�thode de r�cup�ration d'un fichier XML � partir de la BD est vraiment rapide, �a nous permet de faire des comparaisons tr�s facilement
 * 
 * Utiliser dbunit c'est interessant pour tester les op�rations qui font des modifications sur des BD et ceci 
 * vu qu'on utilise des DataSet qui repr�sentent des BD avant et apr�s op�rations 
 * 
 * Pour tester des op�rations de type "get" faire des requ�tes et faire des simples assertEquals avec junit suffisent
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
			
			/* Connexion � la BD */
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connected as USER: " + user + " with: URL: " + url);
			
			/* Connexion dbUnit */
			dbConnection = new DatabaseConnection(connection, "DBTest" );
			dbConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
			System.out.println("connection DBUnit �tablie");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/* R�tablit la BD De base */
	@AfterClass
	public static void finaliser() {
		try {
			retablir_bd();
			connection.close();
			System.out.println("BD R�tablie");
			System.out.println("Connexion DB Distante ferm�e");
			System.out.println("Connexion DBunit ferm�e");
			dbConnection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* R�tablit la BD de r�f�rence */
	
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
			
			/* Cr�ation du dataset � partir du fichier de r�f�rence */
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
	 * Teste l'injection d'un fichier XML dans la base de donn�e
	 */
	@Test
	public void testInjection() {
		
		initialiser();
		
		try {	
			
			/* Cr�ation du dataset attendu � partir d'un fichier */
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
	 * Test le cas normal d'une suppression d'un �l�ment existant.
	 */
	@Test
	public void testDeleteElement() {
		
			initialiser();
		try {

			/* Cr�ation du dataset attendu � partir d'un fichier */
			File dataSetFile = new File("dataSetExpected3.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			
			/* Suppression de l'entr�e avec l'id 1 */
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
	 * Test le cas normal de l'ajout d'un �l�ment inexistant.
	 */
	@Test
	public void testaddElement() {
		
			initialiser();
		try {

			/* Cr�ation du dataset attendu � partir d'un fichier */
			File dataSetFile = new File("dataSetExpected4.xml");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			
			/* Ajout d'une entr�e */
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
