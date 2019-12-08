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
import luonglv.daos.BookDAO;
import luonglv.dtos.BookDTO;

/**
 *
 * @author LeVaLu
 */
public class BookBean implements Serializable {
    private final BookDAO bookDAO;
    private String bookId, categoryName, title;
    private BookDTO bookDTO;
    private float min, max;

    public BookBean() {
        bookDAO = new BookDAO();
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
    
    
    
    public List<BookDTO> loadAllByUser() throws NamingException, SQLException {
        return bookDAO.loadAllByUser();
    }
    
    public BookDTO findByIdToCart() throws NamingException, SQLException {
        return bookDAO.findByIdToCart(bookId);
    }
    
    public List<BookDTO> loadAllByAdmin() throws NamingException, SQLException {
        return bookDAO.loaddAllByAdmin();
    }
    
    public BookDTO findByIdToAdmin() throws NamingException, SQLException {
        return bookDAO.findByIdToAdmin(bookId);
    }
    
    public boolean insert() throws NamingException, SQLException {
        return bookDAO.inset(bookDTO);
    }
    
    public boolean update() throws NamingException, SQLException {
        return bookDAO.update(bookDTO);
    }
    
    public boolean delete() throws NamingException, SQLException {
        return bookDAO.delete(bookId);
    }
    
    public int loadQuantity() throws NamingException, SQLException {
        return bookDAO.loadQuantity(bookId);
    }
    
    public List<BookDTO> search() throws NamingException, SQLException {
        return bookDAO.search(title, categoryName, min, max);
    }
}
