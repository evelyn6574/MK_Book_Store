package ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import bean.*;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/Analytics")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private POData POAccessor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve DAOs from context scope.
		POAccessor = new POData();
		
		//Current Date to get report period
		Date date = new Date();
		SimpleDateFormat YMformate = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat YMDformate = new SimpleDateFormat("yyyyMMdd");
		
		try {
			//UC A1: Set report with book id and quantity as attribute.
			request.setAttribute("report", POAccessor.retrieveOrderHistory(YMformate.format(date)+"01", YMDformate.format(date)));
			System.out.println(request.getAttribute("report"));
			/*
			Map<String, Integer> report = POAccessor.retrieveOrderHistory(YMformate.format(date)+"01", YMDformate.format(date));
			for (String bid: report.keySet())
				System.out.println("bid:"+bid+", quantity:"+report.get(bid));
			*/
			//UC A3: Set all PO records as attribute.
			request.setAttribute("anonymizedpo", POAccessor.retrieveAllPO());
			/*
			Map<POBean, Map<String, Integer>> map = POAccessor.retrieveAllPO();
			for (POBean key : map.keySet()) {
				System.out.println("***" + key.toString());
				for (String bid : map.get(key).keySet()) {
					System.out.println("book: " + bid + ", quantity: " + map.get(key).get(bid));
				}
			}
			*/
			//Forward to page
			request.getRequestDispatcher("/analytics.jspx").forward(request, response);
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
