package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AddressDAO;
import DAO.CustomerDAO;
import bean.AddressBean;
import bean.CustomerBean;
import bean.POBean;
import model.ShoppingCart;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AddressDAO adrsAccessor = (AddressDAO) request.getServletContext().getAttribute("adrsAccessor");
		CustomerDAO customerAccessor = (CustomerDAO) request.getServletContext().getAttribute("customerAccessor");
		
		// Retrieve username and password.
		String username = request.getParameter("usr");
		String password = request.getParameter("password");
		
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
		
		try {
			// Create user object and store it in session scope.
			if(customerAccessor.retrieveCustomer(username) == null){
				CustomerBean user = new CustomerBean(username, password);
				request.getSession().setAttribute("user", user);
				
				// Store this customer into database.
				customerAccessor.updateCustomer(user);
		
				// Create addresses.
				AddressBean billing = new AddressBean(user.getUsername(), 
						adrs, state, country, zip, phone, "Billing");
				AddressBean shipping = new AddressBean(user.getUsername(), 
						sadrs, sstate, scountry, szip, sphone, "Shipping");
			
				// Interact with database.
				adrsAccessor.updateAddr(billing);
				adrsAccessor.updateAddr(shipping);	
				request.setAttribute("message", "Successfully registered!");
				request.setAttribute("referer", "/MK_BookStore/main.jspx");
			}
			else{
				request.setAttribute("message", "User already exists! Please select a new username!");
				request.setAttribute("referer", "/MK_BookStore/register.jspx");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
