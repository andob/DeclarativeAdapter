package ro.andreidobrescu.declarativeadapter.model;

public class Receipe
{
    private String name;
    private String image;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Receipe(String name, String image)
    {
        this.name = name;
        this.image = image;
    }
}
