<%-- 
    Document   : OrderStatus
    Created on : Oct 9, 2018, 10:44:34 AM
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%=request.getParameter("email")%> <br> View your Order status here!</h1>
        <table>
            <tr>
                <td>
                    <%  //er forretningslogik functionlayer eller presentationlayer?
                        List<Order> History = (List<Order>) request.getSession().getAttribute("history");
                        int test = (int) request.getSession().getAttribute("test");
                        //out.println(test + "test here");
                        if (History != null) {
                            out.println("<H2>History</h2>");
                            for (int i = 0; i < History.size(); i++) {
                                out.println("Order ID: " + History.get(i).getL_iID() + " Order Contains:<br>" + "Length: " + History.get(i).getL_iLength() + "<br>" + "Width: "
                                        + History.get(i).getL_iWdith() + "<br>" + "Heigth: " + History.get(i).getL_iQuantityheigth() + "<br>" + "OrderDate: "
                                        + History.get(i).getL_dOrderDate() + "<br><br>");
                            }
                        } else {
                            out.println("You have no Order History so far");
                        }
                    %>
                </td>
            </tr>
        </table>
    </body>
</html>
