package bookStoreReservation;

import java.sql.*;
import java.util.Date;

import javax.swing.JOptionPane;

public class OrderManager {
	
	//Open Database Connection
	Connection conn = DatabaseConnect.dbConnect();
	Date date;
	User currentUser = User.readUser();
	
	public OrderManager(){
		
	}
	
	public void createOrder(){
		try {
			date = new Date();
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * from MyOrders");
			int counter = 0;
			while(rs.next())
				counter++;
			
			String query = "INSERT INTO MyOrders VALUES"+"('"+counter+"', '"+date.toString()+"', '"+currentUser.getUserName()+"', "
					+ "'"+currentUser.geteMail()+"', '"+currentUser.getFirstName()+"', '"+currentUser.getLastName()+"', '"+ currentUser.printBookList() +"')";
			statement.executeUpdate(query);
			
			JOptionPane.showMessageDialog(null, "Order# "+counter+" on "+date+ " was Created Succesfully!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
