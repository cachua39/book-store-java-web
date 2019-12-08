/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author LeVaLu
 */
public class OrderDTO implements Serializable{
    private String userId, couponCode, createdDate;
    private float total, discountValue;
    private List<BookDTO> listBook;
    int orderId;

    public OrderDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<BookDTO> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookDTO> listBook) {
        this.listBook = listBook;
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
