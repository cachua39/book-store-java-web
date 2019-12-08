/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import luonglv.dtos.BookDTO;

/**
 *
 * @author LeVaLu
 */
public class ShoppingCart implements Serializable{
    private String customerId;
    private Map<String, BookDTO> cart;

    public ShoppingCart(String customerId) {
        this.customerId = customerId;
        cart = new HashMap<>();
    }

    public Map<String, BookDTO> getCart() {
        return cart;
    }

    public String getCustomerId() {
        return customerId;
    }
    
    public void add(BookDTO bookDTO) {
        if(cart.containsKey(bookDTO.getBookId())) {
            int quantity = cart.get(bookDTO.getBookId()).getQuantityCart();
            bookDTO.setQuantityCart(quantity + 1);
        }
        cart.put(bookDTO.getBookId(), bookDTO);
    }
    
    public void update(String bookId, int quantity) {
        if(cart.containsKey(bookId)) {
            cart.get(bookId).setQuantityCart(quantity);
        }
    }
    
    public void remove(String bookId) {
        if(cart.containsKey(bookId)) {
            cart.remove(bookId);
        }
    }
    
    public void removeAll(){
        cart.clear();
    }
    
    public Float getTotal() {
        Float total = 0F;
        
        for(BookDTO bookDTO : cart.values()) {
            total += (bookDTO.getQuantityCart() * bookDTO.getPrice());
        }
        return total;
    }
}
