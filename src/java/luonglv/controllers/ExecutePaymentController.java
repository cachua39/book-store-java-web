/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.controllers;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import luonglv.dtos.BookDTO;
import luonglv.dtos.OrderDTO;
import luonglv.models.BookBean;
import luonglv.models.CouponBean;
import luonglv.models.OrderBean;
import luonglv.paypal.PaypalServices;
import shoppingcarts.ShoppingCart;

/**
 *
 * @author LeVaLu
 */
public class ExecutePaymentController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "customer_confirm_paypal.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");

            PaypalServices paypalServices = new PaypalServices();

            HttpSession session = request.getSession();
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("ORDER_PAYMENT");
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("CART");
            if (orderDTO != null) {
                BookBean bookBean = new BookBean();
                int quantity;
                boolean outOfStock = false;
                for (BookDTO bookDTO : orderDTO.getListBook()) {
                    bookBean.setBookId(bookDTO.getBookId());
                    quantity = bookBean.loadQuantity();
                    if (quantity < bookDTO.getQuantityCart()) {
                        request.setAttribute("ERROR", "Book:  " + bookDTO.getTitle() + " is out of stock!");
                        outOfStock = true;
                    }
                }
                if (!outOfStock) {
                    OrderBean orderBean = new OrderBean();
                    String code = orderDTO.getCouponCode();
                    if (code != null && !code.isEmpty()) {
                        CouponBean couponBean = new CouponBean();
                        couponBean.setCode(code);
                        if (!couponBean.check()) {
                            request.setAttribute("ERROR", "Discount Code is invalid!");
                        } else {
                            orderBean.setOrderDTO(orderDTO);
                            Payment payment = paypalServices.executePayment(paymentId, payerId);
                            if (payment != null) {
                                if (orderBean.insertWithDiscount()) {
                                    shoppingCart.removeAll();
                                    url = SUCCESS;
                                }

                            } else {
                                request.setAttribute("ERROR", "Order fail!");
                            }
                        }
                    } else {
                        orderBean.setOrderDTO(orderDTO);
                        Payment payment = paypalServices.executePayment(paymentId, payerId);
                        if (payment != null) {
                            if (orderBean.insertNoDiscount()) {
                                shoppingCart.removeAll();
                                url = SUCCESS;
                            }
                        } else {
                            request.setAttribute("ERROR", "Order fail!");
                        }
                    }
                }
            }
        } catch (SQLException | NamingException | PayPalRESTException e) {
            log("Error at ExecutePaymentController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
