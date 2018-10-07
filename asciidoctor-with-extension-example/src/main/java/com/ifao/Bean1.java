package com.ifao;

import org.springframework.stereotype.Component;

import javax.xml.ws.BindingType;

@Component
@BindingType
public class Bean1 {

    //Comment1
    @Deprecated
    private Integer i;
}
