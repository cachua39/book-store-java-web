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
public class CouponDTO implements Serializable{
    private String code;
    private float value;
    private String startDate, endDate, userId, status;
    
    public CouponDTO (){
        
    }

    public CouponDTO(String code, float value, String startDate, String endDate, String userId, String status) {
        this.code = code;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

   
    
    
}
