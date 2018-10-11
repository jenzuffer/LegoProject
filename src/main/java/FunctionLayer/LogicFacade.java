package FunctionLayer;

import DBAccess.UserMapper;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * The purpose of LogicFacade is to...
 *
 * @author kasper// christian m edit
 */
public class LogicFacade {

    public static User login(String email, String password) throws LoginSampleException {
        return UserMapper.login(email, password);
    }

    public static User createUser(String email, String username, String password) throws LoginSampleException {
        System.out.print("Look here: " + email + " " + username + " " + password);
        User user = new User(email, username, password, "customer");
        //Inserts user into database
        UserMapper.createUser(user);
        return user;
    }

    public static Order CreateOrder(int length, int width, int quantity, User user) throws LoginSampleException {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        Order order = new Order(0, length, width, quantity, currentTime);
        order.setL_iID(UserMapper.FindOrderID());
        //inserts order into database
        UserMapper.createOrder(order, user);
        return order;
    }

    public static List<Order> UserOrderHistory(User user) throws LoginSampleException {
        List<Order> l_lOrderHistory = UserMapper.GetOrderHistory(user);
        return l_lOrderHistory;
    }

    public static List<String> UserOrderStatus(User user) throws LoginSampleException {
        List<String> l_lOrderStatus = UserMapper.getOrderStatuses(user);
        return l_lOrderStatus;
    }

    public static List<OrderLine> UserOrderLine(User user) throws LoginSampleException {
        float l_fLength = 0;
        float l_fWidth = 0;
        float l_fheigth = 0;
        float l_fTotal = 0;
        float l_fWindow = 4; //2x2
        float l_fDoor = 6; //3x2
        int l_i2x4 = 8;
        List<OrderLine> l_lOrderLine = UserMapper.GetOrderLine(user);
        for (int i = 0; i < l_lOrderLine.size(); i++) {
            l_fLength = l_lOrderLine.get(i).getL_iLength();
            l_fWidth = l_lOrderLine.get(i).getL_iWdith();
            l_fheigth = l_lOrderLine.get(i).getL_iQuantityheigth();
            //l_fLength = 8, l_fWidth = 7, l_fheigth = 16 (example)
            // 8 * 7 * 16 = 896 total dots
            l_fTotal = ((l_fLength * l_fWidth) * l_fheigth) / l_i2x4; //896 / 8 = 112 total 2x4 dots, however that would be the green task
            //using "forbandt" the dots structure has to be different from the layer below it meaning if the first layer has 2x4 the second layer needs to use 2x2 or 1x2 dots instead,
            //also replacing 1x2 with 2x2 or 2x4

            //112 is rounded but some might not be rounded like 13 * 15 * 17 = 3315 / 8 = 414.375
            l_fTotal = l_fTotal / 2;
            //112 / 2 = 56. Every second layer can use 2x4 dots structure so we need a total of 56 2x4 dots, 50% of the layers

            //for adding door/window remove the required dots from the 2x4, now all 2x4 layers have spaces left for doors/windows
            //56 - 10 = 46 per layer
            l_fTotal = l_fTotal - (l_fWindow + l_fDoor);

            if ((l_fTotal % 1) != 0) {
                //if l_fheigth were 15 instead it would be 42.5 2x4 dots, or other example: 414.375 / 2 = 197.1875 2x4 dots
                float ll_fDecimal = l_fTotal - (int) l_fTotal;
                l_fTotal = (float) Math.floor(l_fTotal);
                l_lOrderLine.get(i).setL_fTotalXHeigth2x4(l_fTotal);
                // assigned 42 2x4 dots, for 50% still missing 0.5 2x4 dots (1 2x2 dot or 2 1x2), (other example would be 0.1875 2x4 dots)

                l_lOrderLine.get(i).setL_fTotalXHeigth2x2(l_fTotal * 2);
                // 84 2x2 dots, missing 0.5 * 2 2x4 for other 50%, other example would be 0.1875 * 2 2x4
                //in 0.5 * 8 * 2 = 1 full 2x4 missing, in 0.1875 * 8 * 2 = 3 / 2 = 1.5 2x4
                if ((ll_fDecimal * l_i2x4 * 2 / 2) % 2 == 0) {
                    l_lOrderLine.get(i).setL_fTotalXHeigth1x2(ll_fDecimal * l_i2x4 * 2);
                } else {
                    l_lOrderLine.get(i).setL_fTotalXHeigth1x2((float) (Math.ceil(ll_fDecimal) * l_i2x4 * 2 / 2));
                    //this order would be one dot short, 1x2 = 2 dots, 3 required so adding one extra 1x2
                }
                //now just add doors/windows for each layer
                l_lOrderLine.get(i).setL_fTotalWindows2x2(l_fWindow * l_fheigth);
                l_lOrderLine.get(i).setL_fTotalDoors3x2(l_fDoor * l_fheigth);
            } else {
                //if the number is rounded we end up here, 46 is rounded
                l_lOrderLine.get(i).setL_fTotalXHeigth2x4(l_fTotal);
                //other 50% can be done entirely with 92 2x2 dots, no need for 1x2 now
                l_lOrderLine.get(i).setL_fTotalXHeigth2x2(l_fTotal * 2);
                l_lOrderLine.get(i).setL_fTotalXHeigth1x2(0);
                //now just add doors/windows for each layer
                l_lOrderLine.get(i).setL_fTotalWindows2x2(l_fWindow * l_fheigth);
                l_lOrderLine.get(i).setL_fTotalDoors3x2(l_fDoor * l_fheigth);
            }
        }
        return l_lOrderLine;
    }

    public static List<OrderDetails> GetAllOrders() throws LoginSampleException {
        List<OrderDetails> l_lAllOrder = UserMapper.GetAllOrders();
        return l_lAllOrder;
    }

    public static void ChangeOrderStatus(int l_iIDAccepted, int state) throws LoginSampleException {
        UserMapper.changeOrderStatus(l_iIDAccepted, state);
    }
}
