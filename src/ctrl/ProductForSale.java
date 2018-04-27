package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BSData;

/**
 * Servlet implementation class ProductForSale
 */
@WebServlet("/ProductForSale")
public class ProductForSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BSData bsdata;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductForSale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = request.getRequestedSessionId() + ".xml";//get session id as filename
		try {
			bsdata = new BSData();
			System.out.println(this.getServletContext().getRealPath("XML/") + filename);
			bsdata.exportProductInfo("b001", "C:/Malan/University/eclipse_workspace/Workspace/MK_BookStore/WebContent/XML/" + filename);
			//this.getServletContext().getRealPath(filename)
		} catch (Exception e) {
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
