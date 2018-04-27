package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class POBean {

	private String PO_id;
	private String username;
	private String status;
	private AddressBean address;
	private String PO_date;
	private String lname;
	private String fname;

	private static SimpleDateFormat idf = new SimpleDateFormat("yyyyMMddHHmmSS");
	private static SimpleDateFormat datef = new SimpleDateFormat("yyyyMMdd");

	public final static String ORDERED = "ORDERED";
	public final static String PROCESSED = "PROCESSED";
	public final static String DENIED = "DENIED";

	public POBean(String PO_id, String username, String status, AddressBean address, String PO_date, String lname,
			String fname) {
		this.PO_id = PO_id;
		this.username = username;
		this.status = status;
		this.address = address;
		this.PO_date = PO_date;
		this.lname = lname;
		this.fname = fname;
	}

	public POBean(String username, String status, AddressBean address, String lname, String fname) {
		this(idf.format(new Date()), username, status, address, datef.format(new Date()), lname, fname);
	}

	/**
	 * @return the PO_id
	 */
	public String getPO_id() {
		return PO_id;
	}

	/**
	 * @param PO_id
	 *            the PO_id to set
	 */
	public void setPO_id(String PO_id) {
		this.PO_id = PO_id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param fname
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the address
	 */
	public AddressBean getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(AddressBean address) {
		this.address = address;
	}

	/**
	 * @return the PO_date
	 */
	public String getPO_date() {
		return PO_date;
	}

	/**
	 * @param PO_date
	 *            the PO_date to set
	 */
	public void setPO_date(String PO_date) {
		this.PO_date = PO_date;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String toString() {
		return "PO_id: " + PO_id
				+ "\nusername: " + username
				+ "\nstatus" + status
				+ "\naddress: " +  address.toString()
				+ "\nPO_date: " +  PO_date
				+ "\nlname: " +   lname
				+ "\nfname: " +   fname;
	}

}