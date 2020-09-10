<%-- 
    Document   : roomForm
    Created on : May 24, 2018, 10:31:01 AM
    Author     : user0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add a course</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>


    </head>
    
    <body>

        
        <div class="header">
            <h1>KIC Admin</h1>
        </div>
        
        <div class="row">
            <div class="col-3 col-s-3"></div>
            <div class="col-6 col-s-6">
                <c:forEach items="${requestScope.errors}" var="error">
                    <dl>
                        <dt>Error place: ${error.key}</dt>
                        <dd>Error type:  ${error.value}</dd>
                    </dl>
    
                </c:forEach>
         
                <div id="readroot" style="display: none">
                    
                    <input type="button" value="Remove"
		onclick="removeFields(this)" /><br /><br />

                            <label for="courseId">Course ID:</label>
                            <input type="text" id="courseId" name="courseId" value="${param.courseId==null? '':param.courseId}" required>
                      
                            <label for="courseCode">Course code</label>
                            <input type="text" id="courseCode" name="courseCode" value="${param.courseCode==null? '':param.courseCode}" required>
                       
                            <label for="courseTitle">Title</label>
                            <input type="text" id="courseTitle" name="courseTitle" value="${param.courseTitle==null? '':param.courseTitle}" required>
                        
                            <label for="shortName">ShortName</label>
                            <input type="text" id="shortName" name="shortName" value="${param.shortName==null? '':param.shortName}" required>
                      
                            <label for="courseCat">Category</label>
                            <select name="courseCat" id ="courseCat" >
                                <c:forEach items="${requestScope.categories}" var="category">
                    
                                <option value="${category.id}" name ="courseCat">${category.name} \\ ${category.id}</option>
    
                                </c:forEach>    
                            </select>
                       
                            <label for="credits">Credits</label>
                            
                            <select id="courseCredits" name="courseCredits">
                                <option id="courseCredits" name="courseCredits" value=  "2">2  </option>
                                <option id="courseCredits" name="courseCredits" value="4"> 4  </option>
                                <option id="courseCredits" name="courseCredits" value="8"> 8  </option>
                             </select>
                            
                            <label for="isElective">isElective</label>
                            <select id="isElective" name="isElective" >
                            <option id="isElective" name="isElective" value="true" >   true</option>
                            <option id="isElective" name="isElective" value="false">  false</option>
                            </select>
                      
                    </fieldset>
                    
                </div>
                            
                  <form action="course.save" method="post" >             
                    <span id="writeroot"></span>

                    <p class="right-btn">
                        <input type="button" value="Addfields" onclick="moreFields()">
                        <input type="submit" value="Submit">
                        <input type="button" value="Cancel" onclick="document.getElementById('dashboardLink').click()">
                    </p>     
                </form>
                            
                <a id="dashboardLink" href="dashboard" style="visibility: hidden">Dashboard</a>
                        
                              
        <script>
             
function moreFields() {
	
	var newFields = document.getElementById('readroot').cloneNode(true);
	newFields.id = '';
	newFields.style.display = 'block';
	var newField = newFields.childNodes;
	
	var insertHere = document.getElementById('writeroot');
	insertHere.parentNode.insertBefore(newFields,insertHere);
}

function removeFields(myNode) {
    
    var myNode2 = myNode;
    //counter--;
    myNode2.parentNode.parentNode.removeChild(myNode2.parentNode);
    
}
window.onload = moreFields;
            
        </script>

                              
                        
                        
                <a id="dashboardLink" href="dashboard" style="visibility: hidden">Dashboard</a>
            </div>
            <div class="col-3 col-s-3"></div>
        </div>
        <div class="footer">
            <p>Course Admin has been developed with Web Application Development Course</p>
        </div>
                        
                        

    </body>
</html>
