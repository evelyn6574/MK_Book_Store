package DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.VisitEventBean;

public class VisitEventDAO {

	private DataSource ds;

	public VisitEventDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public VisitEventBean retrieve(String visitDay, String bookID) throws SQLException {
		String query = "select V.DAY, V.BID, B.EVENTTYPE from VISITEVENT V where V.DAY = '" + visitDay + "' and V.BID = '" + bookID + "'";
		VisitEventBean ve =	null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String day = r.getString("DAY");
			String bid = r.getString("BID");
			String eventtype = r.getString("EVENTTYPE");
			ve = new VisitEventBean(day, bid, eventtype);
		}
		r.close();
		p.close();
		con.close();
		return ve;
	}

}
