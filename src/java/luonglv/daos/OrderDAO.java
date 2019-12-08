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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import luonglv.db.DBConnection;
import luonglv.dtos.BookDTO;
import luonglv.dtos.OrderDTO;

/**
 *
 * @author LeVaLu
 */
public class OrderDAO implements Serializable {

    public boolean insertWithDiscount(OrderDTO orderDTO) throws NamingException, SQLException {
        boolean result = false;

        try (
                Connection con = DBConnection.makeConnection();
                Statement st = con.createStatement()) {
            con.setAutoCommit(false);

            // Add Order to database
            String sqlOrder = "insert into tblOrder(UserId, CouponCode, Total, CreatedDate) "
                    + "values('" + orderDTO.getUserId() + "',"
                    + "'" + orderDTO.getCouponCode() + "',"
                    + "'" + orderDTO.getTotal() + "',"
                    + "GETDATE())";
            st.execute(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            int orderId = 0;
            try (
                    ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

            // Add Detail to database and Subtract quantity
            String sqlDetail;
            String sqlQuantity;
            for (BookDTO book : orderDTO.getListBook()) {
                float subTotal = book.getPrice() * book.getQuantityCart();
                sqlDetail = "insert into tblOrderDetail(OrderId, BookId, Quantity, SubTotal, Title) "
                        + "values('" + orderId + "','" + book.getBookId() + "',"
                        + "'" + book.getQuantityCart() + "','" + subTotal + "', '"+ book.getTitle() +"')";
                st.addBatch(sqlDetail);

                sqlQuantity = "update tblBook set Quantity=Quantity-" + book.getQuantityCart() + " where BookId='" + book.getBookId() + "'";
                st.addBatch(sqlQuantity);
            }

            // Updaet Discount Code
            String sqlDiscount = "update tblCoupon set Status='Used', UserId='" + orderDTO.getUserId() + "' "
                    + "where Code='" + orderDTO.getCouponCode() + "' "
                    + "and StartDate<=GETDATE() and EndDate>=GETDATE() "
                    + "and Status='NotUsed' and UserId='Empty'";
            st.addBatch(sqlDiscount);

            int resultArr[] = st.executeBatch();
            result = resultArr[0] > 0;
            int i = 1;
            while (result && i < resultArr.length) {
                result = result && resultArr[i] > 0;
                i++;
            }
            if (result) {
                con.commit();
            } else {
                con.rollback();
            }
        }
        return result;
    }

    public boolean insertNoDiscount(OrderDTO orderDTO) throws NamingException, SQLException {
        boolean result = false;

        try (
                Connection con = DBConnection.makeConnection();
                Statement st = con.createStatement()) {
            con.setAutoCommit(false);

            // Add Order to database
            String sqlOrder = "insert into tblOrder(UserId, CouponCode, Total, CreatedDate) "
                    + "values('" + orderDTO.getUserId() + "',"
                    + "'" + orderDTO.getCouponCode() + "',"
                    + "'" + orderDTO.getTotal() + "',"
                    + "GETDATE())";
            st.execute(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            int orderId = 0;
            try (
                    ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

            // Add Detail to database and Subtract quantity
            String sqlDetail;
            String sqlQuantity;
            for (BookDTO book : orderDTO.getListBook()) {
                float subTotal = book.getPrice() * book.getQuantityCart();
                sqlDetail = "insert into tblOrderDetail(OrderId, BookId, Quantity, SubTotal, Title) "
                        + "values('" + orderId + "','" + book.getBookId() + "',"
                        + "'" + book.getQuantityCart() + "','" + subTotal + "','"+ book.getTitle() +"')";
                st.addBatch(sqlDetail);

                sqlQuantity = "update tblBook set Quantity=Quantity-" + book.getQuantityCart() + " where BookId='" + book.getBookId() + "'";
                st.addBatch(sqlQuantity);
            }

            int resultArr[] = st.executeBatch();
            result = resultArr[0] > 0;
            int i = 1;
            while (result && i < resultArr.length) {
                result = result && resultArr[i] > 0;
                i++;
            }

            if (result) {
                con.commit();
            } else {
                con.rollback();
            }
        }
        return result;
    }

    public List<OrderDTO> loadHistory(String userId) throws NamingException, SQLException {
        List<OrderDTO> result = null;
        List<BookDTO> listBook = null;
        OrderDTO orderDTO = null;
        BookDTO bookDTO = null;

        String sqlOrder = "select OrderId, Total, CreatedDate from tblOrder where UserId = ?";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sqlOrder)) {
            ps.setString(1, userId);
            try (
                    ResultSet rs = ps.executeQuery()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    orderDTO = new OrderDTO();
                    orderDTO.setUserId(userId);
                    orderDTO.setOrderId(rs.getInt("OrderId"));
                    orderDTO.setTotal(rs.getFloat("Total"));
                    orderDTO.setCreatedDate(rs.getTimestamp("CreatedDate").toString());

                    String sqlDetail = "select tblOrderDetail.Quantity, SubTotal, Title from tblOrderDetail "
                            + "where OrderId=?";

                    try (
                            PreparedStatement ps2 = con.prepareStatement(sqlDetail)) {
                        ps2.setInt(1, orderDTO.getOrderId());
                        try (
                                ResultSet rsDetail = ps2.executeQuery()) {
                            listBook = new ArrayList<>();
                            while (rsDetail.next()) {
                                bookDTO = new BookDTO();
                                bookDTO.setTitle(rsDetail.getString("Title"));
                                bookDTO.setQuantity(rsDetail.getInt("Quantity"));
                                bookDTO.setSubTotal(rsDetail.getFloat("SubTotal"));
                                listBook.add(bookDTO);
                            }
                        }
                    }
                    orderDTO.setListBook(listBook);
                    result.add(orderDTO);
                }
            }

        }
        return result;
    }

    public List<OrderDTO> searchHistoryWithDate(String userId, String date, String name) throws NamingException, SQLException {
        List<OrderDTO> result = null;
        List<BookDTO> listBook = null;
        OrderDTO orderDTO = null;
        BookDTO bookDTO = null;

        String sqlOrder = "select distinct tblOrder.OrderId, Total, CreatedDate from tblOrder "
                + "inner join tblOrderDetail on tblOrder.OrderId = tblOrderDetail.OrderId "
                + "where UserId = ? and Title like ? and CAST(CreatedDate as Date) = CAST(? as Date)";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sqlOrder)) {
            ps.setString(1, userId);
            ps.setString(2, "%"+ name +"%");
            ps.setString(3, date);
            try (
                    ResultSet rs = ps.executeQuery()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    orderDTO = new OrderDTO();
                    orderDTO.setUserId(userId);
                    orderDTO.setOrderId(rs.getInt("OrderId"));
                    orderDTO.setTotal(rs.getFloat("Total"));
                    orderDTO.setCreatedDate(rs.getTimestamp("CreatedDate").toString());

                    String sqlDetail = "select tblOrderDetail.Quantity, SubTotal, Title from tblOrderDetail "
                            + "where OrderId=?";

                    try (
                            PreparedStatement ps2 = con.prepareStatement(sqlDetail)) {
                        ps2.setInt(1, orderDTO.getOrderId());
                        try (
                                ResultSet rsDetail = ps2.executeQuery()) {
                            listBook = new ArrayList<>();
                            while (rsDetail.next()) {
                                bookDTO = new BookDTO();
                                bookDTO.setTitle(rsDetail.getString("Title"));
                                bookDTO.setQuantity(rsDetail.getInt("Quantity"));
                                bookDTO.setSubTotal(rsDetail.getFloat("SubTotal"));
                                listBook.add(bookDTO);
                            }
                        }
                    }
                    if (listBook.size() > 0) {
                        orderDTO.setListBook(listBook);
                        result.add(orderDTO);
                    }
                }
            }

        }
        return result;
    }

    public List<OrderDTO> searchHistory(String userId, String name) throws NamingException, SQLException {
        List<OrderDTO> result = null;
        List<BookDTO> listBook = null;
        OrderDTO orderDTO = null;
        BookDTO bookDTO = null;

        String sqlOrder = "select distinct tblOrder.OrderId, Total, CreatedDate from tblOrder "
                + "inner join tblOrderDetail on tblOrder.OrderId = tblOrderDetail.OrderId "
                + "where UserId = ? and Title like ?";
        try (
                Connection con = DBConnection.makeConnection();
                PreparedStatement ps = con.prepareStatement(sqlOrder)) {
            ps.setString(1, userId);
            ps.setString(2, "%"+name+"%");
            try (
                    ResultSet rs = ps.executeQuery()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    orderDTO = new OrderDTO();
                    orderDTO.setUserId(userId);
                    orderDTO.setOrderId(rs.getInt("OrderId"));
                    orderDTO.setTotal(rs.getFloat("Total"));
                    orderDTO.setCreatedDate(rs.getTimestamp("CreatedDate").toString());

                    String sqlDetail = "select tblOrderDetail.Quantity, SubTotal, Title from tblOrderDetail where OrderId=?";
                    try (
                            PreparedStatement ps2 = con.prepareStatement(sqlDetail)) {
                        ps2.setInt(1, orderDTO.getOrderId());
                        try (
                                ResultSet rsDetail = ps2.executeQuery()) {
                            listBook = new ArrayList<>();
                            while (rsDetail.next()) {
                                bookDTO = new BookDTO();
                                bookDTO.setTitle(rsDetail.getString("Title"));
                                bookDTO.setQuantity(rsDetail.getInt("Quantity"));
                                bookDTO.setSubTotal(rsDetail.getFloat("SubTotal"));
                                listBook.add(bookDTO);
                            }
                            orderDTO.setListBook(listBook);
                        }
                    }
                    result.add(orderDTO);
                }
            }

        }
        return result;
    }
}
