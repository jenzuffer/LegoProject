<%-- 
    Document   : employeepage.jsp
    Created on : Aug 24, 2017, 6:31:57 AM
    Author     : kasper
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.OrderDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee home page</title>
    </head>
    <body>

        <h1>Hello <%=request.getParameter("email")%> </h1>
        You are now logged in as a EMPLOYEE of our wonderful site.
        <table>
            <tr>
                <td>
                    <%
                        //<input type="hidden" name="Test" value="DO YOU FAIL">
                        /*
                       
                         */
                        List<OrderDetails> AllOrders = (List<OrderDetails>) request.getSession().getAttribute("allOrders");
                        if (AllOrders != null) {
                            out.println("<H2>OrderList " + AllOrders.size() + "</h2>");
                            int l_iID = 0;
                            for (int i = 0; i < AllOrders.size(); i++) {
                                out.println("<br>Order ID: " + AllOrders.get(i).getL_iID() + "<br>" + AllOrders.get(i).getL_sEmail() + "<br>" + AllOrders.get(i).getL_sStatus()
                                        + "<br>" + "Change:<br>");
                                l_iID = AllOrders.get(i).getL_iID();
                                request.setAttribute("OrderID", l_iID);
                    %> 
                    <form name="employeepage" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="employeepage">
                        <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                        <input type="hidden" name="password" value="<%=request.getParameter("password")%>">
                        <%if (AllOrders.get(i).getL_sStatus().contains("proceeded")) {%>
                        <input type="hidden" name="orderaccepted" value="<%=request.getAttribute("OrderID")%>">
                        <input type="submit" value="Change Order to Accepted">
                        <%//out.print(Integer.toString(AllOrders.get(i).getL_iID()));%>
                        <% } else {
                        %>
                        <input type="hidden" name="OrderReverted" value="<%=request.getAttribute("OrderID")%>">
                        <input type="submit" value="Change Order to not yet proceeded">
                        <% }
                        %>
                    </form>
                    <%                       }
                        } else {
                            out.println("No Orders in system");
                        }
                    %>
                </td>
            </tr>
        </table>
    </body>
</html>
