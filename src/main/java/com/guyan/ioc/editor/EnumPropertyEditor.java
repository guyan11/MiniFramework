package com.guyan.ioc.editor;

import com.guyan.ioc.utils.StringUtil;

public class EnumPropertyEditor extends AbstractPropertyEditor {

    @SuppressWarnings("rawtypes")
    private final Class<? extends Enum> enumType;

    @SuppressWarnings("rawtypes")
    public EnumPropertyEditor(Class<? extends Enum> enumType) {
        this.enumType = enumType;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doSetAsText(String text) {
        if (StringUtil.isBlank(text)) {
            setValue(null);
            return;
        }

        try {
            Object anEnum = Enum.valueOf(enumType, text.trim());
            setValue(anEnum);
        } catch (Exception e) {
            throw new IllegalArgumentException("枚举类型转换错误" + enumType.getName(), e);
        }

    }
}
