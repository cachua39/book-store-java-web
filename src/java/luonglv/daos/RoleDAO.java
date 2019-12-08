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
import luonglv.dtos.RoleDTO;

/**
 *
 * @author LeVaLu
 */
public class RoleDAO implements Serializable {

    public List<RoleDTO> loadAll() throws NamingException, SQLException {
        List<RoleDTO> result = null;
        RoleDTO roleDTO = null;
        String sql = "select RoleId, Name from tblRole";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            result = new ArrayList<>();
            while (rs.next()) {
                roleDTO = new RoleDTO(rs.getInt("RoleId"), rs.getString("Name"));
                result.add(roleDTO);
            }
        }

        return result;
    }
}
