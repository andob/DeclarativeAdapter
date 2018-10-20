package ro.andreidobrescu.declarativeadapter.model;

import java.util.List;

public class RestaurantDetails
{
    private Restaurant restaurant;
    private List<Receipe> receipes;
    private List<Comment> comments;

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public List<Receipe> getReceipes()
    {
        return receipes;
    }

    public void setReceipes(List<Receipe> receipes)
    {
        this.receipes = receipes;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
