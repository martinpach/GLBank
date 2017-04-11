/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glbank;

import java.util.Date;

/**
 *
 * @author Martin
 */
public class CashTransaction {
    private int idemp;
    private float amount;
    private long idacc;
    private Date cashDatetime;

    public CashTransaction(int idemp, float amount, long idacc, Date cashDatetime) {
        this.idemp = idemp;
        this.amount = amount;
        this.idacc = idacc;
        this.cashDatetime = cashDatetime;
    }

    public int getIdemp() {
        return idemp;
    }

    public float getAmount() {
        return amount;
    }

    public long getIdacc() {
        return idacc;
    }

    public Date getCashDatetime() {
        return cashDatetime;
    }
    
    
}
