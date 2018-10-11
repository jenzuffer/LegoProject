/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.OrderDetails;
import FunctionLayer.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class BackDoor extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String l_s1 = request.getParameter("orderaccepted");
        //String test = request.getParameter("Test");
        String l_s2 = request.getParameter("OrderReverted");
        int l_iIDAccepted = (l_s1 == null || l_s1.length() < 1) ? 0 : Integer.parseInt(l_s1);
        int l_iIDRevert = (l_s2 == null || l_s2.length() < 1) ? 0 : Integer.parseInt(l_s2);
        //System.out.println(l_iIDRevert + " " + l_iIDAccepted  + " " + email + " " + l_s1 + " " + l_s2);
        if (l_iIDAccepted > 0) {
            LogicFacade.ChangeOrderStatus(l_iIDAccepted, 0);
        } else if (l_iIDRevert > 0) {
            LogicFacade.ChangeOrderStatus(l_iIDRevert, 1);
        }
        //throw new LoginSampleException("l_sIDAccepted: " + l_sIDAccepted + " l_sIDRevert: " + l_sIDRevert);
        List<OrderDetails> l_lAllOrder = LogicFacade.GetAllOrders();
        User user = LogicFacade.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getL_sRole());
        session.setAttribute("allOrders", l_lAllOrder);

        return user.getL_sRole() + "page";

    }

}
