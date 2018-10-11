/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Christian
 */
public class OrderPage extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginSampleException {
        int l_iWidth = Integer.valueOf(request.getParameter("width"));
        int l_iLength = Integer.valueOf(request.getParameter("length"));
        int l_iHeight = Integer.valueOf(request.getParameter("height"));
        if (l_iWidth < 4 || l_iLength < 4 || l_iHeight < 4)
             throw new LoginSampleException("Minimum Dimension is 5 x 5 x 5");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = LogicFacade.login(email, password);
        LogicFacade.CreateOrder(l_iLength, l_iWidth, l_iHeight, user);
        //HttpSession session = request.getSession();
        return "customerpage";
    }

}
