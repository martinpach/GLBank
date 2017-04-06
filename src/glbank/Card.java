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
public class Card {
    private int idCard;
    private long cardNumber;
    private long idacc;
    private boolean blocked;
    private int pincode;

    public Card(int idCard, long cardNumber, long idacc, boolean blocked, int pincode) {
        this.idCard = idCard;
        this.cardNumber = cardNumber;
        this.idacc = idacc;
        this.blocked = blocked;
        this.pincode = pincode;
    }

    public int getIdCard() {
        return idCard;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public long getIdacc() {
        return idacc;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getPincode() {
        return pincode;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
    
    
    
}
