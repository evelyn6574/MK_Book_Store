package bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="MKBook")
@XmlType(propOrder={"bid", "title", "price", "category"})
public class BookBean {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BookBean [bid=" + bid + ", title=" + title + ", price=" + price + ", category=" + category + "]";
	}

	private String bid;
	private String title;
	private int price;
	private String category;

	/**
	 * constructor set given attributes.
	 */
	public BookBean(String bid, String title, int price, String category) {
		this.setBid(bid);
		this.setTitle(title);
		this.setPrice(price);
		this.setCategory(category);
	}
	
	public BookBean() {
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}