/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.sql.Date;

/**
 *
 * @author Christian
 */
public class Order {
    private int l_iID;
    private int l_iLength;
    private int l_iWdith;
    private int l_iQuantityheigth;
    private String l_dOrderDate;

    public Order(int l_iID, int l_iLength, int l_iWdith, int l_iQuantityheigth, String l_dOrderDate) {
        this.l_iID = l_iID;
        this.l_iLength = l_iLength;
        this.l_iWdith = l_iWdith;
        this.l_iQuantityheigth = l_iQuantityheigth;
        this.l_dOrderDate = l_dOrderDate;
    }

    public int getL_iID() {
        return l_iID;
    }

    public void setL_iID(int l_iID) {
        this.l_iID = l_iID;
    }

    public int getL_iLength() {
        return l_iLength;
    }

    public void setL_iLength(int l_iLength) {
        this.l_iLength = l_iLength;
    }

    public int getL_iWdith() {
        return l_iWdith;
    }

    public void setL_iWdith(int l_iWdith) {
        this.l_iWdith = l_iWdith;
    }

    public int getL_iQuantityheigth() {
        return l_iQuantityheigth;
    }

    public void setL_iQuantityheigth(int l_iQuantityheigth) {
        this.l_iQuantityheigth = l_iQuantityheigth;
    }

    public String getL_dOrderDate() {
        return l_dOrderDate;
    }

    public void setL_dOrderDate(String l_dOrderDate) {
        this.l_dOrderDate = l_dOrderDate;
    }

    
}
