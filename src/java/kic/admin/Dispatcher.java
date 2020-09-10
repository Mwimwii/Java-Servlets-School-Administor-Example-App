package kic.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.sqlite.SQLiteConfig;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

/**
 *
 * @author user0
 */
public class Dispatcher extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String requestUri = request.getRequestURI().trim();
        // trim the string 
        String destUrl = requestUri.substring(requestUri.lastIndexOf("/"));
        // get 
        
        switch (destUrl) {
            
            case "/login.action":
            case "/login.form":
                new LoginController().process(request, response);
                break;
           
            case "/dashboard":
                new DashboardController().process(request, response);
                break;
           
            case "/room.form":            
            case "/room.save":
            case "/room.del": // no spaces in the case
                new RoomController().process(request, response, getServletContext());
                break;
                
            case "/course.form":
            case "/course.save":
            case "/course.del":
                new oldCourseController().process(request, response, getServletContext());
                break;
             
            case "/category.form":
            case "/category.save":
            case "/category.del":
                new CategoryController().process(request, response, getServletContext());
                break;
                
                
                
            case "/logout":
                new LogoutController().process(request, response);
                break;
                
            default:
                response.sendRedirect("login.form");

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

    @Override
    public void init() throws ServletException {
        super.init();
//        SQLiteConnectionPoolDataSource ds;
        try {
            DbController dbController = new DbController(
                    getServletContext().getRealPath("/WEB-INF/db/wad_project_db.db3"));
            this.getServletContext().setAttribute("dbController", dbController);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE,
                    "DB Driver not found! Check your jars", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE,
                    "Error while connecting to the DB", ex);
        }
    }
}
