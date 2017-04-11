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
public class BankTransaction {
    private float amount;
    private Date transDateTime;
    private int idemp;
    private long destacc;

    public BankTransaction(float amount, Date transDateTime, int idemp, long destacc) {
        this.amount = amount;
        this.transDateTime = transDateTime;
        this.idemp = idemp;
        this.destacc = destacc;
    }

    public float getAmount() {
        return amount;
    }

    public Date getTransDateTime() {
        return transDateTime;
    }

    public int getIdemp() {
        return idemp;
    }

    public long getDestacc() {
        return destacc;
    }
    
    
}
