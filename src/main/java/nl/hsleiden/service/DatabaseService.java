package nl.hsleiden.service;

import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class DatabaseService {
	private Connection dbconnection;
	private String username = "postgres";
	private String password = "Tijgereetijs17!";
	private String databaseAdress = "jdbc:postgresql://136.144.170.229/martijn";

	public DatabaseService(){
		try{
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find the postgresql driver");
		}

		try{
			dbconnection = DriverManager.getConnection(databaseAdress,username,password);
			System.out.println(dbconnection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		return dbconnection;
	}
}
