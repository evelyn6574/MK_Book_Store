package DAO;

import java.sql.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.*;

public class POItemDAO {

	private DataSource ds;

	public POItemDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update POItem by giving PO_id from POBean and a map stored bid and
	 * quantity for each book
	 * 
	 * @param PO_id
	 * @param books
	 * @throws SQLException
	 */
	public void updateItem(String PO_id, Map<String, Integer> books) throws SQLException {
		Connection con = this.ds.getConnection();
		PreparedStatement p = null;
		for (String bid : books.keySet()) {
			String query = "INSERT INTO POITEM VALUES ('" + PO_id + "', '" + bid + "', " + books.get(bid) + ")";
			p = con.prepareStatement(query);
			p.executeUpdate();
		}
		p.close();
		con.close();
	}

	/**
	 * Return Map with BID and Quantity for each book
	 * 
	 * @param PO_id
	 * @return Map with BID and Quantity for each book
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveItemByID(String PO_id) throws SQLException {
		String query = "select * from POItem where PO_ID='" + PO_id + "'";
		Map<String, Integer> map = new HashMap<String, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("BID");
			int quantity = r.getInt("QUANTITY");
			map.put(bid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

	/**
	 * Return the ordered item information for a book in a particular Purchase
	 * order
	 * 
	 * @param POID
	 * @param bookID
	 * @return Return the ordered item information for a book in a particular
	 *         Purchase order
	 * @throws SQLException
	 */
	public POItemBean retrieveItem(String POID, String bookID) throws SQLException {
		String query = "select P.ID, P.BID, P.PRICE from POITEM P where P.ID = " + Integer.parseInt(POID)
				+ " and P.BID = '" + bookID + "'";
		POItemBean POItem = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String PO_id = r.getString("PO_ID");
			String bid = r.getString("BID");
			int quantity = r.getInt("QUANTITY");
			POItem = new POItemBean(PO_id, bid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return POItem;
	}

	/**
	 * Return books with sold quantity that has been ordered in this period (from
	 * start to end)
	 * 
	 * @param start
	 *            - starting date in String with format 'yyyymmdd', given date
	 *            is included
	 * @param end
	 *            - ending date in String with format 'yyyymmdd', given date is
	 *            included
	 * @return Return all book that has been ordered in this period with
	 *         quantity
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveOrderHistory(String start, String end) throws SQLException {
		String query = "select POItem.bid, sum(POItem.quantity) as \"QUANTITY\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' and POItem.PO_id >= (select min(PO_id) from POItem where PO_id like '"
				+ start + "%') and POItem.PO_id <= (select max(PO_id) from POItem where PO_id like '" + end
				+ "%') group by POItem.bid";
		Map<String, Integer> map = new HashMap<String, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("BID");
			int quantity = r.getInt("QUANTITY");
			map.put(bid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}
	
	/**
	 * Return the most popular book from the first order
	 * @return the most popular book
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveMostPopular() throws SQLException {
		String query = "select bid, Q from (select POItem.bid, sum(POItem.quantity) as \"Q\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.bid) as M where Q=(select max(Q) from (select POItem.bid, sum(POItem.quantity) as \"Q\" from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.bid) as M)";
		Map<String, Integer> map = new HashMap<String, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String bid = r.getString("BID");
			int quantity = r.getInt("Q");
			map.put(bid, quantity);
		}
		r.close();
		p.close();
		con.close();
		return map;
	}

}
