package com.guyan.ioc.editor;

import java.util.HashMap;
import java.util.Map;

public class PropertyEditorRegistry {

    private final Map<Class<?>, PropertyEditor> propertyEditorMap = new HashMap<>();

    public void registerEditor(Class<?> targetType, PropertyEditor propertyEditor) {
        if (targetType != null && propertyEditor != null) {
            propertyEditorMap.put(targetType, propertyEditor);
        }
    }

    public PropertyEditor findEditor(Class<?> requiredType) {
        return propertyEditorMap.get(requiredType);
    }
}
