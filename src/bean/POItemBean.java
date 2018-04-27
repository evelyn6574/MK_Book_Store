package bean;

public class POItemBean {

	private String PO_id;
	private String bid;
	private int quantity;

	public POItemBean(String PO_id, String bid, int quantity) {
		this.setPO_id(PO_id);
		this.setBid(bid);
		this.setQuantity(quantity);
	}

	/**
	 * @return the id
	 */
	public String getPO_id() {
		return PO_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setPO_id(String PO_id) {
		this.PO_id = PO_id;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid
	 *            the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the price
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}