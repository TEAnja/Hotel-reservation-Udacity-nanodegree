package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(emailRegex);

    public Customer(String firstName, String lastName, String email){
        if(!matcher.matches()){
            throw new IllegalArgumentException("Error, Invalid email");
        }
        else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return "Name: " + firstName + " " + lastName + ", Email: " + email;
    }
}
