package com.slashandhyphen.tasterly.Models;

/**
 * Created by ookamijin on 2/20/15.
 */
public class SessionResponse {
    private Data data;
    private String success;
    private String info;
    private String auth_token;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getInfo ()
    {
        return info;
    }

    public void setInfo (String info)
    {
        this.info = info;
    }

    public String getAuth_token() {
        return auth_token;
    }
}
