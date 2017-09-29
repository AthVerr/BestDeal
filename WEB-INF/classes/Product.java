

import java.util.ArrayList;
import java.util.List;


public class Product  implements java.io.Serializable{
	public String id;  
    public String name;
    public int price;
    public String image;
	public String manufacturer;
    public String ConditionStatus;    
	public double discount;   
    //public List<String> accessories;

    public Product(String id,String name,int price,String image,String manufacturer,String ConditionStatus,double discount){
     this.id=  id;
     this.name=name;
    this.price=price;
     this.image=image;
	 this.manufacturer=manufacturer;
     this.ConditionStatus= ConditionStatus;
	this.discount= discount;
    }

public void setId(String id) {
	this.id = id;
}
public String getId(){
	return id;
	}
public void setName(String name) {
	this.name = name;
}
public String getName(){
	return name;
}
public void setPrice(int price) {
	this.price = price;
}

public int getPrice(){
return this.price;
}
public void setImage(String image) {
	this.image = image;
}

public String getImage() {
	return image;
}

public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
}

public String getManufacturer(){
	return manufacturer;
}

public void setCondition(String ConditionStatus) {
	this.ConditionStatus = ConditionStatus;
}

public String getCondition() {
	return this.ConditionStatus;
}
public double getDiscount(){
return this.discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
//public List getAccessories() {
	//return accessories;
//}

}
   