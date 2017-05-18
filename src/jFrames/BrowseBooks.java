package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bookStoreReservation.Book;
import bookStoreReservation.DatabaseConnect;
import bookStoreReservation.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import java.sql.*;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BrowseBooks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JTextField textFieldFilter;
	
	protected User currentUser = new User();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseBooks frame = new BrowseBooks();
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
	public BrowseBooks() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 475, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblCollegeOfStaten = new JLabel("College of Staten Island Bookstore");
		
		textFieldFilter = new JTextField();
		textFieldFilter.setColumns(10);
		
		JButton btnFilter = new JButton("Filter ISBN");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFilter();
			}
		});
		
		JButton btnAddBook = new JButton("Add to My Books!");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser = currentUser.readUser();
				
				int row = table.getSelectedRow();
				Book selectedBook = new Book((long)table.getValueAt(row, 0),(String)table.getValueAt(row, 1),
						(String)table.getValueAt(row, 2),(String)table.getValueAt(row, 3));
				
				currentUser.addBook(selectedBook);
				currentUser.saveUser();
			}
		});
		
	    

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAddBook)
							.addGap(67)
							.addComponent(textFieldFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFilter))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(106)
							.addComponent(lblCollegeOfStaten)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCollegeOfStaten)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFilter)
						.addComponent(textFieldFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddBook))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		//Database Connection
		Connection conn = DatabaseConnect.dbConnect();
		try{
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT pID, pName, pCategory, pAuthor FROM products");
			table = new JTable(buildTableModel(rs));
			table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			scrollPane.setViewportView(table);
			
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(null, e1);
		}
		
		//Table Sorting Method
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		table.setAutoResizeMode(5);
		
		
	}
	private void newFilter() {
	    RowFilter<TableModel, Object> rf = null;
	    //If current expression doesn't parse, don't update.
	    try {
	        rf = RowFilter.regexFilter(textFieldFilter.getText(), 0);
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	    sorter.setRowFilter(rf);
	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	 
	    columnNames.add("ISBN");
	    columnNames.add("Title");
	    columnNames.add("Category");
	    columnNames.add("Author");

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    

	    return new DefaultTableModel(data, columnNames){
	    	 public boolean isCellEditable(int row, int column)
	    	 {
	    	     return false;
	    	 }
	    };

	}
}
