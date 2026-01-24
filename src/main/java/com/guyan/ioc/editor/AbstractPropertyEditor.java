package com.guyan.ioc.editor;

public abstract class AbstractPropertyEditor implements PropertyEditor {

    private Object value;

    @Override
    public void setAsText(String text) {
        doSetAsText(text);
    }

    protected void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    protected abstract void doSetAsText(String text);
}
