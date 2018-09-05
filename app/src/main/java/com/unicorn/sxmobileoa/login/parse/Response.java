package com.unicorn.sxmobileoa.login.parse;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Response {

    @Element
    public String code;

    @Element
    public String msg;

    @Element(required = false)
    public parameters parameters;

}
