import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class AjaxUtility{
	Connection conn = null; 
	
	//get the products
	
	public HashMap<String,Product> getData(){
	   HashMap<String,Product> product = new HashMap<String,Product>();
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","root");
		 
		   String selectCustomerQuery = "SELECT * from product ;";
		   PreparedStatement pst = conn.prepareStatement(selectCustomerQuery);		
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   
			    String id = rs.getString("ID");
			   String name = rs.getString("Name");
			   Integer price = rs.getInt("Price");
			   String image = rs.getString("Image");
			   String manufacturer = rs.getString("Manufacturer");
			   String condition = rs.getString("ConditionStatus");
			   Double discount=rs.getDouble("Discount");
			   Product p = new Product(id, name, price, image, manufacturer,condition ,discount);
               product.put(id, p);
			  		
		   }		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }   
		return product;
   }


//read the products
public StringBuffer readData(String searchId){
		StringBuffer sb = new StringBuffer();
		HashMap<String,Product> product = getData();		
		Iterator it = product.entrySet().iterator();
		 while (it.hasNext()) {
			 Map.Entry pi = (Map.Entry)it.next();
			 Product p=(Product)pi.getValue();
			  
			 
			 if(p.getName().toLowerCase().startsWith(searchId)){
				
						sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<Name>" + p.getName() + "</Name>");                      
                        sb.append("</product>");				
				 
			 }
	
	}
   	return sb;
}

}