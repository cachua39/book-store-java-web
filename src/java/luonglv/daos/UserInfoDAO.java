/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import luonglv.db.DBConnection;
import luonglv.dtos.UserInfoDTO;

/**
 *
 * @author LeVaLu
 */
public class UserInfoDAO implements Serializable{
    public UserInfoDTO findById(String id) throws NamingException, SQLException {
        UserInfoDTO userInfoDTO  = null;
        String fullname, email, phone, address;
        
        String sql = "select Fullname, Email, Phone, Address from tblUserInfo where UserId = ?";
        try(
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try(
                    ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    fullname = rs.getString("Fullname");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                address = rs.getString("Address");
                
                userInfoDTO = new UserInfoDTO(id, fullname, email, phone, address);
                }
            }
        }
        
        return  userInfoDTO;
    }
}
