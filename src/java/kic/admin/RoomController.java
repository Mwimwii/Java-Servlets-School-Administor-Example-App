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
import javax.servlet.http.HttpSession;
import kic.admin.models.Room;
/**
 *
 * @author user0
 */
public class RoomController {

    public void process(HttpServletRequest request, HttpServletResponse response,
            ServletContext application)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI().trim();
        String destUrl = requestUri.substring(requestUri.lastIndexOf("/") + 1);

        switch (destUrl) {

            case "room.form":
                request.getRequestDispatcher("/WEB-INF/jsp/roomForm.jsp").forward(request, response);
                break;      

            case "room.del":
//                HttpSession session = request.getSession();
//                Room roomToDel = (Room)session.getAttribute("rooms");
               String roomID = request.getParameter("delete");
               try {
                DbController dbController = (DbController)application.getAttribute("dbController"); //get dbcontrollwe
                dbController.delRoomFromDb(roomID);
                } catch (SQLException ex) {                 
                    
                    Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
            
        }  response.sendRedirect("dashboard");
            break;
            
            case "room.save":
                HashMap<String, String> errors = new HashMap<>();
              
                
                String[] roomId = request.getParameterValues("roomId");
                
                
                
                if ((roomId[0] == null) || (!roomId[0].matches("[a-zA-Z]{1,2}"))) {
                    errors.put("roomId (" + roomId[0] + ")",
                            "RoomId can't be empty and it's length is limited to 2 characters");
                }   int roomCap = Integer.parseInt(request.getParameter("roomCap"));
                if (roomCap > 40) {
                    errors.put("Capacity (" + roomCap + ")",
                            "Capacity can't be more than 40");
                }   if (errors.isEmpty()) {
                   ArrayList<Room> newRoom = new ArrayList<>();
                   // Room newRoom = new Room(roomId, roomCap);
                   newRoom.add(new Room (roomId[0], roomCap));
                   
                   
                   try {
                        DbController dbController = (DbController) application.getAttribute("dbController");
                        
                        dbController.insertRoomInDb(newRoom.get(0));
                        request.setAttribute("newRoom", newRoom.get(0));
                        request.getRequestDispatcher("/WEB-INF/jsp/roomDetails.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE,
                                "Error while adding a room in the DB", ex);
                        response.sendRedirect("dashboard");
                    }
                } else {
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/WEB-INF/jsp/roomForm.jsp").forward(request, response);
                }   break;
            default:
                response.sendRedirect("login.form");
                break;
        }
    }
}
