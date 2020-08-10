package com.example.ideskdemo.object;

/**
 * Created by keert on 01/03/2017.
 */

public class Category {

    public Category(String categoryName, int image) {
        this.categoryName = categoryName;
        this.image = image;
    }

    public Category(){

    }

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String categoryID;
    private String categoryName;
    private int image;
    private String categoryImage;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

}
