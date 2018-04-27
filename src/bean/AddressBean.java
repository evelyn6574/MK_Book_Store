package bean;

public class AddressBean {
	
	private String username;
	private String street;
	private String province;
	private String country;
	private String zip;
	private String phone;
	private String type;
	
	public static String BILLING = "Billing";
	public static String SHIPPING = "Shipping";

	public AddressBean(String username, String street, String province, String country, String zip, String phone, String type) {
		this.setUsername(username);
		this.setStreet(street);
		this.setProvince(province);
		this.setCountry(country);
		this.setZip(zip);
		this.setPhone(phone);
		this.setType(type);
	}
	
	/**
	 * @return the id
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param id the id to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 */
	public String toString() {
		return "Username: " + username
				+ "\nStreet: " + street
				+ "\nProvince: " + province
				+ "\nCountry: " + country
				+ "\nZip: " + zip
				+ "\nPhone: " + phone
				+ "\nType: " + type;
	}

}