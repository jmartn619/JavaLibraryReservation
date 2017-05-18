package jFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookstoreHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookstoreHome frame = new BookstoreHome();
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
	public BookstoreHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLibraryMangement = new JLabel("Library Mangement");
		lblLibraryMangement.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		JLabel lblNewLabel = new JLabel("Library");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		JButton buttonBrowse = new JButton("");
		buttonBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BrowseBooks browse = new BrowseBooks();
				browse.setVisible(true);
			}
		});
		buttonBrowse.setIcon(new ImageIcon(BookstoreHome.class.getResource("/Images/1492855039_Book_books_education_library_reading_open_book_study.png")));
		
		JButton buttonBooks = new JButton("");
		buttonBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyBooks myBooks = new MyBooks();
				myBooks.setVisible(true);
			}
		});
		buttonBooks.setIcon(new ImageIcon(BookstoreHome.class.getResource("/Images/1492855034_Book_contacts_library_notebook_bookmark_business_phone_book.png")));
		
		JButton buttonSearch = new JButton("");
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchWindow search = new SearchWindow();
				search.setVisible(true);
			}
		});
		buttonSearch.setIcon(new ImageIcon(BookstoreHome.class.getResource("/Images/1492855107_edit-find.png")));
		
		JLabel lblBrowse = new JLabel("Browse");
		lblBrowse.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblMyBooks = new JLabel("My Books");
		lblMyBooks.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		JButton buttonSettings = new JButton("");
		buttonSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings settingsWindow = new Settings();
				settingsWindow.setVisible(true);
			}
		});
		buttonSettings.setIcon(new ImageIcon(BookstoreHome.class.getResource("/Images/1492855127_Settings.png")));
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(184)
							.addComponent(lblLibraryMangement))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(26, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel)
										.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(buttonSettings)
										.addComponent(buttonBrowse, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(6)
											.addComponent(lblSettings)))
									.addGap(65))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblBrowse)
									.addGap(79)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblMyBooks, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(buttonBooks))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(78)
									.addComponent(buttonSearch))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(94)
									.addComponent(lblSearch)))))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLibraryMangement)
							.addGap(80)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(buttonBrowse, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonSearch, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonBooks, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addGap(29)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMyBooks, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearch)
								.addComponent(lblBrowse))
							.addGap(52)
							.addComponent(buttonSettings)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSettings))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addComponent(lblUser, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
