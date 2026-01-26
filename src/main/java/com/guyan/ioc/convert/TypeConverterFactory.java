package com.guyan.ioc.convert;

import com.guyan.ioc.domain.Address;
import com.guyan.ioc.editor.DatePropertyEditor;
import com.guyan.ioc.editor.EnumPropertyEditor;
import com.guyan.ioc.editor.PropertyEditor;
import com.guyan.ioc.editor.PropertyEditorRegistry;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TypeConverterFactory {

    private final Map<Class<?>, TypeConverter> converterMap = new HashMap<>();

    private final PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistry();

    public TypeConverterFactory() {
        registerDefaultConverter();
        registerDefaultEditor();
    }

    private void registerDefaultConverter() {
        registerConverter(String.class, new StringConverter());

        registerConverter(int.class, new IntegerConverter());
        registerConverter(Integer.class, new IntegerConverter());
    }


    public void registerConverter(Class<?> targetType, TypeConverter typeConverter) {
        if (targetType != null && typeConverter != null) {
            converterMap.put(targetType, typeConverter);
        }
    }


    private void registerDefaultEditor() {
        registerEditor(Date.class, new DatePropertyEditor("yyyy-MM-dd"));
        registerEditor(Address.class, new EnumPropertyEditor(Address.class));
    }

    private void registerEditor(Class<?> targetType, PropertyEditor propertyEditor) {
        if (targetType != null && propertyEditor != null) {
            propertyEditorRegistry.registerEditor(targetType, propertyEditor);
        }
    }

    public Object convert(Object value, Class<?> targetType) {

        if (value == null) {
            return null;
        }

        // 如果类型匹配，则直接返回
        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        TypeConverter typeConverter = converterMap.get(targetType);
        if (typeConverter != null) {
            return typeConverter.convert(value, targetType);
        }

        PropertyEditor propertyEditor = propertyEditorRegistry.findEditor(targetType);
        if (propertyEditor != null) {
            try {
                propertyEditor.setAsText(value.toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return propertyEditor.getValue();
        }

        throw new IllegalArgumentException("No converter found for target type: " + targetType);
    }

}
