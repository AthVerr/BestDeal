import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Laptoplist")

public class Laptoplist extends HttpServlet {

	/* Laptop Page Displays all the Laptops and their Info */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //handle the request

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Laptop type Mac,Asus,Acer,HP,sony*/
				
		String name = null;
		String CategoryName = request.getParameter("maker"); //try to find the brand with the maker key word
		HashMap<String, Laptop> hm = new HashMap<String, Laptop>();//create a hashtable object that has the laptops 
		
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.laptops);//take the hashtable obj laptops that is in the class SaxParserDataStore
			name = "";
		}
		else  //search for the name in xml file to match 
		{
		  if(CategoryName.equals("mac"))
		  {
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				{
				if(entry.getValue().getRetailer().equals("mac"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "mac";
		  }
		  else if(CategoryName.equals("asus"))
		  {
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				{
				if(entry.getValue().getRetailer().equals("asus"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "asus";
		  }
		   else if(CategoryName.equals("acer"))
		  {
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				{
				if(entry.getValue().getRetailer().equals("acer"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "acer";
		  }
		  else if(CategoryName.equals("hp"))
		  {
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				{
				if(entry.getValue().getRetailer().equals("hp"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "hp";
		  }
		  else if(CategoryName.equals("sony"))
		  {
			for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				{
				if(entry.getValue().getRetailer().equals("sony"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "sony";
		  }
		 
		}

		/* Header, Left Navigation Bar are Printed.

		All the laptops and laptops information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Laptops</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Laptop> entry : hm.entrySet()){
			Laptop lap = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+lap.getName()+"</h3>");
			pw.print("<strong>"+ "$" + lap.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/laptops/"+lap.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}

}
