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

@WebServlet("/ViewReview")
public class ViewReview extends HttpServlet {
   HashMap<String,ArrayList<Review>> review = null;
  
	MongoDBDataStoreUtilities s = null;
      
    public void init() {
           
			review = new HashMap<String,ArrayList<Review>>();
            s  = new MongoDBDataStoreUtilities();
            review = s.selectReview();
          }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
	}
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View a review");
			response.sendRedirect("Login");
			return;
		}

	String ProductModelName = request.getParameter("name");	
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<form name ='ViewReview' action='ViewReview' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>View a review:</a>");
		pw.print("</h2><div class='entry'>");
      	ArrayList<Review> productreview = review.get(ProductModelName);
										
				if(productreview != null){
										 
				 for(Review product: review.get(ProductModelName)){											 									
																		
			    if(product !=null){
pw.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +"<TR BGCOLOR=\"#E6E6FA\">\n");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ProductModelName\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getProductModelName());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ProductCategory\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getProductCategory());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ProductPrice\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getProductPrice());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "RetailerName\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getRetailerName());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "RetailerZip\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getRetailerZip());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "RetailerCity\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getRetailerCity());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "RetailerState\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getRetailerState());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ProductOnSale\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getProductOnSale());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ManufacturerName\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getManufacturerName());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ManufacturerRebate\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getManufacturerRebate());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "UserId\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getUserId());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "UserAge\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getUserAge());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "UserGender\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getUserGender());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "UserOccupation\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getUserOccupation());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");							
pw.println( "<td>\n ");
pw.println( "ReviewRating\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getReviewRating());
pw.println("</td> \n");
pw.println("</tr>");
pw.println("<tr>\n");
pw.println( "<td> ");
pw.println( "ReviewDate\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getReviewDate());
pw.println("</td> \n"); 
pw.println("<tr>\n");
pw.println( "<td>\n ");													
pw.println( "ReviewText\n" );
pw.println("</td> \n");
pw.println( "<td>\n ");
pw.println(product.getReviewText());
pw.println("</td> \n"); 
pw.println("</tr>");
pw.println("</table>\n");
									} 
									else{
											pw.println("No Review available");
										}  
				
							                     }
							 
				                }
		
	pw.print("</form></div></div></div>");		
	utility.printHtml("Footer.html");               
		
  }	
  
}


