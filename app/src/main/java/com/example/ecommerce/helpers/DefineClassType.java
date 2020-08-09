package com.example.ecommerce.helpers;

public class DefineClassType {
    //generic class in java
    //casting
    public static <T> T getType(Object type, Class<T> classes) {
        try {
            return classes.cast(type);
        } catch (ClassCastException ex) {
            return null;
        }
    }
}
