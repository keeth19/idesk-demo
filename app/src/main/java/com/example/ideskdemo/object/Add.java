package com.example.ideskdemo.object;

public class Add {

    private String addTittle;
    private int image;
    private int price;

    public Add(int image, String addTittle, int price) {
        this.addTittle = addTittle;
        this.price = price;
        this.image = image;
    }


    public  Add(){
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddTittle() {
        return addTittle;
    }

    public void setAddTittle(String addTittle) {
        this.addTittle = addTittle;
    }

}
