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
public class OrderLine {
    private int l_iLength;
    private int l_iWdith;
    private int l_iQuantityheigth;
    private float l_fTotalXHeigth2x4;
    private float l_fTotalXHeigth2x2;
    private float l_fTotalXHeigth1x2;
    private float l_fTotalWindows2x2;
    private float l_fTotalDoors3x2;

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

    public float getL_fTotalXHeigth2x4() {
        return l_fTotalXHeigth2x4;
    }

    public void setL_fTotalXHeigth2x4(float l_fTotalXHeigth2x4) {
        this.l_fTotalXHeigth2x4 = l_fTotalXHeigth2x4;
    }

    public float getL_fTotalXHeigth2x2() {
        return l_fTotalXHeigth2x2;
    }

    public void setL_fTotalXHeigth2x2(float l_fTotalXHeigth2x2) {
        this.l_fTotalXHeigth2x2 = l_fTotalXHeigth2x2;
    }

    public float getL_fTotalXHeigth1x2() {
        return l_fTotalXHeigth1x2;
    }

    public void setL_fTotalXHeigth1x2(float l_fTotalXHeigth1x2) {
        this.l_fTotalXHeigth1x2 = l_fTotalXHeigth1x2;
    }

    public float getL_fTotalWindows2x2() {
        return l_fTotalWindows2x2;
    }

    public void setL_fTotalWindows2x2(float l_fTotalWindows2x2) {
        this.l_fTotalWindows2x2 = l_fTotalWindows2x2;
    }

    public float getL_fTotalDoors3x2() {
        return l_fTotalDoors3x2;
    }

    public void setL_fTotalDoors3x2(float l_fTotalDoors3x2) {
        this.l_fTotalDoors3x2 = l_fTotalDoors3x2;
    }

    public OrderLine(int l_iLength, int l_iWdith, int l_iQuantityheigth, float l_fTotalXHeigth2x4, float l_fTotalXHeigth2x2, float l_fTotalXHeigth1x2, float l_fTotalWindows2x2, float l_fTotalDoors3x2) {
        this.l_iLength = l_iLength;
        this.l_iWdith = l_iWdith;
        this.l_iQuantityheigth = l_iQuantityheigth;
        this.l_fTotalXHeigth2x4 = l_fTotalXHeigth2x4;
        this.l_fTotalXHeigth2x2 = l_fTotalXHeigth2x2;
        this.l_fTotalXHeigth1x2 = l_fTotalXHeigth1x2;
        this.l_fTotalWindows2x2 = l_fTotalWindows2x2;
        this.l_fTotalDoors3x2 = l_fTotalDoors3x2;
    }

    
}
