package edu.zahra.myapplication;

public class Playgrounds {

    public String imageURL;
    public String name;
    public String description;
    public String price;
    public String capacity;
    public String areaSize;
    public String openingHours;
    public String contactNumber;
    public String location;

    public Playgrounds(){
    }

    public Playgrounds( String name, String description, String price, String capacity, String areaSize,String openingHours,
                        String contactNumber, String location ,String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.areaSize = areaSize;
        this.openingHours = openingHours;
        this.contactNumber = contactNumber;
        this.location = location;
        this.imageURL = url;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){this.description = description;}

    public String getPrice() {
        return price;
    }
    public void setPrice(String price){this.price = price;}

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity){this.capacity = capacity;}

    public String getAreaSize() { return areaSize; }
    public void setAreaSize(String areaSize){this.areaSize = areaSize;}

    public String getOpeningHours() {
        return openingHours;
    }
    public void setOpeningHours(String openingHours){this.openingHours = openingHours;}

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber){this.contactNumber = contactNumber;}

    public String getLocation() {
        return location;
    }
    public void setLocation(String location){this.location = location;}

    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL){this.imageURL = imageURL;}
}
