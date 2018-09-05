package com.unicorn.sxmobileoa.login.parse;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "parameters")
public class Parameters {

    @ElementList(entry = "parameter",inline = true,required = false)
    public List<Parameter> parameters;

}
