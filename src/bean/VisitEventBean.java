package bean;

public class VisitEventBean {
	
	private String day;
	private String bid;
	private String evettype;

	public VisitEventBean(String day, String bid, String eventtype) {
		this.setDay(day);
		this.setBid(bid);
		this.setEvettype(eventtype);
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the bid
	 */
	public String getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(String bid) {
		this.bid = bid;
	}

	/**
	 * @return the evettype
	 */
	public String getEvettype() {
		return evettype;
	}

	/**
	 * @param evettype the evettype to set
	 */
	public void setEvettype(String evettype) {
		this.evettype = evettype;
	}

}