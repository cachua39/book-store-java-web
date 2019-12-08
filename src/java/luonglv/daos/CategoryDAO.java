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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import luonglv.db.DBConnection;
import luonglv.dtos.CategoryDTO;

/**
 *
 * @author LeVaLu
 */
public class CategoryDAO implements Serializable{
    
    public List<CategoryDTO> loadAdd() throws NamingException, SQLException {
        List<CategoryDTO> result = null;
        CategoryDTO categoryDTO = null;
        int id;
        String name;
        
        String sql = "select CategoryId, Name from tblCategory";
        try(
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            result = new ArrayList();
            while(rs.next()) {
                id = rs.getInt("CategoryId");
                name = rs.getString("Name");
                categoryDTO = new CategoryDTO(id, name);
                result.add(categoryDTO);
            }
        }
        
        return result;
    }
    
}
