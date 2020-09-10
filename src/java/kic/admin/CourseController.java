/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kic.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kic.admin.models.Course;

/**
 *
 * @author user
 */
public class CourseController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext application)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI().trim();
        String destUrl = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        switch (destUrl) {

            case "course.form":
                request.getRequestDispatcher("/WEB-INF/jsp/courseForm.jsp").forward(request, response);
                break;      

            case "course.del":
               String roomID = request.getParameter("delete");
               try {
                DbController dbController = (DbController)application.getAttribute("dbController"); //get dbcontrollwe
                dbController.delRoomFromDb(roomID);
                } catch (SQLException ex) {                 
            Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
            
        }  response.sendRedirect("dashboard");
            break;
            
            case "course.save":
                HashMap<String, String> errors = new HashMap<>();
//                String categoryId   =  ("c");
 //               String categoryName =  ("ca");
                
                String[] courseId = request.getParameterValues("courseId");
               String courseCode = request.getParameter("courseCode");
                String courseTitle = request.getParameter("courseTitle");
                String shortName = request.getParameter("shortName");
                String courseCat = request.getParameter("courseCat");
                
                int courseCredits = Integer.parseInt(request.getParameter("courseCredits"));
                String isElective = request.getParameter("isElective");
                
                if (errors.isEmpty()) {
                   
                    Course newCourse = new Course(courseId[0], courseCode, courseTitle, shortName, courseCat, courseCredits, isElective);
                   //need an array for the room 
                    try {
                        DbController dbController = (DbController) application.getAttribute("dbController");
                        dbController.insertCourseInDb(newCourse);
                        request.setAttribute("newCourse", newCourse);
                        request.getRequestDispatcher("/WEB-INF/jsp/courseDetails.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(oldCourseController.class.getName()).log(Level.SEVERE,
                                "Error while adding a room in the DB", ex);
                        response.sendRedirect("dashboard");
                    }
                } else {
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/jsp/courseForm.jsp").forward(request, response);
                }   break;
            default:
                response.sendRedirect("login.form");
                break;
        }
    }

    
    
    
}
