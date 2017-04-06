/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glbank;


/**
 *
 * @author Martin
 */
public class Account {
    private long idacc;
    private int idc;
    private float balance;

    public Account(long idacc, int idc, float balance) {
        this.idacc = idacc;
        this.idc = idc;
        this.balance = balance;
    }

    public long getIdacc() {
        return idacc;
    }

    public int getIdc() {
        return idc;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
    
    
}
