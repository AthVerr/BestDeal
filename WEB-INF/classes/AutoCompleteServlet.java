import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/AutoCompleteServlet")

public class AutoCompleteServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
				
		HttpSession session = request.getSession(true);	 
 
try{	
		String action = request.getParameter("action");
		String searchId   = request.getParameter("searchId");
		StringBuffer stringBuffer = new StringBuffer();
		boolean namesAdded = false;
		
		if(action.equals("complete"))
		{
			 AjaxUtility ajaxUtility = new AjaxUtility();
			stringBuffer = ajaxUtility.readData(searchId);//read data from ajaxUtility method
			
			if(stringBuffer != null || !stringBuffer.equals(""))
			{
				namesAdded = true;
			}
			if(namesAdded)
			{
				response.setContentType("text/xml");
				response.getWriter().write("<products>"+stringBuffer.toString()+"</products>");
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
 }

}
