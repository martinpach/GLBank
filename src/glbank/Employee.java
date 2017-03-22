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
public class Employee {

    private int idemp;
    private String firstName;
    private String lastName;
    private String email;
    private char position;

    public Employee(int idemp, String firstName, String lastName,
            String email, char position) {
        this.idemp = idemp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
    }

    public int getIdemp() {
        return idemp;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public char getPosition() {
        return position;
    }

}
