package com.example.mimacom.dtos;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class ApplicationDto implements Serializable {
    private static final long serialVersionUID = 6565457756462191628L;
    public ApplicationDto() {
    }
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}