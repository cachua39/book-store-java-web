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
import luonglv.daos.CategoryDAO;
import luonglv.dtos.CategoryDTO;

/**
 *
 * @author LeVaLu
 */
public class CategoryBean implements Serializable{

    public CategoryBean() {
    }
    
    public List<CategoryDTO> loadAll() throws NamingException, SQLException {
        CategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.loadAdd();
    }
}
