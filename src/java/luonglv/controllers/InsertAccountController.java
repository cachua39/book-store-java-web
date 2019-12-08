/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luonglv.dtos.AccountDTO;
import luonglv.dtos.AccountError;
import luonglv.models.AccountBean;

/**
 *
 * @author LeVaLu
 */
public class InsertAccountController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";
    private static final String INVALID = "admin_create_account.jsp";

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
            String userId = request.getParameter("txtUserId").trim();
            String password = request.getParameter("txtPassword").trim();
            String fullname = request.getParameter("txtFullname").trim();
            String email = request.getParameter("txtEmail").trim();
            String phone = request.getParameter("txtPhone").trim();
            String role = request.getParameter("cbRole");
            String address = request.getParameter("txtAddress");

            AccountDTO accountDTO = new AccountDTO(userId, password, role, fullname, email, phone, address);
            AccountBean accountBean = new AccountBean();
            accountBean.setAccountDTO(accountDTO);
            if (accountBean.insert()) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Insert failed!");
            }
        } catch (SQLException | NamingException e) {
            if (e.getMessage() != null && e.getMessage().contains("duplicate")) {
                AccountError accountError = new AccountError();
                accountError.setAccountError("User is existed!");
                request.setAttribute("INVALID", accountError);
                url = INVALID;
            }
            log("Error at InsertController: " + e.getMessage());
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
