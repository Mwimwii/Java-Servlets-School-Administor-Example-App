package kic.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kic.admin.models.Category;
import kic.admin.models.Course;
import kic.admin.models.Room;
//Dashboard filter for the 
/**
 *
 * @author user0
 */
public class DashboardFilter implements Filter {

    /**
     *
     */
    protected FilterConfig filterConfig;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException { //
        

        ServletContext application = filterConfig.getServletContext();
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
     
        if (httpRequest.getSession().getAttribute("userName") != null) {
     
            DbController dbController = (DbController) application.getAttribute("dbController");
            
            ArrayList<Room> rooms = new ArrayList<>();
            
            ArrayList<Course> courses = new ArrayList<>();
            
            ArrayList<Category> categories = new ArrayList<>();
            
            try (ResultSet results = dbController.fetchRoomsFromDb()){
                
                while (results.next()) {
                    rooms.add  (new Room(results.getString("id"), results.getInt("capacity")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardFilter.class.getName()).log(Level.SEVERE,
                        "SQL error in the DashboardFilter.", ex);
            }
            
            try (ResultSet results = dbController.fetchCourseFromDb()){
                
                while (results.next()) {
                   
                    courses.add (new Course(results.getString("id"), results.getString("code"), results.getString("title"), results.getString("short_name"), results.getString("category"), results.getInt("credits"), results.getString("is_elective")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardFilter.class.getName()).log(Level.SEVERE,
                        "SQL error in the DashboardFilter.", ex);
            }
            
            try (ResultSet results = dbController.fetchCategoryFromDb()){
                
                while (results.next()) {
                   
                    categories.add(new Category(results.getString("id"), results.getString("name")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardFilter.class.getName()).log(Level.SEVERE,
                        "SQL error in the DashboardFilter.", ex);
            }
            
            
           request.setAttribute("rooms", rooms);
           request.setAttribute("courses", courses);
           request.setAttribute("categories", categories);

            //Continue to the next stage
            chain.doFilter(request, response);
        
        } else {
            ((HttpServletResponse) response).sendRedirect("login.form");
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        Logger.getLogger(DashboardFilter.class.getName()).log(Level.INFO,
                "DashboardFilter:Initializing filter");
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        return ("DashboardFilter()");
    }
}
