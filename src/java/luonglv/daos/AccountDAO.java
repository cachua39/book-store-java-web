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
import java.sql.Statement;
import javax.naming.NamingException;
import luonglv.db.DBConnection;
import luonglv.dtos.AccountDTO;

/**
 *
 * @author LeVaLu
 */
public class AccountDAO implements Serializable{
    public String checkLogin(String userId, String password) throws NamingException, SQLException {
        String roleName = "failed";
        
        String sql = "select tblRole.Name from tblRole "
                + "inner join tblAccount on tblRole.RoleId = tblAccount.RoleId "
                + "where UserId = ? and Password = ?";
        
        try(
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setString(2, password);
            
            try(
                    ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    roleName = rs.getString("Name");
                }
            }
        }
        return roleName;
    }
    
    public boolean insert(AccountDTO accountDTO) throws NamingException, SQLException {
        boolean result = false;
        try (
                Connection con = DBConnection.makeConnection();
                Statement st = con.createStatement()) {
            con.setAutoCommit(false);


            String sqlAccount = "insert into tblAccount(UserId, Password, RoleId, CreatedDate) values('"+ accountDTO.getUserId() +"','"+ accountDTO.getPassword() +"',(select RoleId from tblRole where Name='"+ accountDTO.getRole() +"'), GETDATE())";
            
            String sqlUserInfo = "insert into tblUserInfo(UserId, Fullname, Email, Phone, Address) "
                    + "values('"+accountDTO.getUserId()+"','"+accountDTO.getFullname()+"',"
                    + "'"+accountDTO.getEmail()+"','"+accountDTO.getPhone()+"',"
                    + "'"+accountDTO.getPassword()+"')";
            
            st.addBatch(sqlAccount);
            st.addBatch(sqlUserInfo);
            
            int resultArr[] = st.executeBatch();
            result = resultArr[0] > 0 && resultArr[1] > 0;
            con.commit();
        }

        return result;
    }
}
