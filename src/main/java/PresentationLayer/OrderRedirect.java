/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.OrderLine;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class OrderRedirect extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        //redirects to 3 order related .jsp sites
        String l_sOrderRedirection = request.getParameter("OrderRedirection");
        switch (l_sOrderRedirection) {
            case "OrderHistory": {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                User user = LogicFacade.login(email, password);
                List<Order> l_lOrderHistory = LogicFacade.UserOrderHistory(user);
                List<String> l_lOrderStatus = LogicFacade.UserOrderStatus(user);
                List<OrderLine> l_lOrderline = LogicFacade.UserOrderLine(user);
                HttpSession session = request.getSession();
                session.setAttribute("history", l_lOrderHistory);
                session.setAttribute("status", l_lOrderStatus);
                session.setAttribute("stykliste", l_lOrderline);
                return l_sOrderRedirection;
            }
            case "OrderPage": {
                return l_sOrderRedirection;
            }
        }
        throw new LoginSampleException("Something went wrong, try another button");
    }
}
