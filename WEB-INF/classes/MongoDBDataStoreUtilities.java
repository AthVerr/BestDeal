import java.util.*;
import java.io.*;
import java.sql.*;
//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.*;


public class MongoDBDataStoreUtilities{
	

static DBCollection reviews;
	    public static void getConnection(){
		MongoClient mongo;
		mongo = new MongoClient("localhost",27017);
		
		DB db = mongo.getDB("CustomerReviews");
		reviews = db.getCollection("reviews");
	}
	
	
	
	
     //find reviews to view
public static HashMap<String, ArrayList<Review>> selectReview()
{
	getConnection();
   HashMap<String, ArrayList<Review>> reviewHashmap=new HashMap<String, ArrayList<Review>>();
   DBCursor cursor = reviews.find();
    while (cursor.hasNext())
   {  
   BasicDBObject obj = (BasicDBObject) cursor.next();
  if(! reviewHashmap.containsKey(obj.getString("ProductModelName")))
      {
     ArrayList<Review> arr = new ArrayList<Review>();
    reviewHashmap.put(obj.getString("ProductModelName"), arr);
     }
 ArrayList<Review> listReview = reviewHashmap.get(obj.getString("ProductModelName"));
 Review review =new Review(obj.getString("itemID"),obj.getString("ProductModelName"),
 obj.getString("ProductCategory"),obj.getString("ProductPrice"),obj.getString("RetailerName"),obj.getString("RetailerZip"),obj.getString("RetailerCity"),
 obj.getString("RetailerState"),obj.getString("ProductOnSale"),obj.getString("ManufacturerName"),obj.getString("ManufacturerRebate"),obj.getString("UserId"),obj.getString("UserAge"),
 obj.getString("UserGender"),obj.getString("UserOccupation"),obj.getString("ReviewRating"),obj.getString("ReviewDate"),obj.getString("ReviewText"));

 listReview.add(review);
 }
 return reviewHashmap;
}



//insert new reviews
public void insertReview(Review review){
	int result = 0;
	
	//all the review fields / variables 
    String itemID = review.getitemID();
	System.out.println(itemID);
	String ProductModelName = review.getProductModelName();			
	String ProductCategory = review.getProductCategory();
	String ProductPrice = review.getProductPrice();
	String RetailerName = review.getRetailerName();
	String RetailerZip = review.getRetailerZip();
	String RetailerCity = review.getRetailerCity();
	String RetailerState = review.getRetailerState();
	String ProductOnSale = review.getProductOnSale();
	String ManufacturerName = review.getManufacturerName();
	String ManufacturerRebate = review.getManufacturerRebate();
	String UserId = review.getUserId();
	String UserAge = review.getUserAge();
	String UserGender = review.getUserGender();
	String UserOccupation = review.getUserOccupation();
	String ReviewRating = review.getReviewRating(); 
	String ReviewDate = review.getReviewDate();
	String ReviewText = review.getReviewText();				
	
   try{			
	getConnection();
	
	
	BasicDBObject doc  = new BasicDBObject("title","reviews");
	
	doc.append("itemID",itemID);
	doc.append("ProductModelName",ProductModelName);
	doc.append("ProductCategory",ProductCategory);
	doc.append("ProductPrice",ProductPrice);
	doc.append("RetailerName",RetailerName);
	doc.append("RetailerZip",RetailerZip);
	doc.append("RetailerCity",RetailerCity);
	doc.append("RetailerState",RetailerState);
	doc.append("ProductOnSale",ProductOnSale);
	doc.append("ManufacturerName",ManufacturerName);
	doc.append("ManufacturerRebate",ManufacturerRebate);
	doc.append("UserId",UserId);
	doc.append("UserAge",UserAge);
	doc.append("UserGender",UserGender);
	doc.append("UserOccupation",UserOccupation);
	doc.append("ReviewRating",ReviewRating);
	doc.append("ReviewDate",ReviewDate);
	doc.append("ReviewText",ReviewText);				
	reviews.insert(doc);
	 
   }
   catch(Exception e){
	   e.printStackTrace();
   }
   // return result;
}
//top five products with the highest reviews,most liked
public AggregationOutput topfiveproductliked(){
	   AggregationOutput aggregationoutput1 = null;
	   try{
		getConnection();
		//group by product name and rating 
        DBObject groupFields = new BasicDBObject("_id", 0);
		groupFields.put("_id", "$ProductModelName"); 
		groupFields.put("ReviewRating",new BasicDBObject("$push","$ReviewRating")); //we search for the average rating 										
		DBObject group1 = new BasicDBObject("$group", groupFields); 	
//project by product name and rating 		
		DBObject projectFields = new BasicDBObject("_id", 0); 
		projectFields.put("ProductModelName", "$_id"); 
		projectFields.put("ReviewRating","$ReviewRating"); 
		DBObject project1 = new BasicDBObject("$project", projectFields);	
//sort and limitation 		
		DBObject sort1 = new BasicDBObject(); 
		sort1.put("ReviewRating",-1); 
		DBObject order1 =new BasicDBObject("$sort",sort1); //sort the product so they are in order
		DBObject limit1 =new BasicDBObject("$limit",5); //the top 5 rating products																				
		aggregationoutput1 = reviews.aggregate(group1,project1,order1,limit1);  
		 //aggregationoutput1 = reviews.aggregate(group1,project1); 
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }   
	   	   
     return aggregationoutput1;	   
   }
   
   //check for the top 5  zip code that sold the most products
    public AggregationOutput topfivezip(){
	   AggregationOutput aggregationoutput2 = null;
	   try{
		   getConnection();
		   //group by zip code and products
        DBObject groupFields2 = new BasicDBObject("_id", 0);
	    groupFields2.put("_id", "$RetailerZip"); 
		groupFields2.put("count",new BasicDBObject("$sum",1)); 		//use sum to add how many products have solved with the same zip								
		DBObject group2 = new BasicDBObject("$group", groupFields2); 	
//project by zip code and products		
		DBObject projectFields2 = new BasicDBObject("_id", 0); 
		projectFields2.put("RetailerZip", "$_id"); 
		projectFields2.put("ProductSold","$count"); 
		DBObject project2 = new BasicDBObject("$project", projectFields2); 
		//sort and limitation 	
		DBObject sort2 = new BasicDBObject(); 
		sort2.put("ProductSold",-1); 
		DBObject order2=new BasicDBObject("$sort",sort2); 
		DBObject limit2=new BasicDBObject("$limit",5); 
		aggregationoutput2 = reviews.aggregate(group2,project2,order2,limit2); 
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }
	   	   	   
     return aggregationoutput2;	   
   }

   //Top five most sold products regardless of the rating
     public AggregationOutput topsold(){
	   AggregationOutput aggregationoutput3 = null;
	   try{
		   getConnection();
		   //group by zip code and products
        DBObject groupFields3 = new BasicDBObject("_id", 0);
	    groupFields3.put("_id", "$ProductModelName"); 
		groupFields3.put("count",new BasicDBObject("$sum",1)); 		//use sum to add how many products have solved with the same zip								
		DBObject group3 = new BasicDBObject("$group", groupFields3); 	
//project by zip code and products		
		DBObject projectFields3 = new BasicDBObject("_id", 0); 
		projectFields3.put("ProductModelName", "$_id"); 
		projectFields3.put("ProductSold","$count"); 
		DBObject project3 = new BasicDBObject("$project", projectFields3); 
		//sort and limitation 	
		DBObject sort3 = new BasicDBObject(); 
		sort3.put("ProductSold",-1); 
		DBObject order3=new BasicDBObject("$sort",sort3); 
		DBObject limit3=new BasicDBObject("$limit",5); 
		aggregationoutput3 = reviews.aggregate(group3,project3,order3,limit3); 
			   }
       catch(Exception e){
		   e.printStackTrace();
	   }
	   	   	   
     return aggregationoutput3;	   
   }
   
}
