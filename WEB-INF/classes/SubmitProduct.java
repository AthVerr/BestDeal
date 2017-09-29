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

@WebServlet("/SubmitProduct")

public class SubmitProduct extends HttpServlet { 
	 HashMap<String, ArrayList<Product>> products= new HashMap<String, ArrayList<Product>>();
   MySqlDataStoreUtilities sd = null;
    
    public void init() {
	          sd = new MySqlDataStoreUtilities();             
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);	   
	
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='SubmitProduct' action='SubmitProduct' method='post'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;color: red;'>Product Status:</a>");
		pw.print("</h2><div class='entry'>");
		
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceSt  = request.getParameter("price");
		int price = Integer.parseInt(priceSt);
		String image  = request.getParameter("image");
		String manufacturer = request.getParameter("manufacturer");
		String condition = request.getParameter("condition");		
		String discountSt = request.getParameter("discount");
		double discount= Double.parseDouble(discountSt);
			
	    Product product = new Product(productId,productName,price,image,manufacturer,condition,discount);
	    ArrayList<Product> arr = new ArrayList<Product>();
        products.put(productId, arr);			
	    sd.addProducts(productId,productName,price,image,manufacturer,condition,discount);
	   
        pw.print("<br>Product "+productName+" is successfully added");
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
	}

}


