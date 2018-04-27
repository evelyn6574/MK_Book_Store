package model;

import java.sql.SQLException;
import java.util.*;

import DAO.*;
import bean.*;

public class POData {

	private PODAO po;
	private POItemDAO poitem;

	public POData() {
		try {
			po = new PODAO();
			poitem = new POItemDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Return Map<POBean, Map<String, Integer>>, where POBean stores information
	 * for every PO for this user, inner map Map<String, Integer> stores each
	 * book and quantity user ordered in this PO.
	 * 
	 * @param username
	 * @return Map<POBean, Map<String, Integer>>
	 * @throws Exception
	 */
	public Map<POBean, Map<String, Integer>> retrievePO(String username) throws Exception {
		Map<String, POBean> pobean = po.retrievePOByUser(username);
		Map<POBean, Map<String, Integer>> result = new HashMap<POBean, Map<String, Integer>>();
		for (String key : pobean.keySet()) {
			result.put(pobean.get(key), poitem.retrieveItemByID(key));
		}
		return result;
	}
	
	/**
	 * Return Map<POBean, Map<String, Integer>>, where POBean stores information
	 * for every PO for all users, inner map Map<String, Integer> stores each
	 * book and quantity user ordered in this PO.
	 * 
	 * @param username
	 * @return Map<POBean, Map<String, Integer>>
	 * @throws Exception
	 */
	public Map<POBean, Map<String, Integer>> retrieveAllPO() throws Exception {
		Map<String, POBean> pobean = po.retrieveAll();
		Map<POBean, Map<String, Integer>> result = new HashMap<POBean, Map<String, Integer>>();
		for (String key : pobean.keySet()) {
			result.put(pobean.get(key), poitem.retrieveItemByID(key));
		}
		return result;
	}

	/**
	 * Update a new purchase order with created POBean and map in shopping cart
	 * 
	 * @param pobean
	 *            - pobean that need to be stored
	 * @param books
	 *            - from shopping cart, ShoppingCart.getBooks()
	 * @return PO_id for pobean
	 * @throws Exception
	 */
	public String updatePO(POBean pobean, Map<String, Integer> books) throws Exception {
		po.updatePO(pobean);
		poitem.updateItem(pobean.getPO_id(), books);
		return pobean.getPO_id();
	}

	/**
	 * Return a Purchase Order POBean with PO_id
	 * 
	 * @param PO_id
	 * @return a Purchase Order POBean with PO_id, return null is PO_id is not
	 *         found
	 * @throws SQLException
	 */
	public POBean retrievePOByID(String PO_id) throws Exception {
		return po.retrievePOByID(PO_id);
	}

	/**
	 * Return the user's past purchase orders, ordered from recent to past.
	 * 
	 * @param username
	 * @return Return the user's past purchase orders, null is user did
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByUser(String username) throws Exception {
		return po.retrievePOByUser(username);
	}

	/**
	 * Return all purchase order
	 * 
	 * @return all purchase order
	 * @throws SQLException
	 */
	public Map<String, POBean> retrieveAll() throws SQLException {
		return po.retrieveAll();
	}

	/**
	 * Return purchase orders within the date range from to until
	 * 
	 * @param start
	 *            - starting date in String with format 'yyyymmdd', given date
	 *            is included
	 * @param end
	 *            - ending date in String with format 'yyyymmdd', given date is
	 *            included
	 * @return purchase orders within the date range from to until, null if no
	 *         order within those date
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByDate(String start, String end) throws SQLException {
		return po.retrievePOByDate(start, end);
	}

	/**
	 * Return purchase orders that is in the status given
	 * 
	 * @param status
	 *            - "ORDERD", "PROCESSED" or "DENIED"
	 * @return purchase orders within the date range from to until, null if no
	 *         order within those date
	 * @throws SQLException
	 */
	public Map<String, POBean> retrievePOByStatus(String status) throws SQLException {
		return po.retrievePOByStatus(status);
	}

	/**
	 * Return Map with BookBean and Quantity for each book
	 * 
	 * @param PO_id
	 * @return Map with BookBean and Quantity for each book
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveItemByID(String PO_id) throws SQLException {
		return poitem.retrieveItemByID(PO_id);
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
		return poitem.retrieveItem(POID, bookID);
	}

	/**
	 * Return all book with quantity that has been ordered in this period (from
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
		return poitem.retrieveOrderHistory(start, end);
	}
	
	/**
	 * Return the most popular book from the first order
	 * @return the most popular book
	 * @throws SQLException
	 */
	public Map<String, Integer> retrieveMostPopular() throws SQLException {
		return poitem.retrieveMostPopular();
	}

}
