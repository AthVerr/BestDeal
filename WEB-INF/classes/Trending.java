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
import com.mongodb.DBCollection;
import com.mongodb.*;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	MongoDBDataStoreUtilities s = null;
	 static DBCollection reviews;
	AggregationOutput aggregate3 = null;
	AggregationOutput aggregate2 = null;	
	AggregationOutput aggregate1 = null;
    
    public void init() {
		   
            s  = new MongoDBDataStoreUtilities();           
			
			aggregate1 = s.topfiveproductliked();
			aggregate2 = s.topfivezip(); 
			aggregate3 = s.topsold();

    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}
		protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<form name ='Trending' action='Trending' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>See the trending products:</a>");
		pw.print("</h2><div class='entry'>");
			
		
			//top 5 rating 							 
		pw.print("</table>"); 			                           
		pw.print("<table id='bestdeal'>"); 
		pw.println("<tr><td><center>"+"Top five most liked products based on rating"+"</center></td></tr>");
		pw.println("<tr><td> "+"ProductModelName"+"</td>&nbsp" + "<td>"+"ReviewRating"+"</td></tr>");		

		for (DBObject result : aggregate1.results()) { 
		BasicDBObject bobj = (BasicDBObject) result; //call for the result of top rating products 		
										pw.println("<tr><td> "+bobj.getString("ProductModelName")+"</td>&nbsp" + "<td>"+bobj.getString("ReviewRating")+"</td></tr>"); 
										
										}
									
		pw.print("</table>");  
		
		
		
 		//top zip max product sold in this zip code
		pw.print("</table>"); 			                           
		pw.print("<table id='bestdeal2'>"); 
		pw.println("<tr><td><center>"+"Top five zip-codes where maximum number of products sold"+"</center></td></tr>");
		pw.println("<tr><td> "+"RetailerZip"+"</td>&nbsp" + "<td>"+"Products sold:"+"</td></tr>");
		for (DBObject result : aggregate2.results()) { 
		BasicDBObject bobj = (BasicDBObject) result; 		
										pw.println("<tr><td> "+bobj.getString("RetailerZip")+"</td>&nbsp" + "<td>"+bobj.getString("ProductSold")+"</td></tr>"); 
										
										}									
		pw.print("</table>");  
		
		
		//most sold product no matter the rating 
		pw.print("</table>"); 			                           
		pw.print("<table id='bestdeal3'>"); 
		pw.println("<tr><td><center>"+"Top five most sold products regardless of the rating"+"</center></td></tr>");
		pw.println("<tr><td> "+"ProductModelName"+"</td>&nbsp" + "<td>"+"sold:"+"</td></tr>");
		for (DBObject result : aggregate3.results()) { 
		BasicDBObject bobj = (BasicDBObject) result; 
	
										pw.println("<tr><td> "+bobj.getString("ProductModelName")+"</td>&nbsp" + "<td>"+bobj.getString("ProductSold")+"</td></tr>"); 
										
										}									
		pw.print("</table>");  
		
	   pw.print("</form></div></div></div>");		
	   utility.printHtml("Footer.html");  
    }

}
