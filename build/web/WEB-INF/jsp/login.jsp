<%-- 
    Document   : login
    Created on : May 21, 2018, 1:49:20 PM
    Author     : user0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Room Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>

    </head>
    <body>
        <div class="header">
            <h1>Room Admin</h1>
        </div>
        <div class="row">
            <div class="col-3 col-s-3"></div>
            <div class="col-6 col-s-6">
                <form action="login.action" method="post">
                    <fieldset>
                        <legend>Log in</legend>
                        <p>
                            <label for="userName">Login ID:</label>
                            <input type="text" class= "newAtt" id="userName" name="userName" required>
                        </p>
                        <p>
                            <label for="userName">Secret:</label>
                            <input type="password" id="secret" name="secret" required>
                        </p>
                        <p class="right-btn">
                            <input type="submit" value="Submit">
                        </p>
                    </fieldset>
                </form>
            </div>
            <div class="col-3 col-s-3"></div>
        </div>
        <div class="footer">
            <p>Room Admin has been developed with Web Application Development Course</p>
        </div>
    </body>
</html>
