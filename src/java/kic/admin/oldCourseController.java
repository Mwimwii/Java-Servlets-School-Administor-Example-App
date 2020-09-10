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
public class oldCourseController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext application)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI().trim();
        String destUrl = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        switch (destUrl) {

            case "course.form":
                request.getRequestDispatcher("/WEB-INF/jsp/courseForm.jsp").forward(request, response);
                break;
            
            case "course.save":
                HashMap<String, String> newErrors = new HashMap<>();
               
                
                String[] courseId = request.getParameterValues("courseId"); // return array pf courseId
                String[] courseCode = request.getParameterValues("courseCode");
                String[] courseTitle = request.getParameterValues("courseTitle");
                String[] shortName = request.getParameterValues("shortName");
                String[] courseCat = request.getParameterValues("courseCat");
                String[] courseCredits = request.getParameterValues("courseCredits");
                String[] isElective = request.getParameterValues("isElective");

                
                    if (newErrors.isEmpty()) {
                   
                        ArrayList<Course> newCourses = new ArrayList<>();
                  //while (courseId.)
                  
                  for (int i = 0; i < courseId.length; i++)  { 
                    
                      newCourses.add(new Course(courseId[i], courseCode[i], courseTitle[i], shortName[i], courseCat[i], Integer.parseInt(courseCredits[i]), isElective[i]) );
                  }
                     try {
                        
                        DbController dbController = (DbController) application.getAttribute("dbController");
                        
                        for (Course courseAdd : newCourses ){
                        dbController.insertCourseInDb(courseAdd);
                        }
                        //request.setAttribute("newNum", courseId.length);
                        request.setAttribute("newCourses", newCourses);
                        
                        
                        request.getRequestDispatcher("/WEB-INF/jsp/courseDetails.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(oldCourseController.class.getName()).log(Level.SEVERE,
                                "Error while adding a room in the DB", ex);
                        response.sendRedirect("dashboard");
                    }
                } else {
                    request.setAttribute("errors", newErrors);
                    request.getRequestDispatcher("/WEB-INF/jsp/courseForm.jsp").forward(request, response);
                }   break;
                
            default:
                response.sendRedirect("login.form");
                break;
        }
    }
}

