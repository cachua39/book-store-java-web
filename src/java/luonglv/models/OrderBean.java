/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.models;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import luonglv.daos.OrderDAO;
import luonglv.dtos.OrderDTO;

/**
 *
 * @author LeVaLu
 */
public class OrderBean implements Serializable{
    private OrderDAO orderDAO;
    private OrderDTO orderDTO;
    private String userId;
    private String name, date;

    public OrderBean() {
        orderDAO = new OrderDAO();
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public boolean insertWithDiscount() throws NamingException, SQLException {
        return orderDAO.insertWithDiscount(orderDTO);
    }
    
    public boolean insertNoDiscount() throws NamingException, SQLException {
        return orderDAO.insertNoDiscount(orderDTO);
    }
    
    public List<OrderDTO> loadHistory() throws NamingException, SQLException {
        return orderDAO.loadHistory(userId);
    }
    
     public List<OrderDTO> searchHistory() throws NamingException, SQLException {
        return orderDAO.searchHistory(userId, name);
    }
     
     public List<OrderDTO> searchHistoryWithDate() throws NamingException, SQLException {
        return orderDAO.searchHistoryWithDate(userId, date, name);
    }
}
