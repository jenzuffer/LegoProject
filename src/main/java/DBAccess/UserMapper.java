package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import FunctionLayer.Order;
import FunctionLayer.OrderDetails;
import FunctionLayer.OrderLine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The purpose of UserMapper is to...
 *
 * @author christian M kaspers template
 *
 */
public class UserMapper {

    public static int FindOrderID() throws LoginSampleException {
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT `AUTO_INCREMENT`\n"
                    + "FROM  INFORMATION_SCHEMA.TABLES\n"
                    + "WHERE TABLE_SCHEMA = 'legoDB'\n"
                    + "AND   TABLE_NAME   = 'Orders';";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            ResultSet l_rs = l_pStatement.executeQuery();
            if (l_rs.next()) {
                return l_rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        throw new LoginSampleException("Issue with finding Auto-Increment for OrderDetails");
    }

    public static void createOrder(Order order, User user) throws LoginSampleException {
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "INSERT INTO `Orders` (`length`,`width`,`quantityheigth`,`orderdater`) VALUES (?, ?, ?, ?)";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL, Statement.RETURN_GENERATED_KEYS);
            l_pStatement.setInt(1, order.getL_iLength());
            l_pStatement.setInt(2, order.getL_iWdith());
            l_pStatement.setInt(3, order.getL_iQuantityheigth());
            l_pStatement.setString(4, order.getL_dOrderDate());
            l_pStatement.executeUpdate();

            l_sSQL = "INSERT INTO `OrderDetails` (`id`, `email`,`status`) VALUES (?, ?, ?)";

            l_pStatement = l_cCon.prepareStatement(l_sSQL, Statement.RETURN_GENERATED_KEYS);
            l_pStatement.setInt(1, order.getL_iID());
            l_pStatement.setString(2, user.getL_sEmail());
            l_pStatement.setString(3, "Not yet proceeded");
            l_pStatement.executeUpdate();

            //l_cCon.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static void createUser(User user) throws LoginSampleException {
        try {
            Connection l_cCon = Connector.connection();
            if (isEmailOrUsernameNOTTaken(user, l_cCon)) {
                String l_sSQL = "INSERT INTO `UserTable` (`email`,`username`,`password`,`rolegroup`) VALUES(?, ?, ?, ?)";
                PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL, Statement.RETURN_GENERATED_KEYS);
                l_pStatement.setString(1, user.getL_sEmail());
                l_pStatement.setString(2, user.getL_sUsername());
                l_pStatement.setString(3, user.getL_sPassword());
                l_pStatement.setString(4, user.getL_sRole());
                l_pStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static User login(String email, String password) throws LoginSampleException {
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT username, rolegroup FROM `UserTable`"
                    + "WHERE email = ? AND password = ?";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            l_pStatement.setString(1, email);
            l_pStatement.setString(2, password);
            ResultSet l_rs = l_pStatement.executeQuery();
            if (l_rs.next()) {
                String l_sRole = l_rs.getString("rolegroup");
                String l_sUsername = l_rs.getString("username");
                User user = new User(email, l_sUsername, password, l_sRole);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user" + " " + l_sSQL + " " + email + " " + password);
                //+ " " + l_sSQL + " " + email + " " + password
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    public static List<Order> GetOrderHistory(User user) throws LoginSampleException {
        List<Order> l_lOrderHistory = new ArrayList();
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT od1.id, o1.length, o1.width, o1.quantityheigth, o1.orderdater FROM `OrderDetails`od1, `Orders`o1 WHERE od1.email = ? AND od1.id = o1.id";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            l_pStatement.setString(1, user.getL_sEmail());
            ResultSet l_rsSearch = l_pStatement.executeQuery();
            //Order order = new Order(0, length, width, quantity, currentTime);
            while (l_rsSearch.next()) {
                Order order = new Order(l_rsSearch.getInt(1), l_rsSearch.getInt(2), l_rsSearch.getInt(3), l_rsSearch.getInt(4), l_rsSearch.getString(5));
                l_lOrderHistory.add(order);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return l_lOrderHistory;
    }

    //extra
    private static boolean isEmailOrUsernameNOTTaken(User user, Connection l_cCon) throws SQLException, LoginSampleException {
        String l_sSQLSearchPreExisting = "SELECT email FROM `UserTable` WHERE email = ?";
        PreparedStatement l_pStatemenSearcht = l_cCon.prepareStatement(l_sSQLSearchPreExisting);
        l_pStatemenSearcht.setString(1, user.getL_sEmail());
        ResultSet l_rsSearch = l_pStatemenSearcht.executeQuery();
        if (l_rsSearch.next()) {
            throw new LoginSampleException(l_rsSearch.getString(1) + " Query is: " + l_sSQLSearchPreExisting);
            //throw new LoginSampleException("email already taken");
            //return 2;
        }
        l_sSQLSearchPreExisting = "SELECT username FROM `UserTable` WHERE username = ?";
        l_pStatemenSearcht = l_cCon.prepareStatement(l_sSQLSearchPreExisting);
        l_pStatemenSearcht.setString(1, user.getL_sUsername());
        l_rsSearch = l_pStatemenSearcht.executeQuery();
        if (l_rsSearch.next()) {
            throw new LoginSampleException(l_rsSearch.getString(1) + " Query is: " + l_sSQLSearchPreExisting);
            //throw new LoginSampleException("Username already taken");
            //return 3;
        }
        return true;
    }

    public static List<String> getOrderStatuses(User user) throws LoginSampleException {
        List<String> l_lOrderStatuses = new ArrayList();
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT status FROM `OrderDetails` WHERE OrderDetails.email = ?";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            l_pStatement.setString(1, user.getL_sEmail());
            ResultSet l_rsSearch = l_pStatement.executeQuery();
            while (l_rsSearch.next()) {
                l_lOrderStatuses.add(l_rsSearch.getString(1));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return l_lOrderStatuses;
    }

    public static List<OrderLine> GetOrderLine(User user) throws LoginSampleException {
        List<OrderLine> l_lOrderStatuses = new ArrayList();
        float l_fInitializer = (float) 0.0;
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT o1.length, o1.width, o1.quantityheigth FROM `OrderDetails`od1, `Orders`o1 WHERE od1.email = ? AND od1.id = o1.id";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            l_pStatement.setString(1, user.getL_sEmail());
            ResultSet l_rsSearch = l_pStatement.executeQuery();
            while (l_rsSearch.next()) {
                OrderLine orderline = new OrderLine(l_rsSearch.getInt(1), l_rsSearch.getInt(2), l_rsSearch.getInt(3), l_fInitializer, l_fInitializer, l_fInitializer, l_fInitializer, l_fInitializer);
                l_lOrderStatuses.add(orderline);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return l_lOrderStatuses;
    }

    public static List<OrderDetails> GetAllOrders() throws LoginSampleException {
        List<OrderDetails> l_lAllOrder = new ArrayList();
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL = "SELECT * FROM `OrderDetails`";
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            ResultSet l_rsSearch = l_pStatement.executeQuery();
            //Order order = new Order(0, length, width, quantity, currentTime);
            while (l_rsSearch.next()) {
                OrderDetails order = new OrderDetails(l_rsSearch.getInt(1), l_rsSearch.getString(2), l_rsSearch.getString(3));
                l_lAllOrder.add(order);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
        return l_lAllOrder;
    }

    public static void changeOrderStatus(int l_iIDAccepted, int state) throws LoginSampleException {
        try {
            Connection l_cCon = Connector.connection();
            String l_sSQL;
            if (state == 0) {
                l_sSQL = "UPDATE `OrderDetails` SET `status`= \"Accepted\" WHERE `id`= " + l_iIDAccepted + "";
            } else {
                l_sSQL = "UPDATE `OrderDetails` SET `status`=\"not yet proceeded\" WHERE `id`= " + l_iIDAccepted + "";
            }
            PreparedStatement l_pStatement = l_cCon.prepareStatement(l_sSQL);
            l_pStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
