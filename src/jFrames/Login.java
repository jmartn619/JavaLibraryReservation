package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import bookStoreReservation.DatabaseConnect;
import bookStoreReservation.User;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.sql.*;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JButton btnLogin;
	private JButton btnCreateAccount;
	private JButton btnForgotpassword;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;
	
	protected User currentUser;
	
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		
		//Database Connection
		conn = DatabaseConnect.dbConnect();
		
		setTitle("Bookstore Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLogin = new JLabel("Bookstore Login");
		lblLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		
		JLabel lblUsername = new JLabel("Username:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ResultSet rs = null;
				PreparedStatement pst = null;
				
				try{
					String query = "select * from Users where userName=? and password=?";
					pst = conn.prepareStatement(query);
					pst.setString(1, textFieldUser.getText());
					pst.setString(2, passwordField.getText());
					
					rs = pst.executeQuery();
					
					
					int counter = 0;
					while(rs.next()){
						currentUser = new User(rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5));
						
						counter++;
					}
					if(counter == 1){
						currentUser.saveUser();
						JOptionPane.showMessageDialog(null, "Welcome to Library Management!");
						dispose();
						BookstoreHome homePage = new BookstoreHome();
						homePage.setVisible(true);
					}
					else if(counter > 1){
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					}
					else{
						JOptionPane.showMessageDialog(null, "Username and Password are incorrect.");
					}
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				finally{
					try{
						rs.close();
						pst.close();
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}

			}
		});
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/Images/1492425441_Apply.png")));
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountCreation accountCreation = new AccountCreation();
				accountCreation.setVisible(true);
			}
		});
		btnCreateAccount.setIcon(new ImageIcon(Login.class.getResource("/Images/1492425455_Database.png")));
		
		btnForgotpassword = new JButton("Forgot Password");
		btnForgotpassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPassword forgotPass = new ForgotPassword();
				forgotPass.setVisible(true);
				
			}
		});
		btnForgotpassword.setIcon(new ImageIcon(Login.class.getResource("/Images/1492425558_Help.png")));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/Images/1492424925_People.png")));
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Images/1492425027_Key.png")));
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnForgotpassword, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername)
										.addComponent(lblPassword))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passwordField)
										.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_1)))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCreateAccount)))
							.addContainerGap(100, Short.MAX_VALUE))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(174)
					.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(134))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblLogin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUsername)
							.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCreateAccount)
								.addComponent(btnLogin)))
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(btnForgotpassword))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
