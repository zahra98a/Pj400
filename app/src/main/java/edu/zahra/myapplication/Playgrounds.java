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

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Playgrounds(){
    }
}
