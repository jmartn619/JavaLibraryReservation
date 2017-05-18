package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bookStoreReservation.DatabaseConnect;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AccountCreation extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPassword;
	private JTextField textFieldUsername;
	private JTextField textFieldEmail;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountCreation frame = new AccountCreation();
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
	public AccountCreation() {
		//cONNECT to database
		Connection conn = DatabaseConnect.dbConnect();
		setTitle("Account Creation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password:");
		
		JLabel lblSecurityQuestion = new JLabel("First Name:");
		
		JLabel lblSecurityAnswer = new JLabel("Last Name:");
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try{
					//String query = "INSERT INTO Users " +  "VALUES('csc430', 'password', 'Computer', 'Science', 'csi@cuny.edu')";
					
						String username = textFieldUsername.getText();
						String password = textFieldPassword.getText();
						String firstName = textFieldFirstName.getText();
						String lastName = textFieldLastName.getText();
						String eMail = textFieldEmail.getText();
					
						Statement statement = conn.createStatement();
						statement.executeUpdate("INSERT INTO Users " + "VALUES ('"+username+"', '"+password+"', '"+firstName+"', '"+lastName+"', '"+eMail+"')");

						JOptionPane.showMessageDialog(null, "Account has been created successfully!");
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnCreateAccount.setIcon(new ImageIcon(AccountCreation.class.getResource("/Images/1492425441_Apply.png")));
		
		JLabel lblEmail = new JLabel("E-Mail:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(AccountCreation.class.getResource("/Images/1492424908_Back.png")));
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblReenterPassword)
								.addComponent(lblPassword)
								.addComponent(lblUsername)
								.addComponent(lblSecurityQuestion)
								.addComponent(lblSecurityAnswer)
								.addComponent(lblEmail))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldLastName)
								.addComponent(textFieldFirstName)
								.addComponent(textFieldEmail)
								.addComponent(textFieldPassword)
								.addComponent(textFieldUsername, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addComponent(btnCreateAccount))
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textFieldUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblReenterPassword)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSecurityQuestion)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSecurityAnswer)
								.addComponent(textFieldLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(textFieldFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(btnCreateAccount)
					.addGap(19))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(285, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
