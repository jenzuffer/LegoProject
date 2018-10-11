/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Christian
 */
public class OrderDetails {
    private int l_iID;
    private String l_sEmail;
    private String l_sStatus;

    public int getL_iID() {
        return l_iID;
    }

    public void setL_iID(int l_iID) {
        this.l_iID = l_iID;
    }

    public String getL_sEmail() {
        return l_sEmail;
    }

    public void setL_sEmail(String l_sEmail) {
        this.l_sEmail = l_sEmail;
    }

    public String getL_sStatus() {
        return l_sStatus;
    }

    public void setL_sStatus(String l_sStatus) {
        this.l_sStatus = l_sStatus;
    }

    public OrderDetails(int l_iID, String l_sEmail, String l_sStatus) {
        this.l_iID = l_iID;
        this.l_sEmail = l_sEmail;
        this.l_sStatus = l_sStatus;
    }
}
