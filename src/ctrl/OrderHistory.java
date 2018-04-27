package ctrl;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;
import model.*;
import bean.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/OrderHistory")
public class OrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve DAOs from context scope.
		POData POAccessor = (POData) request.getServletContext().getAttribute("POAccessor");
		CustomerDAO customerAccessor = 
				(CustomerDAO) request.getServletContext().getAttribute("customerAccessor"); 
		
		// Current logged in user.
		CustomerBean user = (CustomerBean) request.getSession().getAttribute("user");
		
		try {
				request.setAttribute("sample", POAccessor.retrievePO(user.getUsername()));
				// If user wants to change their profile.
				if(request.getParameter("editFName") != null){
					
					// Retrieve all parameters from query string.
					String newPassword = request.getParameter("newPassword");
					String editFName = request.getParameter("editFName");
					String editLName = request.getParameter("editLName");
					String editemail = request.getParameter("editemail");
					
					// Create new profile object.
					ProfileBean profile = new ProfileBean(user.getUsername(), editFName, editLName, editemail);
					
					// If user wanted to change their password, reset password.
					if(newPassword != null){
						user.setPassword(newPassword);
					}
					
					// Update user info in database and updata user object in session scope.
					user.setProfile(profile);
					customerAccessor.updateCustomer(user);
					request.getSession().setAttribute("user", user);
					
					request.setAttribute("message", "Successfully updated your account information!");
					request.setAttribute("referer", request.getHeader("referer"));
					//System.out.println(request.getHeader("referer"));
					request.getRequestDispatcher("/message.jspx").forward(request, response);
				}
				else
					request.getRequestDispatcher("/myAccount.jspx").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
