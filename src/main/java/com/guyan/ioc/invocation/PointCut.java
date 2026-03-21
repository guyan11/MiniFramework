package com.guyan.ioc.invocation;

import java.lang.reflect.Method;

public interface PointCut {

    /**
     * 切点匹配接口
     *
     * @param method      待匹配的方法
     * @param targetClass 目标类（用于按类匹配的场景，如包名、类名、注解等）
     */
    boolean matches(Method method, Class<?> targetClass);
}
