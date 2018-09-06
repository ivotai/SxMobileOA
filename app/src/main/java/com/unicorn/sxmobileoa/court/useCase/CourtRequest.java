package com.unicorn.sxmobileoa.court.useCase;

import com.unicorn.sxmobileoa.app.Key;
import com.unicorn.sxmobileoa.app.network.model.request;

//@Root(name = "request")
public class CourtRequest extends request {

    public CourtRequest() {
        super("fyxx");
        // 表示查询所有法院
        addParameter("type", "0");
        // 查询所有法院时该参数无效
        addParameter(Key.fydm, "");
    }

}
