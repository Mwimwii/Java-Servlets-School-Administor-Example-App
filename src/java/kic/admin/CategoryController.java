/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kic.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kic.admin.models.Category;

/**
 *
 * @author user
 */
public class CategoryController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext application)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI().trim();
        String destUrl = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        switch (destUrl) {

            case "category.form":
                request.getRequestDispatcher("/WEB-INF/jsp/categoryForm.jsp").forward(request, response);
                break;      

//            case "category.del":
//               String roomID = request.getParameter("delete");
//               try {
//                DbController dbController = (DbController)application.getAttribute("dbController"); //get dbcontrollwe
//                dbController.delRoomFromDb(roomID);
//                } catch (SQLException ex) {                 
//            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }  response.sendRedirect("dashboard");
//            break;
//            
            case "category.save":
                HashMap<String, String> errors = new HashMap<>();
//                String categoryId   =  ("c");
 //               String categoryName =  ("ca");
                String categoryId = request.getParameter("categoryId");
                String categoryName = request.getParameter("categoryName");
                  if (errors.isEmpty()) {
                    Category newCategory = new Category(categoryId, categoryName);
                    try {
                        DbController dbController = (DbController) application.getAttribute("dbController");
                        dbController.insertCategoryInDb(newCategory);
                        request.setAttribute("newCategory", newCategory);
                        
                        request.getRequestDispatcher("/WEB-INF/jsp/categoryDetails.jsp").forward(request, response);
                    }
                    
                    catch (SQLException ex) {
                        Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE,
                                "Error while adding a room in the DB", ex);
                        response.sendRedirect("dashboard");
                    }
                } else {
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/jsp/categoryForm.jsp").forward(request, response);
                }   break;
            default:
                response.sendRedirect("login.form");
                break;
        }
    }

    
    
    
}
