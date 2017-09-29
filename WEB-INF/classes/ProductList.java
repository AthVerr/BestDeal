

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





@WebServlet("/ProductList")

public class ProductList extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayProduct(request, response, pw, false);
	}
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
displayProduct(request, response, pw, true);				
  }	
  
  
  	
	protected void displayProduct(HttpServletRequest request,HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");


		pw.print("<form name ='ProductList' action='SubmitProduct' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>Add a product:</a>");
		pw.print("</h2><div class='entry'>");

		
	pw.println("<form action='ProductList'>");
  	pw.println("<h2>Add your product</h2>");
  	pw.println("<table>");
    pw.println("<tr>");
  	pw.println("<td> Product ID</td>");
    pw.println("<td> <input type='text' name='productId'></td>");
  	pw.println("</tr>");
	pw.println("<tr>");
  	pw.println("<td> Product Name</td>");
    pw.println("<td> <input type='text' name='productName'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Product Price</td>");
    pw.println("<td> <input type=number name='price'></td>");
  	pw.println("</tr>");
	pw.println("<tr>");
	pw.println("<td> Image</td>");
  	pw.println("<td> <input type='text' name='image'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Manufacturer Name</td>");
    pw.println("<td> <input type='text' name='manufacturer'></td>");
  	pw.println("</tr>");  	
  	pw.println("<tr>");
  	pw.println("<td>Condition</td>");
  	pw.println("<td> <input type='text' name='condition'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Discount</td>");
  	pw.println("<td> <input type=number name='discount'></td>");
  	pw.println("</tr>");  		
  	
  	pw.println("");
	pw.print("<tr><td></td><td></td><td><input type='submit' name='submit' value='Submit' class='btnbuy'></td>");			
  	pw.println("</table>");
  	pw.println("</form>");
  	pw.println("");
	pw.print("</form></div></div></div>");		
	utility.printHtml("Footer.html");
	}

}