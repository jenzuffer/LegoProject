<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Welcome to Sem 2</h1>
        
        <table>
            <tr>
                <td>employeepage:
                    <form name="employeepage" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="employeepage">
                        Email:<br>
                        <input type="text" name="email" value="">
                        <br>
                        Password:<br>
                        <input type="password" name="password" value="">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
                <td>Login:
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="login">
                        Email:<br>
                        <input type="text" name="email" value="">
                        <br>
                        Password:<br>
                        <input type="password" name="password" value="">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
                <td>Register:
                    <form name="register" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="register">
                        Email:<br>
                        <input type="text" name="email" value="">
                        <br>
                        Username:<br>
                        <input type="text" name="username" value="">
                        <br>
                        Password:<br>
                        <input type="password" name="password1" value="">
                        <br>
                        Retype Password:<br>
                        <input type="password" name="password2" value="">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </td>
            </tr>
        </table>
        <% String error = (String) request.getAttribute( "error");
           if ( error != null) { 
               out.println("<H2>Error!!</h2>");
               out.println(error);
           }
        %>
    </body>
</html>
