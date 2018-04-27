package model;

import java.sql.SQLException;
import java.util.*;

import DAO.BookDAO;
import bean.BookBean;

public class ShoppingCart {
	private Map<String, Integer> books; // Key is the bid of the book object and value 
										  // is the amount of the purchased book.
	private BookDAO bookAccessor;	// The data access object.
	
	
	/**
	 *  Create a empty shopping cart object.
	 * @throws ClassNotFoundException 
	 */
	public ShoppingCart() throws ClassNotFoundException {
		super();
		this.books = new HashMap<String, Integer>();
		this.bookAccessor = new BookDAO();
	}

	/**
	 * Add a book to cart.
	 * @param bid
	 */
	public void addBook(String bid, int quantity){
		if(!books.containsKey(bid))
			books.put(bid, quantity);
		else{
			int oldAmount = books.get(bid);
			books.put(bid, oldAmount + quantity);
		}
	}
	
	/**
	 * Drop a book from cart.
	 * @param bid
	 */
	public void dropBook(String bid){
		if(books.containsKey(bid)){
			books.remove(bid);
		}
	}
	
	/**
	 * In shopping cart page, use this method to update the amount of a book.
	 * @param bid book id of the book to be added or dropped.
	 * @param quantity the target amount of the book.
	 */
	public void updateBookQuantity(String bid, int quantity){
		books.put(bid, quantity);
	}

	/**
	 * @return number of books in cart.
	 */
	public int getSize(){
		int totalSize = 0;
		for(Integer temp : books.values()){
			totalSize += temp;
		}
		return totalSize;
	}
	
	/**
	 * @return the total price of all items in cart.
	 */
	public int getPrice(){
		int totalPrice = 0;
		for (Map.Entry<String, Integer> entry : books.entrySet()){
			BookBean book;
			try {
				book = bookAccessor.retrieveBook(entry.getKey());
				totalPrice += book.getPrice() * entry.getValue();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalPrice;
	}
	
	/**
	 * 
	 * @param bid book id of a book.
	 * @return the unit price * quantity of the given book in cart.
	 */
	public int getSubtotal(String bid){
		int totalPrice = 0;
		BookBean book;
		try {
			if(books.containsKey(bid)){
				book = bookAccessor.retrieveBook(bid);
				totalPrice = book.getPrice() * books.get(bid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalPrice;
	}

	/**
	 * @return the books
	 */
	public Map<String, Integer> getBooks() {
		return books;
	}
	
	public BookBean getBook(String bid){
		BookBean result = null;
		if(books.containsKey(bid))
			try {
				result = bookAccessor.retrieveBook(bid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
			
	}
	
	public String getTitle(String bid){
		return getBook(bid).getTitle();
	}
	
	public int getUnitPrice(String bid){
		return getBook(bid).getPrice();
	}

	public String getCategory(String bid){
		return getBook(bid).getCategory();
	}
	public void clear(){
		this.books.clear();
	}
	
}
