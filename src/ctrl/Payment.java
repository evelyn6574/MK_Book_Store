package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AddressDAO;
import DAO.PODAO;
import bean.AddressBean;
import bean.CustomerBean;
import bean.POBean;
import bean.POItemBean;
import model.POData;
import model.ShoppingCart;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String ORDERED = "ORDERED";
	private final static String PROCESSED = "PROCESSED";
	private final static String DENIED = "DENIED";
	private static int counter = 1;	// every 3rd order is denied.
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		POData POAccessor = (POData) request.getServletContext().getAttribute("POAccessor");
		AddressDAO adrsAccessor = (AddressDAO) request.getServletContext().getAttribute("adrsAccessor");
		
		
		// Billing info.
		String phone = request.getParameter("phone");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String adrs = request.getParameter("adrs");
		
		// Shipping info.
		String sfname = request.getParameter("sfname");
		String slname = request.getParameter("slname");
		String sphone = request.getParameter("sphone");
		String scountry = request.getParameter("scountry");
		String sstate = request.getParameter("sstate");
		String szip = request.getParameter("szip");
		String sadrs = request.getParameter("sadrs");
		
		// Get user object.
				CustomerBean user = (CustomerBean) request.getSession().getAttribute("user");
		// Get cart object.
				ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
	
		// Create addresses.
		AddressBean billing = new AddressBean(user.getUsername(), 
				adrs, state, country, zip, phone, "Billing");
		AddressBean shipping = new AddressBean(user.getUsername(), 
				sadrs, sstate, scountry, szip, sphone, "Shipping");
		
		// Create an order.
		String status = PROCESSED;
		request.getSession().setAttribute("poupdated", counter);
		System.out.println(request.getSession().getAttribute("poupdated"));
		if(counter % 3 == 0){
			status = DENIED;
		}
		POBean order = new POBean(user.getUsername(), status, shipping, slname, sfname);
		counter++;	// update counter.
		
		
		// Interact with database.
		try {
			adrsAccessor.updateAddr(billing);
			adrsAccessor.updateAddr(shipping);
			POAccessor.updatePO(order, cart.getBooks());
			System.out.println("Success!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Clear shopping cart.
		cart.clear();
		request.getSession().setAttribute("cart", cart);
		if(status == PROCESSED)
			request.setAttribute("message", "Order Successfully Completed.");
		else
			request.setAttribute("message", "Credit Card Authorization Failed.");
		request.setAttribute("referer", "/MK_BookStore/main.jspx");
		request.getRequestDispatcher("/message.jspx").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
