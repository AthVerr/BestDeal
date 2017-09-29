import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/WriteReview")
public class WriteReview extends HttpServlet {
   HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
 
	MongoDBDataStoreUtilities s = null;
    
    public void init() {              
              s  = new MongoDBDataStoreUtilities();
            }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayReview(request, response, pw, false);
	}
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String username = request.getParameter("username");
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Write a review");
			response.sendRedirect("Login");
			return;
		}
displayReview(request, response, pw, true);				
  }	
  
  
  	
	protected void displayReview(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");


		pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>Write a review:</a>");
		pw.print("</h2><div class='entry'>");

		String ProductModelName = request.getParameter("name");		
		String ProductCategory = request.getParameter("type");
		
	pw.println("<form action='WriteReview'>");
  	pw.println("<h2>Write your product review</h2>");
  	pw.println("<table>");
  	pw.println("<tr>");
  	pw.println("<td>Product Model Name</td>");
  	pw.println("<td><input type='text' readonly height='20' width='30' name='ProductModelName' value='"+ProductModelName+"'"+"></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Product Category</td>");
  	pw.println("<td> <input type='text' readonly name='ProductCategory'value='"+ProductCategory+"'"+"></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Product Price</td>");
    pw.println("<td> <input type='text' name='ProductPrice'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Retailer Name</td>");
    pw.println("<td> <input type='text' name='RetailerName'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
	pw.println("<td> Retailer Zip</td>");
  	pw.println("<td> <input type='text' name='RetailerZip'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Retailer City</td>");
  	pw.println("<td> <input type='text' name='RetailerCity'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Retailer State</td>");
  	pw.println("<td> <input type='text' name='RetailerState'></td>");
  	pw.println("</tr>");  		
  	pw.println("<tr>");
  	pw.println("<td> ProductOnSale</td>");
  	pw.println("<td> <input type='text' name='ProductOnSale'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> ManufacturerName</td>");
  	pw.println("<td> <input type='text' name='ManufacturerName'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> ManufacturerRebate</td>");
  	pw.println("<td> <input type='text' name='ManufacturerRebate'></td>");
  	pw.println("</tr>");
  	pw.println("");
  	pw.println("<tr>");
  	pw.println("<td> UserID</td>");
  	pw.println("<td> <input type='text' name='UserID'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> User Age</td>");
  	pw.println("<td> <input type='text' name='UserAge'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> User gender</td>");
  	pw.println("<td> <select name='UserGender'>");
  	pw.println("<option>Male");
  	pw.println("</option>");
  	pw.println("<option>Female");
  	pw.println("</option>");
  	pw.println("</select>");
  	pw.println("</td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> User Occupation</td>");
  	pw.println("<td> <input type='text' name='UserOccupation'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Review Rating</td>");
  	pw.println("<td> <input type='text' name='ReviewRating'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Review Date</td>");
  	pw.println("<td> <input type='text' name='ReviewDate'></td>");
  	pw.println("</tr>");
  	pw.println("<tr>");
  	pw.println("<td> Review Text</td>");
  	pw.println("<td> <textarea rows='4' cols='50' name='ReviewText'></textarea></td>");
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


