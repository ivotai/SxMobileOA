package com.unicorn.sxmobileoa.login.parse;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Parameter {

    @Attribute
    public String name;

    @Text
    public String text ;
}
