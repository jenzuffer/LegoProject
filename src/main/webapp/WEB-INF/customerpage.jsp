<%-- 
    Document   : customerpage
    Created on : Aug 22, 2017, 2:33:37 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer home page</title>
    </head>
    <body>
        <h1>Hello <%=request.getParameter("email")%> <br> Welcome back!</h1>
        <table>
            <tr>
                <td>
                    <form name="OrderRedirect" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="OrderRedirect">
                        <input type="hidden" name="OrderRedirection" value="OrderPage">
                        <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                        <input type="hidden" name="password" value="<%=request.getParameter("password")%>">
                        <br>
                        <input type="submit" value="Make a new Order">
                    </form>
                </td>
                <td> 
                    <form name="OrderRedirect" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="OrderRedirect">
                        <input type="hidden" name="OrderRedirection" value="OrderHistory">
                        <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                        <input type="hidden" name="password" value="<%=request.getParameter("password")%>">
                        <br>
                        <input type="submit" value="View Order history">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
