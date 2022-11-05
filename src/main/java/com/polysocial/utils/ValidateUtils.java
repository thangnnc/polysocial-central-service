package com.polysocial.utils;

import java.util.List;

public class ValidateUtils {

    public static boolean isNullOrEmpty(Object obj){
        boolean result = false;
        if(obj == null){
            return true;
        }

        if(obj instanceof String){
            if(((String) obj).equalsIgnoreCase("")){
                result = true;
            }
        }

        if(obj instanceof List<?>){
            if(((List<?>) obj).size() == 0){
                result = true;
            }
        }
        return result;
    }

    public static boolean isNotNullOrEmpty(Object obj){
        return !isNullOrEmpty(obj);
    }

}
