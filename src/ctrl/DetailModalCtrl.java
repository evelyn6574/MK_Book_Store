package ctrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BookBean;
import bean.CustomerBean;
import bean.ReviewBean;
import model.BSData;
import model.ShoppingCart;

/**
 * Servlet implementation class DetailModalCtrl
 */
@WebServlet("/DetailModalCtrl")
public class DetailModalCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailModalCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSData ba = new BSData();
		HttpSession session = request.getSession();
		if(request.getParameter("bid") != null){
			try {
				String bid = (String) request.getParameter("bid");
				request.setAttribute("dm_book", ba.retrieveBook(bid));
				request.setAttribute("reviews", ba.retrieveAllReview(bid));
				BookBean book = ba.retrieveBook(bid);
				ArrayList<ReviewBean> reviews = ba.retrieveAllReview(bid);
				response.setHeader("reviews_size", reviews.size() +"");
				int i = 0;
				
				for(ReviewBean review : reviews){
					response.addHeader("review" + i, review.getRating() + ","
							+ review.getReview() + "," + review.getUsername());
					i++;
				}
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(request.getParameter("customerreview") != null && request.getParameter("rating") != null){
			String review = (String) request.getParameter("customerreview");
			String bid = (String) request.getParameter("submit_bid");
			int rating = Integer.parseInt(request.getParameter("rating"));
			CustomerBean user = (CustomerBean) session.getAttribute("user");
			if(user == null){
				;
			}
			else{
				try {
					ba.updateReview(new ReviewBean(bid, user.getUsername(), rating, review));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//request.getRequestDispatcher("/main.jspx").forward(request, response);
		}
		request.getRequestDispatcher("/main.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
