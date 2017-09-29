import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
public class MySqlDataStoreUtilities{
	String databaseURL = "jdbc:mysql://localhost:3306/bestdeal";
        String user = "root";
        String password = "root";
        Connection conn = null;
	public void getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","root");
		}
		catch(Exception e){}
	}
	
	
	//..............................accounts login information..............................
//when register the user details go here
	public void  insertUser(String username,String password,String usertype){
        try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","root");
        String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,usertype)" + "VALUES (?,?,?);";
        PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
        pst.setString(1,username);
        pst.setString(2,password); //Setting Value for Each Parameter
		pst.setString(3,usertype);
        pst.execute(); //Execute method will insert data into database
		conn.close();
         }
        catch(Exception e){}
    }
	//select a user from database
	  public HashMap<String,User> selectUser(){
	   HashMap<String,User> user = new HashMap<String,User>();
	   try{
		   Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","root");
		   String selectCustomerQuery = "SELECT * from Registration ;";
		   PreparedStatement pst = conn.prepareStatement(selectCustomerQuery); //select from the registration table
		   ResultSet rs = pst.executeQuery();
		   while(rs.next()){
			   String userid = rs.getString("userID");			   
			   String username = rs.getString("username");
			   String password = rs.getString("password");
			   String usertype = rs.getString("usertype");	  			  	   
			   User u = new User(username,password,usertype); //class user create an instance 
			   user.put(username,u);
		   }
		   
	     pst.close();	      
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   		
	   return user;
   }
	  public boolean checkStatus(String user){ //check if the user exist in the database	   
	   boolean status = false;
	   try {
	   Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal" ,"root","root");    
	   String checkCustomerQuery = "SELECT UserID from Registration where UserID =?;";   	   
	   PreparedStatement pst = conn.prepareStatement(checkCustomerQuery);
	   pst.setString(1,user);
	   ResultSet rs = pst.executeQuery();
	   while(rs.next()){
		  	String userid = (rs.getString("userID"));

			if (user.equals(userid)){
			    status = true;			
			}
			else {
				status = false;				
			}				 
	   }
		pst.close();
	   }
	   catch(Exception e){
		e.printStackTrace();
	   }
	   
	  return status;    
   }
   
   //..............................transactions/orders..............................
   public HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
     {
     HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
      try{ 
	   Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal" ,"root","root");  
      String selectOrderQuery ="select * from CustomerOrders";
      PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
      ResultSet rs = pst.executeQuery();
      ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
      while(rs.next())
     {if(!orderPayments.containsKey(rs.getInt("orderId")))
    {ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
     orderPayments.put(rs.getInt("orderId"), arr);}
     ArrayList<OrderPayment> listOrderPayment =orderPayments.get(rs.getInt("OrderId"));
     OrderPayment order= new
     OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"));
     listOrderPayment.add(order);
    }
   }catch(Exception e){}
    return orderPayments;
 }
	
	
	public void insertOrder(int orderId,String userName,String orderName,double orderPrice,String orderAddress,String creditNo)
       { try
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal" ,"root","root");  
          
		  String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrders(orderId,userName,orderName,orderPrice,orderAddress,creditNo)" + "VALUES (?,?,?,?,?,?);";
         PreparedStatement pst =conn.prepareStatement(insertIntoCustomerOrderQuery);
          pst.setString(1,Integer.toString(orderId));
          pst.setString(1,userName);
          pst.setString(2,orderName);
		  pst.setString(3,Double.toString(orderPrice));
          pst.setString(4,orderAddress);
          pst.setString(5,creditNo);
         pst.execute();
		 conn.close();
        } 
        catch(Exception e){}
    } 
	
	
	public  void removeOrder(ArrayList<OrderPayment> lists){  //use it when we want to delete an order 
		try { 
		Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal" ,"root","root"); 
			
			String delete="DELETE FROM CustomerOrders WHERE orderID=? AND orderName=?);";
			PreparedStatement pst=conn.prepareStatement(delete);

			for (OrderPayment item: lists){
				pst.setString(1,Integer.toString(item.getOrderId()));
				pst.setString(2,item.getOrderName());
				pst.execute();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//add product 
	
	 public void addProducts(String productId,String productName,int price,String image,String manufacturer,String condition,double discount)
	  {    
		  
				  try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
                   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","root");
						String query ="INSERT INTO product(ID, Name, Price, Image, Manufacturer, ConditionStatus ,Discount) VALUES(?,?,?,?,?,?,?)";
						PreparedStatement preparedStatement = conn.prepareStatement(query);
						preparedStatement.setString(1, productId);
						preparedStatement.setString(2, productName);
						preparedStatement.setInt(3, price);
						preparedStatement.setString(4, image);
						preparedStatement.setString(5, manufacturer);
						preparedStatement.setString(6, condition);
						preparedStatement.setDouble(7, discount);
						//preparedStatement.setString(8, product.getAccessories());				
						
						preparedStatement.execute(); //Execute method will insert data into database
		                conn.close();
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}

			  
				  
		  }
		
	  
   
}





 