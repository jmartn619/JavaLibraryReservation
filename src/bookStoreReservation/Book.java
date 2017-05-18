package bookStoreReservation;

import java.io.Serializable;
import java.sql.*;

public class Book implements Serializable {
	
	private long ISBN;
	private String Name;
	private String Category;
	private String Author;
	
	public Book(){
		
	}
	
	public Book(long iSBN, String name, String category, String author) {
		super();
		ISBN = iSBN;
		Name = name;
		Category = category;
		Author = author;
	}
	
	@Override
	public String toString() {
		return "[Title: " + Name + ", Category: " + Category + ", Author: " + Author + ", ISBN: " + ISBN + "]";
	}

	//Getters & Setters
	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}
	
	
	
}
