package com.unicorn.sxmobileoa.court.useCase;

import com.unicorn.sxmobileoa.app.network.model.FydmRequest;

//@Root(name = "request")
public class CourtRequest extends FydmRequest {

    public CourtRequest() {
        // 查询所有法院时fydm参数无效
        super("fyxx", "");
        // type 0 表示查询所有法院
        addParameter("type", "0");
    }

}
