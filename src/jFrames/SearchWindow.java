package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bookStoreReservation.Book;
import bookStoreReservation.DatabaseConnect;
import bookStoreReservation.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;;

public class SearchWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JComboBox comboBox;
	private JTable table;
	private TableRowSorter<TableModel> sorter;
	private JButton btnAddBook;
	
	protected User currentUser = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchWindow frame = new SearchWindow();
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
	public SearchWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnterSearchTerm = new JLabel("Enter Search Term:");
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select A Category...", "ISBN", "Book Title", "Category", "Author"}));
		comboBox.setSelectedIndex(0);
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Database Connection
		Connection conn = DatabaseConnect.dbConnect();
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textFieldSearch.getText().length()!=0 && comboBox.getSelectedIndex()!=0){
					String comboSelection = null;
					
					switch(comboBox.getSelectedIndex()){
						case 1: comboSelection = "pID";
								break;
						case 2: comboSelection = "pName";
								break;
						case 3: comboSelection = "pCategory";
								break;
						case 4: comboSelection = "pAuthor";
								break;
					}
					
					try{
						Statement statement = conn.createStatement();
						ResultSet rs = statement.executeQuery("SELECT pID, pName, pCategory, pAuthor FROM products WHERE "+comboSelection+" LIKE '%"
						+ textFieldSearch.getText() + "%'");
						table = new JTable(BrowseBooks.buildTableModel(rs));
						table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						scrollPane.setViewportView(table);
						
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Search is empty or a Category is not selected!");
				}
			}
		});
		
		btnAddBook = new JButton("Add to My Books!");
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
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEnterSearchTerm)
							.addGap(18)
							.addComponent(textFieldSearch, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
							.addComponent(btnSearch))
						.addComponent(btnAddBook))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterSearchTerm)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnAddBook)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		
		//Table Sorting Method
		sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		table.setAutoResizeMode(5);
	}


}
