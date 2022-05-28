package com.kw.gdx.csvanddata;

import java.lang.reflect.Field;

public class Interpreter {
    public static String iteratorAnnotations(Field field) {
        // 判断 somebody() 方法是否包含MyAnnotation注解
        if(field.isAnnotationPresent(Value.class)){
            // 获取该方法的MyAnnotation注解实例
            Value myAnnotation = field.getAnnotation(Value.class);
            // 获取 myAnnotation的值，并打印出来
            String value = myAnnotation.value();
            if (value==null){
                value = field.getName();
            }
            return value;
        }
        return field.getName();
    }
}