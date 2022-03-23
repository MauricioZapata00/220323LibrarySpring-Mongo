package models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

    private final String IS_LENDED = "The book is borrowed";
    private final String IS_NOT_LENDED = "The book is available";

    @Id
    private String id;

    private String name;
    private String author;
    private Integer numberOfPages;
    private Boolean available;
    private String dateOfLending;
    private String state;
    private String resources;
    private String thematic;

    public Book(String name, String author, Integer numberOfPages, String resources, String thematic) {
        this.name = name;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.available = true;
        this.state = this.IS_NOT_LENDED;
        this.resources = resources;
        this.thematic = thematic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
        if (this.available){
            this.setState(this.IS_NOT_LENDED);
        }else{
            this.setState(this.IS_LENDED);
        }
    }

    public String getDateOfLending() {
        return dateOfLending;
    }

    public void setDateOfLending(String dateOfLending) {
        this.dateOfLending = dateOfLending;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }
}
