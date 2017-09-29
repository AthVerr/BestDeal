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

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet {
	Review review;
	 HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
 
	MongoDBDataStoreUtilities s = null;
    
    public void init() {              
              s  = new MongoDBDataStoreUtilities();
            }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
	   
	
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<form name ='SubmitReview' action='SubmitReview' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>Review Status:</a>");
		pw.print("</h2><div class='entry'>");
		

		
String itemID = request.getParameter("itemID");
String ProductModelName = request.getParameter("ProductModelName");
String ProductCategory = request.getParameter("ProductCategory");
String ProductPrice = request.getParameter("ProductPrice");
String RetailerName = request.getParameter("RetailerName");
String RetailerZip = request.getParameter("RetailerZip");
String RetailerCity = request.getParameter("RetailerCity");
String RetailerState = request.getParameter("RetailerState");
String ProductOnSale = request.getParameter("ProductOnSale");
String ManufacturerName = request.getParameter("ManufacturerName");
String ManufacturerRebate = request.getParameter("ManufacturerRebate");
String UserId = request.getParameter("UserId");
String UserAge = request.getParameter("UserAge");
String UserGender = request.getParameter("UserGender");
String UserOccupation = request.getParameter("UserOccupation");
String ReviewRating = request.getParameter("ReviewRating");
String ReviewDate = request.getParameter("ReviewDate");
String ReviewText = request.getParameter("ReviewText");

Review review = new Review(itemID,ProductModelName,ProductCategory,ProductPrice,RetailerName,RetailerZip,
                     RetailerCity,RetailerState,ProductOnSale,ManufacturerName,ManufacturerRebate,
                     UserId,UserAge, UserGender,UserOccupation,ReviewRating,ReviewDate,ReviewText);

		ArrayList<Review> arr = new ArrayList<Review>();
          reviews.put(ProductModelName, arr);		
				s.insertReview(review); //save in mongodb
      
	   
        pw.print("<br>Your review for product "+ProductModelName+"is complete");
		
		
		
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}

}


