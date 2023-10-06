package com.cg.qgs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.qgs.exception.QGSException;

public class JDBCUtility {

	private static Connection connection = null;

	public static Connection getConnection() throws QGSException{

		File file = null;
		FileInputStream inputStream = null;
		Properties properties = null;

		file = new File("resources/jdbc.properties");
		try {
			inputStream = new FileInputStream(file);

			properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (FileNotFoundException e) {
			throw new QGSException("File not present");
		} catch (IOException e) {
			throw new QGSException("couldn't be able to read the data from file");
		} catch (ClassNotFoundException e) {
			throw new QGSException("problem occured while loading the driver");
		} catch (SQLException e) {
			throw new QGSException("problem occured while connecting");
		}
		return connection;

	}

}
