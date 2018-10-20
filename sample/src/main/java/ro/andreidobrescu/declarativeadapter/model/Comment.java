package ro.andreidobrescu.declarativeadapter.model;

public class Comment
{
    private String message;
    private String author;
    private String createdAt;
    private int createdBy;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public int getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy(int createdBy)
    {
        this.createdBy = createdBy;
    }

    public Comment(String message, String author, String createdAt, int createdBy)
    {
        this.message = message;
        this.author = author;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
}
