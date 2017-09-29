public class Review implements java.io.Serializable {
//Product Review fields
String itemID;
String ProductModelName;
String ProductCategory;
String ProductPrice;
String RetailerName;
String RetailerZip;
String RetailerCity;
String RetailerState;
String ProductOnSale;
String ManufacturerName;
String ManufacturerRebate;
String UserId;
String UserAge;
String UserGender;
String UserOccupation;
String ReviewRating;
String ReviewDate;
String ReviewText;

Review(String itemID,String ProductModelName,String ProductCategory,String ProductPrice,String RetailerName,String RetailerZip,String RetailerCity,String RetailerState,
       String ProductOnSale,String ManufacturerName,String ManufacturerRebate,String UserId,String UserAge, String UserGender,String UserOccupation,
	   String ReviewRating,String ReviewDate,String ReviewText){

	 
           this.itemID = itemID;
           this.ProductModelName = ProductModelName;
           this.ProductCategory = ProductCategory;
           this.ProductPrice = ProductPrice;
           this.RetailerName = RetailerName;
           this.RetailerZip = RetailerZip;
           this.RetailerCity = RetailerCity;
           this.RetailerState = RetailerState;
           this.ProductOnSale = ProductOnSale;
           this.ManufacturerName = ManufacturerName;
           this.ManufacturerRebate = ManufacturerRebate;
           this.UserId = UserId;
           this.UserAge = UserAge;
           this.UserGender = UserGender;
           this.UserOccupation = UserOccupation;
           this.ReviewRating = ReviewRating;
           this.ReviewDate = ReviewDate;
           this.ReviewText = ReviewText;

}

public void setitemID(String itemID){  //unique id
       this.itemID = itemID;
}

public String getitemID(){
       return itemID;
}

public void setProductModelName(String ProductModelName) {
       this.ProductModelName = ProductModelName;
}

public String getProductModelName() {
        return ProductModelName;
}

public void setProductCategory(String ProductCategory) {
       this.ProductCategory = ProductCategory;
}

public String getProductCategory() {
       return ProductCategory;
}

public void setProductPrice(String ProductPrice) {
       this.ProductPrice = ProductPrice;
}

public String getProductPrice() {
    return ProductPrice;
}

public void setRetailerName(String RetailerName) {
    this.RetailerName = RetailerName;
}
public String getRetailerName() {
   return RetailerName;
}

public void setRetailerZip(String RetailerZip) {
      this.RetailerZip = RetailerZip;
}

public String getRetailerZip() {
     return RetailerZip;
}
public void setRetailerCity( String RetailerCity) {
   this.RetailerCity = RetailerCity;
}

public String getRetailerCity() {
   return RetailerCity;
}

public void setRetailerState( String RetailerState){
     this.RetailerState = RetailerState;
}

public String getRetailerState(){
    return RetailerState;
}

public void setProductOnSale(String ProductOnSale){
	this.ProductOnSale = ProductOnSale;
}

public String getProductOnSale(){
	return ProductOnSale;
}

public void setManufacturerName( String ManufacturerName){
	this.ManufacturerName = ManufacturerName;
}

public String getManufacturerName(){
	return ManufacturerName;
}

public void setManufacturerRebate(String ManufacturerRebate){
	this.ManufacturerRebate = ManufacturerRebate;
}

public String getManufacturerRebate(){
	return ManufacturerRebate;
}

public void setUserId(String UserId ){
	this.UserId = UserId;
}

public String getUserId(){
	return UserId;
}

public void setUserAge(String UserAge){
	this.UserAge = UserAge;
}

public String getUserAge(){
	return UserAge;
}


public void setUserGender(String UserGender){
	this.UserGender = UserGender;
}

public String getUserGender(){
	return UserGender;
}

public void setUserOccupation(String UserOccupation){
	this.UserOccupation = UserOccupation;
}

public String getUserOccupation(){
	return UserOccupation;
}

public void setReviewRating(String ReviewRating){
	this.ReviewRating = ReviewRating;
}

public String getReviewRating(){
	return ReviewRating;
}

public void setReviewDate(String ReviewDate){
	this.ReviewDate = ReviewDate;
}

public String getReviewDate(){
	return ReviewDate;
	
}

public void setReviewText(String ReviewText){
	this.ReviewText = ReviewText;
}

public String getReviewText(){
	return ReviewText;
	
}



}

