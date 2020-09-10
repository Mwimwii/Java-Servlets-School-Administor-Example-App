package kic.admin.models;

/**
 *
 * @author user0
 */
public class Course {
    
    public  String id;
    private String code;
    private String title;
    private String shortName;
    private String catId;
    private int credits;
    private String isElective;
   
    public String getid(){
    
        return id;
    }
    public void setid(String id){
        
        this.id = id;
    }
    
    public String getCode(){
    
        return code;
    }
    public void setCode(String code){
    
        this.code=code;
    }
    
    public String getTitle(){
        
        return title;
    }
    public void setTitle(String title){
    
        this.title=title;
    }
    
    public String getShort(){
    
        return shortName;
    }
    public void setShort(String shortName){
    
        this.shortName = shortName;
    }
    
    public String getCat(){
    
        return catId;
        
    }
    public void setCat (String catId){
    
    this.catId = catId; 
            
    }
    
    public int getCredits(){
    
        return credits;
    }
    
    public void setCredits(int credits){
    
    this.credits = credits;
    
    }
    
    public String getElect(){

        return isElective;
    }
    public void setElect(String isElective){
    
    this.isElective = isElective;
    }
    
    public Course(String id,  String code, String title, String shortName, String catId, Integer credits, String isElective) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.shortName = shortName;
        this.catId = catId;
        this.credits = credits;
        this.isElective = isElective;
    }
   
}
