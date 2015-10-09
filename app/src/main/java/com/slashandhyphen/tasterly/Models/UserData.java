package com.slashandhyphen.tasterly.Models;

public class UserData {

    private String email;

    private String password;

    private String password_confirmation;

    private String username;

    public UserData() {}

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getPasswordConfirmation ()
    {
        return password_confirmation;
    }

    public void setPasswordConfirmation (String password_confirmation)
    {
        this.password_confirmation = password_confirmation;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
