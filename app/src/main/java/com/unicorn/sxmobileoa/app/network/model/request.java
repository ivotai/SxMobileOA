package com.unicorn.sxmobileoa.app.network.model;

import com.blankj.utilcode.util.AppUtils;
import com.unicorn.sxmobileoa.app.Global;
import com.unicorn.sxmobileoa.app.Key;
import com.unicorn.sxmobileoa.app.union.RandomGeneter;

import org.joda.time.DateTime;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(strict = false)
public class request {

    public request(String busiCode) {
        this.busiCode = busiCode;
        parameters = new parameters();
        parameters.parameterList = new ArrayList<>();
    }

    public void put(String key, String value) {
        parameters.parameterList.add(new parameter(key, value));
    }

    @Element
    public String requestFlow = new DateTime().toString(Key.DATE_FORMAT) + RandomGeneter.generateString(6);

    @Element
    public String version = AppUtils.getAppVersionName();

    @Element
    public String UUID = "";

    @Element
    public String busiCode;

    // TODO 也许需要 loginName 和 loginBusiType 参数
    @Element
    public String loginName = "";

    @Element
    public String loginBusiType = "";

    @Element
    public String ticket = Global.INSTANCE.getTicket() == null ? "" : Global.INSTANCE.getTicket();

    @Element
    public String randCode = RandomGeneter.generateString(12);

    @Element
    public String randCodeSec = "";

    @Element
    public String time = new DateTime().toString(Key.DATE_FORMAT);

    @Element
    public String phoneType = "android";

    @Element
    public parameters parameters;

}
