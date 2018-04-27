package bean;

public class CustomerBean {
	private String username;
	private String password;
	private ProfileBean profile;
	//private CreditInfoBean creditInfo;
	/**
	 * @param username
	 * @param password
	 * @param profile
	 */
	public CustomerBean(String username, String password, ProfileBean profile) {
		super();
		this.username = username;
		this.password = password;
		this.profile = profile;
		//this.creditInfo = creditInfo;
	}
	
	public CustomerBean(String username, String password){
		this(username, password, new ProfileBean(username));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the profile
	 */
	public ProfileBean getProfile() {
		return profile;
	}

	/**
	 * @return the creditInfo
	 *//*
	public CreditInfoBean getCreditInfo() {
		return creditInfo;
	}*/

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}

	/**
	 * @param creditInfo the creditInfo to set
	 *//*
	public void setCreditInfo(CreditInfoBean creditInfo) {
		this.creditInfo = creditInfo;
	}*/
	/**
	 * 
	 */
	public String toString() {
		return "Username: " + username + ", Password :" + password;
	}
	
	
}
