package com.slashandhyphen.tasterly.Models;

//TODO: omg change user model.  the name "user" for the UserData is currently required by the api,
// as well as the two layer class model...
public class User
{

    private UserData user;

    public UserData getmData() {
        return user;
    }

    public void setmData(UserData mData) {
        this.user = mData;
    }
}
