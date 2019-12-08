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
import luonglv.dtos.BookDTO;

/**
 *
 * @author LeVaLu
 */
public class BookDAO implements Serializable {

    public List<BookDTO> loadAllByUser() throws NamingException, SQLException {
        List<BookDTO> result = null;
        BookDTO bookDTO = null;
        String bookId, title, author, photo, categoryName;
        float price;

        String sql = "select BookId, Title, Author, Price, Photo, tblCategory.Name "
                + "from tblBook inner join tblCategory on tblBook.CategoryId = tblCategory.CategoryId "
                + "where Quantity > 0 and Status = 'Active'";

        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getString("BookId");
                title = rs.getString("Title");
                author = rs.getString("Author");
                photo = rs.getString("Photo");
                categoryName = rs.getString("Name");
                price = rs.getFloat("Price");

                bookDTO = new BookDTO(bookId, title, author, photo, categoryName, price);
                result.add(bookDTO);
            }
        }

        return result;
    }

    public BookDTO findByIdToCart(String bookId) throws NamingException, SQLException {
        BookDTO result = null;

        String sql = "select Title, Price from tblBook where BookId=? and status='Active' and quantity>0";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookId);
            try (
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("Title");
                    float price = rs.getFloat("Price");
                    result = new BookDTO(bookId, title, price);
                }
            }
        }
        return result;
    }

    public List<BookDTO> loaddAllByAdmin() throws NamingException, SQLException {
        List<BookDTO> result = null;
        BookDTO bookDTO = null;
        String bookId, title, author, categoryName, photo;
        float price;
        int quantity;

        String sql = "select BookId, Title, Author, Price, Quantity, Photo, tblCategory.Name from tblBook "
                + "inner join tblCategory on tblBook.CategoryId = tblCategory.CategoryId "
                + "where Status='Active'";

        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            result = new ArrayList<>();
            while (rs.next()) {
                bookId = rs.getString("BookId");
                title = rs.getString("Title");
                author = rs.getString("Author");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                photo = rs.getString("Photo");
                categoryName = rs.getString("Name");

                bookDTO = new BookDTO(bookId, title, author, photo, categoryName, price, quantity);
                result.add(bookDTO);
            }
        }
        return result;
    }

    public int loadQuantity(String bookId) throws NamingException, SQLException {
        int result = 0;
        String sql = "select Quantity from tblBook where BookId=?";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookId);
            try (
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt("Quantity");
                }
            }
        }
        return result;
    }

    public BookDTO findByIdToAdmin(String bookId) throws NamingException, SQLException {
        BookDTO result = null;
        String title, author, categoryName, photo, description;
        float price;
        int quantity;

        String sql = "select Title, Description, Author, Price, Quantity, Photo, tblCategory.Name from tblBook "
                + "inner join tblCategory on tblBook.CategoryId = tblCategory.CategoryId "
                + "where Status='Active' and BookId=?";

        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookId);
            try (
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    title = rs.getString("Title");
                    author = rs.getString("Author");
                    description = rs.getString("Description");
                    price = rs.getFloat("Price");
                    quantity = rs.getInt("Quantity");
                    photo = rs.getString("Photo");
                    categoryName = rs.getString("Name");

                    result = new BookDTO(bookId, title, author, description, photo, categoryName, price, quantity);
                }
            }
        }
        return result;
    }

    public List<BookDTO> search(String name, String category, float min, float max) throws NamingException, SQLException {
        List<BookDTO> result = null;
        BookDTO bookDTO = null;
        String bookId, title, author, photo, categoryName;
        float price;

        String sql = "select BookId, Title, Author, Price, Photo, tblCategory.Name "
                + "from tblBook inner join tblCategory on tblBook.CategoryId = tblCategory.CategoryId "
                + "where Quantity > 0 and Status = 'Active' and Title like ? and tblCategory.Name like ? and Price between ? and ?";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + category + "%");
            ps.setFloat(3, min);
            ps.setFloat(4, max);
            try (
                    ResultSet rs = ps.executeQuery()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    bookId = rs.getString("BookId");
                    title = rs.getString("Title");
                    author = rs.getString("Author");
                    photo = rs.getString("Photo");
                    categoryName = rs.getString("Name");
                    price = rs.getFloat("Price");

                    bookDTO = new BookDTO(bookId, title, author, photo, categoryName, price);
                    result.add(bookDTO);
                }
            }
        }
        return result;
    }

    public boolean inset(BookDTO bookDTO) throws NamingException, SQLException {
        boolean result = false;

        String sql = "insert into tblBook(BookId, Title, Author, Description, "
                + "CategoryId, Price, Quantity, Photo, Status, ImportDate) "
                + "values(?,?,?,?,(select CategoryId from tblCategory where Name=?),?,?,?,'Active',GETDATE())";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookDTO.getBookId());
            ps.setString(2, bookDTO.getTitle());
            ps.setString(3, bookDTO.getAuthor());
            ps.setString(4, bookDTO.getDescription());
            ps.setString(5, bookDTO.getCategoryName());
            ps.setFloat(6, bookDTO.getPrice());
            ps.setInt(7, bookDTO.getQuantity());
            ps.setString(8, bookDTO.getPhoto());

            result = ps.executeUpdate() > 0;
        }
        return result;
    }

    public boolean update(BookDTO bookDTO) throws NamingException, SQLException {
        boolean result = false;

        String sql = "update tblBook set Title=?, Author=?, Description=?, Price=?, "
                + "Quantity=?, Photo=?, ImportDate=GETDATE(), "
                + "CategoryId=(select CategoryId from tblCategory where Name=?) "
                + "where BookId=?";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookDTO.getTitle());
            ps.setString(2, bookDTO.getAuthor());
            ps.setString(3, bookDTO.getDescription());
            ps.setFloat(4, bookDTO.getPrice());
            ps.setInt(5, bookDTO.getQuantity());
            ps.setString(6, bookDTO.getPhoto());
            ps.setString(7, bookDTO.getCategoryName());
            ps.setString(8, bookDTO.getBookId());

            result = ps.executeUpdate() > 0;
        }
        return result;
    }

    public boolean delete(String bookId) throws NamingException, SQLException {
        boolean result = false;

        String sql = "update tblBook set Status='Inactive' where BookId=? and Status='Active'";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bookId);

            result = ps.executeUpdate() > 0;
        }
        return result;
    }

}
