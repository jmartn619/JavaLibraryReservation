package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bookStoreReservation.Book;
import bookStoreReservation.DatabaseConnect;
import bookStoreReservation.OrderManager;
import bookStoreReservation.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyBooks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	protected User currentUser = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBooks frame = new MyBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyBooks() {
		//Load User
		currentUser = currentUser.readUser();
		
		//DB Connection
		Connection conn = DatabaseConnect.dbConnect();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnReserveAll = new JButton("Reserve All");
		btnReserveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, 
						   "Are you sure you wish to order these books?",null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					OrderManager order = new OrderManager();
					order.createOrder();
				} 
				
			}
		});
		
		JLabel lblNewLabel = new JLabel(currentUser.getUserName()+"'s Books");
		
		JButton btnRemoveSelected = new JButton("Remove Selected");
		btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, 
						   "Are you sure you wish to remove this book?",null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int row = table.getSelectedRow();
					model.removeRow(row);
					
					ArrayList<Book> updatedBooks = currentUser.getMyBooks();
					updatedBooks.remove(row);
					currentUser.setMyBooks(updatedBooks);
					currentUser.saveUser();
					
					model.fireTableDataChanged();
					table.setModel(model);
					scrollPane.setViewportView(table);
				} 
				

				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(75)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRemoveSelected)
								.addComponent(btnReserveAll))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReserveAll)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRemoveSelected))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		//Build Table from User book list.
		try{
			
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			String query = "SELECT pID, pName, pCategory, pAuthor FROM products WHERE pID =";
			
			if(currentUser.getMyBooks().size()==1){
				query+=currentUser.getMyBooks().get(0).getISBN();
			}
			else{
				query+=currentUser.getMyBooks().get(0).getISBN();
				for(int i = 1; i < currentUser.getMyBooks().size();i++){
					query+=" OR pID="+currentUser.getMyBooks().get(i).getISBN();
				
				}
			}
			rs = stmt.executeQuery(query);
			table = new JTable(BrowseBooks.buildTableModel(rs));
			table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			scrollPane.setViewportView(table);
			
			
			
		}
		catch(Exception e1){
		JOptionPane.showMessageDialog(null, e1);
		}
	}
}
