package com.unicorn.sxmobileoa.app.network.model;

import com.unicorn.sxmobileoa.app.Key;

public class FydmRequest extends request {

    public FydmRequest(String busiCode, String fydm) {
        super(busiCode);
        addParameter(Key.fydm, fydm);
    }

}
