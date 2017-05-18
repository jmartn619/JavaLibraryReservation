package bookStoreReservation;

import java.sql.*;
import javax.swing.*;


public class DatabaseConnect {
	Connection conn = null;
	
	public static Connection dbConnect(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/jonmartin/Documents/workspace/BookstoreReservationSystem/src/Database/What the Book.sqlite");
			System.out.println("Connection Succesful");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}

}
