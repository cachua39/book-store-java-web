/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.models;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import luonglv.daos.CouponDAO;
import luonglv.dtos.CouponDTO;

/**
 *
 * @author LeVaLu
 */
public class CouponBean implements Serializable{
    private final CouponDAO couponDAO;
    private String code;
    private CouponDTO couponDTO;

    public CouponBean() {
        couponDAO = new CouponDAO();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CouponDTO getCouponDTO() {
        return couponDTO;
    }

    public void setCouponDTO(CouponDTO couponDTO) {
        this.couponDTO = couponDTO;
    }
    
    public CouponDTO findByCode() throws NamingException, SQLException {
        return couponDAO.findByCode(code);
    }
    
    public boolean check() throws NamingException, SQLException {
        return couponDAO.check(code);
    }
    
    public boolean useCode() throws NamingException, SQLException {
        return couponDAO.useCode(couponDTO);
    }
}
