/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import luonglv.dtos.BookDTO;
import luonglv.dtos.BookError;
import luonglv.models.BookBean;
import luonglv.uitls.ImageUtils;

/**
 *
 * @author LeVaLu
 */
@MultipartConfig
public class InsertBookByAdminController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin.jsp";
    private static final String INVALID = "admin_create_book.jsp";
    
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
        try{
            String bookId = request.getParameter("txtBookId");
            String title = request.getParameter("txtTitle").trim();
            String author = request.getParameter("txtAuthor").trim();
            String category = request.getParameter("optionCategory");
            String strPrice = request.getParameter("txtPrice");
            String strQuantity = request.getParameter("txtQuantity");
            String description = request.getParameter("txtDescription").trim();
            Part filePhoto = request.getPart("filePhoto");
            
            /**
             * Write photo to server
             */
            ImageUtils.writeImageToDisk(getServletContext(), filePhoto);
            
            /**
             * Modify the photo's path before store to database
             */
            String photo = Paths.get(filePhoto.getSubmittedFileName()).getFileName().toString();
            photo = "assets/" + photo;
            
            /**
             * Convert Price and Quantity from String type to correct type
             */
            float price = Float.parseFloat(strPrice);
            int quantity = Integer.parseInt(strQuantity);
            
            BookDTO bookDTO = new BookDTO(bookId, title, author, description, photo, category, price, quantity);
            BookBean bookBean = new BookBean();
            bookBean.setBookDTO(bookDTO);
            
            if(bookBean.insert()) {
                url = SUCCESS;
            }else{
                request.setAttribute("ERROR", "Insert book failed!");
            }
        }catch(IOException | NumberFormatException | SQLException | NamingException | ServletException e) {
            if(e.getMessage() != null && e.getMessage().contains("duplicate")) {
                BookError bookError = new BookError();
                bookError.setBookIdError("Book ID is existed!");
                request.setAttribute("INVALID", bookError);
                url = INVALID;
            }
            log("Error at InsertBookByAdminController: " + e.getMessage());
        }finally{
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
