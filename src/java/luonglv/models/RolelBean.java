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
import luonglv.daos.RoleDAO;
import luonglv.dtos.RoleDTO;

/**
 *
 * @author LeVaLu
 */
public class RolelBean implements Serializable{
    public List<RoleDTO> loadAll() throws NamingException, SQLException {
        RoleDAO roleDAO = new RoleDAO();
        return roleDAO.loadAll();
    }
    
}
