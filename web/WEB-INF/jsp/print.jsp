<%-- 
    Document   : print
    Created on : Jun 3, 2018, 10:58:08 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
          
                <fieldset>
                    <legend>New room added</legend>
                    
                    
                    <dl>
                        <dt>Course ID:</dt>
                        <dd>${requestScope.newCourse.id}</dd>
                        <dt>Code</dt>
                        <dd>${requestScope.newCourse.code}</dd>
                        <dt>Title</dt>
                        <dd>${requestScope.newCourse.title}</dd>
                        <dt>Short Name</dt>
                        <dd>${requestScope.newCourse.short_name}</dd>
                        <dt>Category</dt>
                        <dd>${requestScope.newCourse.category}</dd>                      
                        
                        <p class="right-btn">
                            <input type="button" value="OK" onclick="document.getElementById('dashboardLink').click()">
                        </p>
                </fieldset>
                        
    </body>
</html>
