package bookStoreReservation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String firstName;
	private String lastName;
	private String eMail;
	
	
	protected ArrayList<Book> myBooks = new ArrayList<Book>();
	
	public User(){
		
	}
	
	public User(String user, String first, String last, String email){
		userName = user;
		firstName = first;
		lastName = last;
		eMail = email;
	}

	public void addBook(Book book){
		getMyBooks().add(book);
	}
	
	public String printBookList(){
		StringBuilder str = new StringBuilder();
		Iterator itr = myBooks.iterator();
		
		while(itr.hasNext()){
			str.append(itr.next());
		}
		return str.toString();
	}
	
	public void saveUser(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"));
			oos.writeObject(this);
			oos.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static User readUser(){
		User currentUser = null;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"));
			currentUser = (User) ois.readObject();
			ois.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return currentUser;
	
	}
	
	//Getters
	
	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String geteMail() {
		return eMail;
	}

	
	//toString
	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + eMail
				+ "]";
	}

	public ArrayList<Book> getMyBooks() {
		return myBooks;
	}

	public void setMyBooks(ArrayList<Book> myBooks) {
		this.myBooks = myBooks;
	}


}
