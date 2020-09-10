package kic.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import kic.admin.models.Course;
import kic.admin.models.Category;
import kic.admin.models.Room;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author user0
 */
public class DbController {

    private static Connection dbConn;
    

    public DbController(String pathToDbFile) throws ClassNotFoundException, SQLException {
        if (dbConn == null) {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            dbConn = DriverManager.getConnection("jdbc:sqlite:" + pathToDbFile);
        }
    }

    public void insertRoomInDb(Room newRoom) throws SQLException {
        dbConn.createStatement().executeUpdate("insert into room values('"+ newRoom.getId() + "', " + newRoom.getCapacity() + ", 1 );");
       }
    
     public void insertCourseInDb(Course newCourse) throws SQLException {
        dbConn.createStatement().executeUpdate("insert into course values('"+ newCourse.getid() + "', '" + newCourse.getCode() + "',  '" + newCourse.getTitle() + "',  '" + newCourse.getShort() + "',  '" + newCourse.getCat() + "',  '" + newCourse.getCredits() + "',  '" + newCourse.getElect() + "', 1);");
       } 
     
     public void insertCategoryInDb(Category newCategory) throws SQLException {
        dbConn.createStatement().executeUpdate("insert into categories values('"+ newCategory.getId() + "', '" + newCategory.getName() + "', 1 );");
       }
    
//    public void delRoomFromDb(String roomId) throws SQLException {
//        dbConn.createStatement().executeUpdate("delete from room where ID ='"+ roomId +"';");
//    }    
    public void delRoomFromDb(String roomId) throws SQLException {
        dbConn.createStatement().executeUpdate("UPDATE room SET status = 0 WHERE id ='" + roomId + "';");
        String newRoom = roomId +("_old");
        dbConn.createStatement().executeUpdate("UPDATE room SET id ='" + counterDB(newRoom) +"' WHERE id ='"+ roomId + "';");
        
    }
    
     public String counterDB(String roomID) throws SQLException{
         ResultSet result = dbConn.createStatement().executeQuery("select COUNT(id) from room WHERE id like '"+roomID+"%';");
         int newInt = result.getInt("COUNT(id)");
         if (newInt!=0) roomID+=String.valueOf(newInt);
         
         return roomID;
}
     
  public void delCourseFromDb(String roomId) throws SQLException {
        dbConn.createStatement().executeUpdate("UPDATE room SET status = 0 WHERE id ='" + roomId + "';");
        String newRoom = roomId +("_old");
        dbConn.createStatement().executeUpdate("UPDATE room SET id ='" + counterDB(newRoom) +"' WHERE id ='"+ roomId + "';");
        
    }
     
     
    public ResultSet fetchRoomsFromDb() throws SQLException{
        return dbConn.createStatement().executeQuery("select * from room where status=1;");
    }

    public ResultSet fetchCourseFromDb() throws SQLException {
       return dbConn.createStatement().executeQuery("select * from course where status=1;");        
    }

    public ResultSet fetchCategoryFromDb() throws SQLException {
      return dbConn.createStatement().executeQuery("select * from categories where status = 1;");
     }
    
    
}
