package com.slashandhyphen.tasterly;
import java.util.ArrayList;
import com.slashandhyphen.tasterly.Models.UserData;

/**
 * Created by ookamijin on 1/1/2016.
 */
public class TestData {
    private static final TestData Instance = new TestData();
    private ArrayList<UserData> userData;

    public static TestData getInstance() {
        return Instance;
    }

    private TestData() {
        userData = new ArrayList<>();

        // Add default user
        userData.add(new UserData());
        userData.get(0).setEmail("android@test.com");
        userData.get(0).setUsername("androidTest");
        userData.get(0).setPassword("qwertyui");
        userData.get(0).setPasswordConfirmation("qwertyui");
    }

    public String getUserEmail() {
        return userData.get(0).getEmail();
    }

    public String getUserUsername() {
        return userData.get(0).getUsername();
    }

    public String getUserPassword() {
        return userData.get(0).getPassword();
    }

    public String getUserPasswordConfirmation() {
        return userData.get(0).getPasswordConfirmation();
    }

}
