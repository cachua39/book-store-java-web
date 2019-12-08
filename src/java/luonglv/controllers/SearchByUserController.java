/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luonglv.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luonglv.dtos.BookDTO;
import luonglv.models.BookBean;

/**
 *
 * @author LeVaLu
 */
public class SearchByUserController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String title = request.getParameter("txtTitle");
            String categoryName = request.getParameter("category");
            String strMin = request.getParameter("txtMin");
            String strMax = request.getParameter("txtMax");
            
            float min = 0;
            float max = Float.MAX_VALUE;
            if(!strMin.isEmpty()) {
                min = Float.parseFloat(strMin);
            }
            if(!strMax.isEmpty()) {
                max = Float.parseFloat(strMax);
            }
            if(min > max){
                float temp = min;
                min = max;
                max = temp;
            }
            if(min == 0 && max == 0){
                max = Float.MAX_VALUE;
            }
            
            if(categoryName.equals("All")){
                categoryName = "";
            }
            
            BookBean bookBean = new BookBean();
            bookBean.setTitle(title);
            bookBean.setCategoryName(categoryName);
            bookBean.setMin(min);
            bookBean.setMax(max);
            
            List<BookDTO> result = bookBean.search();
            Type type = new TypeToken<List<BookDTO>>(){}.getType();
            String json = new Gson().toJson(result, type);
            out.write(json);
        }catch(Exception e){
            log("Error at SearchByUserController: " + e.getMessage());
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
