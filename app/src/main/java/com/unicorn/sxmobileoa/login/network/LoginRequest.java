package com.unicorn.sxmobileoa.login.network;

import com.unicorn.sxmobileoa.app.network.model.request;

public class LoginRequest extends request {

    public LoginRequest(String username, String password) {
        super("oalogin");
        put("userName", username);
        put("passWord", password);
    }

}
