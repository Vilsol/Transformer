package me.vilsol.transformer.utils;

public class Utils {

    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (Exception ignored){
        }

        return false;
    }

}
