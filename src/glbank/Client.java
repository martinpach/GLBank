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
public class Client {

    private int idc;
    private String lastName;
    private String firstName;
    private String email;
    private String street;
    private String city;
    private int houseNumber;
    private String postCode;
    private String userName;
    private boolean disable;
    private boolean blocked;
    private Date dob;

    public Client(int idc, String lastName, String firstName, String email,
            String street, int houseNumber, String postCode, String userName,
            boolean disable, boolean blocked, Date dob, String city) {
        this.idc = idc;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.userName = userName;
        this.disable = disable;
        this.blocked = blocked;
        this.dob = dob;
        this.city = city;
    }

    public Client(int idc, String lastName, String firstName, Date dob) {
        this.idc = idc;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;
        this.houseNumber = 0;
        this.postCode = null;
        this.email = null;
        this.street = null;
        this.userName = null;
        this.disable = false;
        this.blocked = false;
    }

    public int getIdc() {
        return idc;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isDisable() {
        return disable;
    }

}
