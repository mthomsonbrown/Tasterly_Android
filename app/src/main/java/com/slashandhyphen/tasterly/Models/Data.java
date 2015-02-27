package com.slashandhyphen.tasterly.Models;

public class Data
{
    private Tasks[] tasks;

    private String auth_token;

    public Tasks[] getTasks ()
    {
        return tasks;
    }

    public void setTasks (Tasks[] tasks)
    {
        this.tasks = tasks;
    }

    public String getAuth_token ()
    {
        return auth_token;
    }

    public void setAuth_token (String auth_token)
    {
        this.auth_token = auth_token;
    }
}
