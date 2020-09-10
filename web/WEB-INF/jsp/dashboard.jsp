<%-- 
    Document   : dashboard
    Created on : May 23, 2018, 2:42:37 PM
    Author     : user0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>KIC Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>

        <div class="header">
            <span><h1>KIC Admin</h1><p style="direction: rtl">Welcome ${sessionScope.randNum} (<a href="logout">logout</a>)</p></span>
        </div>

        <div class="row">

            <div class="col-4 col-s-5 menu">
                <ul>
                    <li><a href="room.form">Add a room</a></li>
                    
                    <li><a href="course.form">Add Course</a></li>
                    
                    <li><a href="category.form">Add Course Category</a></li>
                    
                    <li><a href="#">TBA</a></li>
                </ul>
            </div>

            <div class="col-8 col-s-7">
                <h1>KIC Rooms</h1>
                
                <c:forEach items="${requestScope.rooms}" var="room">
                    <dl>
                        
                    <form action="room.del" method="post">
                        <fieldset>
                            <dt>Room ID: ${room.id}</dt>
                            <dd >Capacity: ${room.capacity}</dd>
                            <dd> 
                                <p class="right-btn">   
                                    <i>   </i>
                                        <input type="submit" value="DELETE"> 
                                        <input type="hidden" value="${room.id}" name ="delete">
                                        
                                </p>
                        </fieldset>
                        </dd>
                    </form>

                    </dl>
                </c:forEach>
                
                <c:forEach items="${requestScope.courses}" var="course">
                    <dl>

                        <form action="course.del" method="post">
                        <fieldset>
                        
                            <dt>    Course id:   ${course.id}               </dt>
                            <dd >   Course Code: ${course.code}             </dd>
                            <dd >   Course Title: ${course.title}            </dd>
                            <dd>    Short name : ${course.getShort()} </dd>
                            <dd >   Course Category: ${course.getCat()}          </dd>
                            <dd >   Course Credits: ${course.getCredits()}          </dd>
                            <dd >   Course Elective: ${course.getElect()}          </dd>
                                  
                                <p class="right-btn">   
                                    <input type="submit" value="DELETE"> 
                                    <input type="hidden" value="${course.id}" name ="delete">
                                </p>
                      
                        </fieldset>
                        </form>

                    </dl>
                </c:forEach>
                
                        <c:forEach items="${requestScope.categories}" var="category">
                    <dl>

                        <form action="category.del" method="post">
                        <fieldset>
                        
                            <dt>Category id: ${category.id}         </dt>
                            <dd>Category name: ${category.name}    </dd>
                            
                                <p class="right-btn">   
                                    <input type="submit" value="DELETE"> 
                                    <input type="hidden" value="${course.category}" name ="delete">
                                </p>
                      
                        </fieldset>
                        </form>

                    </dl>
                </c:forEach>
                    
                    
                <p></p>
                <p></p>
                <p></p>
                <p></p>
            </div>

        </div>

        <div class="footer">
            <p>KIC Admin has been developed with Web Application Development Course</p>
        </div>

    </body>
</html>
