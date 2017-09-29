import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Tvlist")

public class Tvlist extends HttpServlet {

	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	/* Checks the tvs type  */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, TV> hm = new HashMap<String, TV>();

		if (CategoryName == null)	
		{
			hm.putAll(SaxParserDataStore.tvs);
			name = "";
		} 
		else 
		{
			if(CategoryName.equals("led")) 
			{	
				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("led"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="led";
			} 
			else if (CategoryName.equals("smart"))
			{
				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("smart"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "smart";
			} 
			else if (CategoryName.equals("ultra")) 
			{
				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("ultra"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}	
				name = "ultra";
			}
			else if (CategoryName.equals("outdoor"))
			{
				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("outdoor"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "outdoor";
			} 
			else if (CategoryName.equals("curved"))
			{
				for(Map.Entry<String,TV> entry : SaxParserDataStore.tvs.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("curved"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "curved";
			} 
	    }

		/* Header, Left Navigation Bar are Printed.

		*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " TV </a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, TV> entry : hm.entrySet()) {
			TV tv = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + tv.getName() + "</h3>");
			pw.print("<strong>" + tv.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/tvs/"
					+ tv.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='tvs'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
