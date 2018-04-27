package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import DAO.*;
import bean.*;

public class BSData {
	
	private AddressDAO address;
	private BookDAO book;
	private CustomerDAO customer;
	private PODAO PO;
	private POItemDAO POItem;
	private ReviewDAO review;
	private VisitEventDAO VisitEvent;

	public BSData() {
		try {
			address = new AddressDAO();
			book = new BookDAO();
			customer = new CustomerDAO();
			PO = new PODAO();
			POItem = new POItemDAO();
			review = new ReviewDAO();
			VisitEvent = new VisitEventDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BookBean retrieveBook(String bookID) throws Exception {
		return book.retrieveBook(bookID);
	}
	
	public ArrayList<ReviewBean> retrieveAllReview(String bookID) throws Exception {
		return review.retrieveAllReview(bookID);
	}
	public void updateReview(ReviewBean review) throws SQLException{
		this.review.updateReview(review);
	}
	public int avgRate(String bookID) throws SQLException{
		return this.review.avgRate(bookID);
	}
	public void updateProfile(ProfileBean profile) throws SQLException {
		this.customer.updateProfile(profile);
	}
	public ProfileBean retrieveProfile(String username) throws SQLException {
		return this.customer.retrieveProfile(username);
	}
	public void updateCustomer(CustomerBean customer) throws SQLException {
		this.customer.updateCustomer(customer);
	}
	
	
	public void exportProductInfo(String productId, String filename)throws Exception{
	
		BookBean bookBean = book.retrieveBook(productId);
		
		BookWrapper bw = new BookWrapper(productId, bookBean);
		
		JAXBContext jc = JAXBContext.newInstance(bw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"MKBook.xsl\"?>");
		sw.write("\n");
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		String path = "C:/Malan/University/eclipse_workspace/Workspace/MK_BookStore/WebContent/XML";
		Schema schema = sf.newSchema(new File(path + "/MK_Book.xsd"));
		marshaller.setSchema(schema);
		marshaller.marshal(bw, new StreamResult(sw));
		System.out.println(sw.toString()); // for debugging
		System.out.println(filename);
		FileWriter fw = new FileWriter(filename);
		fw.write(sw.toString());
		fw.close();
	}
	
	public void exportOrders(String productId, String filename)throws Exception{
		
		BookBean bookBean = book.retrieveBook(productId);
		
		BookWrapper bw = new BookWrapper(productId, bookBean);
		
		JAXBContext jc = JAXBContext.newInstance(bw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"MK.xsl\"?>");
		sw.write("\n");
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		String path = "C:/Malan/University/eclipse_workspace/Workspace/MK_BookStore/WebContent/XML";
		Schema schema = sf.newSchema(new File(path + "/MK.xsd"));
		marshaller.setSchema(schema);
		marshaller.marshal(bw, new StreamResult(sw));
		System.out.println(sw.toString()); // for debugging
		/*FileWriter fw = new FileWriter(filename);
		fw.write(sw.toString());
		fw.close();*/
	}
}
