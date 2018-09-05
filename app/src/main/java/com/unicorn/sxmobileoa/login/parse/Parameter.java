package com.unicorn.sxmobileoa.login.parse;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Parameter {

    @Attribute
    private String name;

    @Text
    private String text ;
}
