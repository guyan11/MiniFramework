package com.guyan.ioc.editor;

import com.guyan.ioc.utils.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatePropertyEditor extends AbstractPropertyEditor {

    private final DateFormat dateFormat;

    public DatePropertyEditor(String pattern) {
        this.dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    protected void doSetAsText(String text) {
        if (StringUtil.isBlank(text)) {
            return;
        }

        try {
            setValue(dateFormat.parse(text));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
