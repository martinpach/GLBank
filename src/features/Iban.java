/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package features;

import java.math.BigInteger;

/**
 *
 * @author Martin
 */
public class Iban {

    public String getIban(String bBan, String prefix) {
        String bankCode = bBan.substring(10);
        String accountNum = bBan.substring(0, 10);
        prefix = prefix.length() == 6 ? prefix : "000000";
        String tempString = bankCode + prefix + accountNum + "282000";
        
        //generating controll numbers
        BigInteger tempNum = new BigInteger(tempString);
        BigInteger moduloTempNum = tempNum.mod(new BigInteger("97"));
        BigInteger controllNumbers = new BigInteger("98").subtract(moduloTempNum);

        // if controll number is less then 10 add 0 at the front
        if (controllNumbers.compareTo(new BigInteger("9")) == 1) {
            return "SK" + controllNumbers + bankCode + prefix + accountNum;
        } else {
            return "SK" + controllNumbers + bankCode + prefix + accountNum;
        }
    }
}
