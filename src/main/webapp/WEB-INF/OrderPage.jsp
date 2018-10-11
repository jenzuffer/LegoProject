<%-- 
    Document   : OrderPage
    Created on : Oct 9, 2018, 10:01:36 AM
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OrderPage</title>
    </head>
    <body>
        <h1>Hello <%=request.getParameter("email")%> <br> Make a new order!</h1>
        <table>
            <tr>
                <td>Order:
                    <form name="OrderPage" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="OrderPage">
                        <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                        <input type="hidden" name="password" value="<%=request.getParameter("password")%>">
                        Width<br>
                        <input type="text" name="width" value="">
                        <br>
                        length<br>
                        <input type="text" name="length" value="">
                        <br>
                        Height<br>
                        <input type="text" name="height" value="">
                        <br>
                        <input type="submit" value="Finalize order!">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
