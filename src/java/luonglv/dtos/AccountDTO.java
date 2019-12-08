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
public class AccountDTO implements Serializable{
    private String userId, password, role;
    private String fullname, email, phone, address;
    
    public AccountDTO() {
        
    }

    public AccountDTO(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public AccountDTO(String userId, String password, String role, String fullname, String email, String phone, String address) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
