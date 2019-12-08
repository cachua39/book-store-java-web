/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.dtos;

import java.io.Serializable;

/**
 *
 * @author LeVaLu
 */
public class BookDTO implements Serializable{
    private String bookId, title, author, description, photo, categoryName;
    private float price, subTotal;
    private int quantity, quantityCart;

    public BookDTO() {
    }
    
    
    // Contructor for save book to database
    public BookDTO(String bookId, String title, String author, String description, String photo, String categoryName, float price, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.description = description;
        this.photo = photo;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = quantity;
    }
    

    // Contructor for loading all books by admin
    public BookDTO(String bookId, String title, String author, String photo, String categoryName, float price, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Contructor for loading all books by customer
    public BookDTO(String bookId, String title, String author, String photo, String categoryName, float price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.categoryName = categoryName;
        this.price = price;
    }
    
    // Contructor for cart
    public BookDTO(String bookId, String title, float price) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
    }
    

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(int quantityCart) {
        this.quantityCart = quantityCart;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
    
}
