package com.nguyendan.footballstanding.data.model.matches;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

public class ParseData {


    public static String getScore(Object object){
        if(object==null){
            return "";
        }else {
            String s = object.toString();
            s = s.substring(0,s.length()-2);
            return s;
        }
    }
}
