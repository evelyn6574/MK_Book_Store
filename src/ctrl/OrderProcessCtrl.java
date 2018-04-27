package ctrl;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BookBean;
import model.ShoppingCart;

/**
 * Servlet implementation class OrderProcessCtrl
 */
@WebServlet("/OrderProcessCtrl")
public class OrderProcessCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if(request.getParameter("addToCart") != null){
			String bid = request.getParameter("addToCart");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.addBook(bid, quantity);
		}
		if(cart.getSize() != 0 && session.getAttribute("total") == null 
				&& session.getAttribute("booksInCart") == null){
			session.setAttribute("total", cart.getPrice());
			session.setAttribute("booksInCart", cart.getBooks());
		}
		if(request.getParameter("delete") != null){
			String param = (String) request.getParameter("delete");
			cart.dropBook(param);
		}
		if(request.getParameter("quantity") != null && request.getParameter("bid") != null){	
			int quantity = Integer.parseInt(((String) request.getParameter("quantity")));
			String bid = (String) request.getParameter("bid");
			cart.updateBookQuantity(bid, quantity);
			response.setHeader("total", 
					"<b>Total: $" + cart.getPrice() + ".00</b></td><br/>");
			response.setHeader("subtotal", 
					"$" + cart.getSubtotal(bid) + ".00</td><br/>");
			response.setHeader("size", cart.getSize() +"");
		}
		request.getRequestDispatcher("/Cart.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
