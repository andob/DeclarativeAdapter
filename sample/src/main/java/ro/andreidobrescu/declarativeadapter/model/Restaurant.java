package ro.andreidobrescu.declarativeadapter.model;

import java.io.Serializable;

public class Restaurant implements Serializable
{
    private String name;
    private String location;
    private int rating;
    private String image;

    public Restaurant(String name, String location, int rating, String image)
    {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.image = image;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}
