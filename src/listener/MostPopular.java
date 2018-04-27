package listener;

import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import model.POData;

/**
 * Application Lifecycle Listener implementation class MostPopular
 *
 */
@WebListener
public class MostPopular implements HttpSessionAttributeListener {
	private POData podata = new POData();

    /**
     * Default constructor. 
     */
    public MostPopular() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	if (event.getName().equals("poupdated")) {
	    	try {
	    		//System.out.println("map:"+podata.retrieveMostPopular().toString());
				event.getSession().setAttribute("mostpopular", podata.retrieveMostPopular());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//System.out.println("//maxPrinciple: " + event.getSession().getAttribute("maxPrinciple"));
    }
	
}
