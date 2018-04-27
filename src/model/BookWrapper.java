package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bean.*;

@XmlRootElement(name="MKBook")

public class BookWrapper {
	
	@XmlAttribute(name="productId")
	private String productId;
	
	@XmlElement(name="bookBean")
	private BookBean book;

	public BookWrapper(String productId, BookBean book) {
		this.productId = productId;
		this.book = book;
	}
	
	public BookWrapper() {
		// TODO Auto-generated constructor stub
	}

}
