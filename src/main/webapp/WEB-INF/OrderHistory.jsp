<%-- 
    Document   : OrderHistory
    Created on : Oct 9, 2018, 10:43:41 AM
    Author     : Christian
--%>

<%@page import="FunctionLayer.OrderLine"%>
<%@page import="FunctionLayer.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History page</title>
    </head>
    <body>
        <h1>Hello <%=request.getParameter("email")%> <br> View your Order history here!</h1>
        <table>
            <tr>
                <td>
                    <%  //er forretningslogik functionlayer eller presentationlayer?
                        List<Order> History = (List<Order>) request.getSession().getAttribute("history");
                        List<String> status = (List<String>) request.getSession().getAttribute("status");
                        List<OrderLine> stykliste = (List<OrderLine>) request.getSession().getAttribute("stykliste");
                        //out.println(test + "test here");
                        if (History != null && status != null) {
                            out.println("<H2>History</h2>");
                            for (int i = 0; i < History.size(); i++) {
                                out.println("Order ID: " + History.get(i).getL_iID() + " Order Contains:<br>" + "Length: " + History.get(i).getL_iLength() + "<br>" + "Width: "
                                        + History.get(i).getL_iWdith() + "<br>" + "Heigth: " + History.get(i).getL_iQuantityheigth() + "<br>" + "OrderDate: "
                                        + History.get(i).getL_dOrderDate() + "<br>" + "OrderLine: <br>" + "2x4: " + stykliste.get(i).getL_fTotalXHeigth2x4()+ "<br>" + "2x2: " 
                                        + stykliste.get(i).getL_fTotalXHeigth2x2()+ "<br>" + "1x2: " + stykliste.get(i).getL_fTotalXHeigth1x2() + "<br>3x2 Doors: "
                                        + stykliste.get(i).getL_fTotalDoors3x2() + "<br>2x2 Windows: " + stykliste.get(i).getL_fTotalWindows2x2() + "<br>" + "Status: " 
                                        + status.get(i) + "<br><br>");
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
