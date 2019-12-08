/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.models;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import luonglv.daos.UserInfoDAO;
import luonglv.dtos.UserInfoDTO;

/**
 *
 * @author LeVaLu
 */
public class UserInfoBean implements Serializable {
    private final  UserInfoDAO userInfoDAO;
    private String userId;

    public UserInfoBean() {
        userInfoDAO = new UserInfoDAO();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public UserInfoDTO findById() throws NamingException, SQLException {
        return userInfoDAO.findById(userId);
    }
}
