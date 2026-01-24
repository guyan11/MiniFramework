package com.guyan.ioc.editor;

import java.text.ParseException;

public interface PropertyEditor {

    void setAsText(String text) throws ParseException;

    Object getValue();
}
