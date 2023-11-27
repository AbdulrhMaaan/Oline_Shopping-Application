package com.example.project_oline_shopping.UI;

public class Product {


    private int id;
    private String name;
    private String category;
    private String price;
    private String Image;
    private int quantity;


    public Product(String name, String price, String Image)
    {
        this.name = name;
        this.price = price;
        this.Image = Image;


    }

    public int getQuantity() {
        return quantity;
    }

    public Product(int id, String name, String category, String price, String Image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.Image = Image;

    }

    public Product( String name, String price,int quantity, String Image) {
        this.name = name;
        this.price = price;
        this.Image = Image;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void increasequantety()
    {
        quantity++;
    }
    public void decreasequantety()
    {
        quantity--;
    }

}
