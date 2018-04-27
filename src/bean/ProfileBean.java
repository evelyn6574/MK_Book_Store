package bean;

public class ProfileBean {
	private String username;
	private String fname;
	private String lname;
	private String email;
	/**
	 * @param username
	 * @param fname
	 * @param lname
	 * @param email
	 */
	public ProfileBean(String username, String fname, String lname, String email) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	
	public ProfileBean(String username){
		this(username, "default", "default", "default");
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the first name
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @return the last name
	 */
	public String getLname() {
		return lname;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param fname the first name to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @param lname the last name to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 */
	public String toString() {
		 return "Username: " + username 
				 + "\nFirst Name: " + fname
				 + "\nLast Name: " + lname
				 + "\nEmail: " + email;
	}
}
