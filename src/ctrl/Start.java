package ctrl;


import java.io.*;
import java.sql.*;
import java.util.*;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.*;
import DAO.*;
import model.*;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_URL = "/main.jspx";

    /**
     * Default constructor. 
     */
    public Start() {
        // TODO Auto-generated constructor stub
    	super();
    }

    /**
    	 * @see Servlet#init(ServletConfig)
    */
    public void init(ServletConfig config) throws ServletException {
    	BookDAO bookAccessor = null;
    	CustomerDAO customerAccessor = null;
    	POData POAccessor = null;
    	AddressDAO adrsAccessor = null;
    	
		try {
			bookAccessor = new BookDAO();
	    	config.getServletContext().setAttribute("bookAccessor", bookAccessor);
	    	customerAccessor = new CustomerDAO();
	    	config.getServletContext().setAttribute("customerAccessor", customerAccessor);
	    	POAccessor = new POData();
	    	config.getServletContext().setAttribute("POAccessor", POAccessor);
	    	adrsAccessor = new AddressDAO();
	    	config.getServletContext().setAttribute("adrsAccessor", adrsAccessor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		try {
			String selectedCategory;
			String searchString;
			if(session.getAttribute("cart") == null){
				request.getSession().setAttribute("cart", new ShoppingCart());
			}
			
			if(session.getAttribute("categories") == null){
				ArrayList<String> categories = new BookDAO().retrieveCategories();
				request.getServletContext().setAttribute("categories", categories);
			}
			if(request.getParameter("searchbtn") != null)
			{
				searchString = request.getParameter("searchPattern");
				Map<String,BookBean> searchedBooks = new BookDAO().searchBook(searchString);
				request.setAttribute("searchedBooksMap", searchedBooks);
				request.setAttribute("searchpattern", searchString);
				request.getRequestDispatcher("/searchedView.jspx").forward(request, response);
				
			}
			else if(request.getParameter("viewByCategory") != null)
			{
				selectedCategory = request.getParameter("viewByCategory");
				Map<String, BookBean> bookInCategory = new BookDAO().retrieveCategory(selectedCategory);
				request.setAttribute("selectCategory", bookInCategory);
				request.setAttribute("categoryName", selectedCategory);
				request.getRequestDispatcher("/viewByCategory.jspx").forward(request, response);

			}
			else if(request.getParameter("viewAll") != null)
			{
				Map<String,BookBean> allBooks = new BookDAO().retrieveAll();
				request.setAttribute("selectCategory", allBooks);
				request.setAttribute("categoryName", "All Books");
				request.getRequestDispatcher("/viewByCategory.jspx").forward(request, response);
				
			}
			else{
				request.getRequestDispatcher(MAIN_URL).forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
