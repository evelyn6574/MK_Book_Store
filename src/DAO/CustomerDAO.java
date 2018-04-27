package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.*;

public class CustomerDAO {

	private DataSource ds;
	private AddressDAO addrDAO = new AddressDAO();

	public CustomerDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Giving an username return a CustomerBean with the username and stored
	 * password if username exist. Return null CostomerBean if username is not
	 * exist in database
	 * 
	 * @param username
	 * @return CustomerBean with username
	 * @throws SQLException
	 */
	public CustomerBean retrieveCustomer(String username) throws SQLException {
		String query = "SELECT * FROM Customer WHERE username = '" + username + "'";
		CustomerBean customer = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next())
			customer = new CustomerBean(username, r.getString("PASSWORD"), retrieveProfile(username));
					//retrieveCreditInfo(username));// Profile and CreditInfo will
													// be null initially
		r.close();
		p.close();
		con.close();
		return customer;
	}

	/**
	 * Check if the username and password is correct
	 * 
	 * @param username
	 * @param password
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean successLogin(String username, String password) throws SQLException {
		String query = "SELECT * FROM Customer WHERE username = '" + username + "'";
		boolean success = false;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next() && r.getString("PASSWORD").equals(password))
			success = true;
		r.close();
		p.close();
		con.close();
		return success;
	}

	/**
	 * Update a new customer or update an exist customer's password with given
	 * information
	 * 
	 * @param customer
	 * @throws SQLException
	 */
	public void updateCustomer(CustomerBean customer) throws SQLException {
		String queryget = "SELECT * FROM Customer WHERE username = '" + customer.getUsername() + "'";
		String queryadd = "INSERT INTO Customer VALUES ('" + customer.getUsername() + "', '" + customer.getPassword()
				+ "')";
		String queryupdate = "UPDATE CUSTOMER SET \"PASSWORD\"='" + customer.getPassword() + "' WHERE \"USERNAME\"='"
				+ customer.getUsername() + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(queryget);
		ResultSet r = p.executeQuery();
		if (r.next()) { // user exist, update password, user will use exist
						// CustomerBean to call this method, so to update to
						// profile
			p = con.prepareStatement(queryupdate);
			p.executeUpdate();
		} else { // user not exist, insert new instance
			p = con.prepareStatement(queryadd);
			p.executeUpdate();
			updateProfile(customer.getProfile());
			//updateCreditInfo(customer.getCreditInfo());
		}
		r.close();
		p.close();
		con.close();
	}

	/**
	 * Giving an username delete the corresponding database instance if it is
	 * exist in database
	 * 
	 * @param username
	 * @throws SQLException
	 */
	public void deleteCustomer(String username) throws SQLException {
		String query = "DELETE FROM Customer WHERE username = '" + username + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.executeUpdate();
		p.close();
		con.close();
	}

	/**
	 * Giving an username return a CreditInfoBean with the username and stored
	 * credit informations if username exist. Return null CreditInfoBean if
	 * username is not exist in database
	 * 
	 * @param username
	 * @return a CreditInfoBean with the username and stored credit informations
	 * @throws SQLException
	 *//*
	public CreditInfoBean retrieveCreditInfo(String username) throws SQLException {
		String query = "select * from CreditInfo where username = '" + username + "'";
		CreditInfoBean credit = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next() && !r.getString("CARDHOLDER").equals("default"))
			credit = new CreditInfoBean(username, r.getInt("CARDTYPE"), r.getString("CARDHOLDER"),
					r.getString("CARDNUMBER"), r.getInt("EXPIREM"), r.getInt("EXPIREY"),
					addrDAO.retrieveAddrByID(r.getInt("A_ID")));
		r.close();
		p.close();
		con.close();
		return credit;
	}*/

	/**
	 * Update a new registered customer credit information or update an exist
	 * customer's credit information
	 * 
	 * @param credit
	 * @throws SQLException
	 *//*
	public void updateCreditInfo(CreditInfoBean credit) throws SQLException {
		String queryget = "SELECT * FROM CreditInfo WHERE username = '" + credit.getUsername() + "'";
		String queryadd = "INSERT INTO CreditInfo (username, cardType, CardHolder, CardNumber, expireM, expireY) VALUES ('"
				+ credit.getUsername() + "', " + credit.getCardType() + ", '" + credit.getCardHolder() + "', '"
				+ credit.getCardNumber() + "', " + credit.getExpireM() + ", " + credit.getExpireY() + ")";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(queryget);
		ResultSet r = p.executeQuery();
		if (r.next()) { // user exist, update password
			int a_id = addrDAO.updateAddr(credit.getbAddress());
			String queryupdate = "UPDATE CreditInfo SET \"CARDTYPE\"=" + credit.getCardType() + ", \"CARDHOLDER\"='"
					+ credit.getCardHolder() + "', \"CARDNUMBER\"='" + credit.getCardNumber() + "', \"EXPIREM\"="
					+ credit.getExpireM() + ", \"EXPIREY\"=" + credit.getExpireY() + ", \"A_ID\"="
					+ a_id + " WHERE \"USERNAME\"='" + credit.getUsername()
					+ "'";
			p = con.prepareStatement(queryupdate);
			p.executeUpdate();
		} else { // user not exist, insert new instance
			p = con.prepareStatement(queryadd);
			p.executeUpdate();
		}
		r.close();
		p.close();
		con.close();
	}*/

	/**
	 * Giving an username return a ProfileBean with the username and stored user
	 * profile if username exist. Return null ProfileBean if username is not
	 * exist in database
	 * 
	 * @param username
	 * @return ProfileBean with the username and stored user profile
	 * @throws SQLException
	 */
	public ProfileBean retrieveProfile(String username) throws SQLException {
		String query = "select * from Profile where username = '" + username + "'";
		ProfileBean profile = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next()) {
			if (!r.getString("FNAME").equals("default"))// if profile is
														// default, return null
														// profileBean
				profile = new ProfileBean(username, r.getString("FNAME"), r.getString("LNAME"), r.getString("EMAIL"));
			// servlet will not use profile to get shipping address
		}
		r.close();
		p.close();
		con.close();
		return profile;
	}

	/**
	 * Update a new registered customer profile or update an exist customer's
	 * profile
	 * 
	 * @param profile
	 * @throws SQLException
	 */
	public void updateProfile(ProfileBean profile) throws SQLException {
		String queryget = "SELECT * FROM Profile WHERE username = '" + profile.getUsername() + "'";
		String queryadd = "INSERT INTO Profile VALUES ('" + profile.getUsername() + "', '" + profile.getFname() + "', '"
				+ profile.getLname() + "', '" + profile.getEmail() + "')";
		String queryupdate = "UPDATE Profile SET \"FNAME\"='" + profile.getFname() + "', \"LNAME\"='"
				+ profile.getLname() + "', \"EMAIL\"='" + profile.getEmail() + "' WHERE \"USERNAME\"='"
				+ profile.getUsername() + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(queryget);
		ResultSet r = p.executeQuery();
		if (r.next()) { // user exist, update password
			p = con.prepareStatement(queryupdate);
			p.executeUpdate();
		} else { // user not exist, insert new instance
			p = con.prepareStatement(queryadd);
			p.executeUpdate();
		}
		r.close();
		p.close();
		con.close();
	}

}
