package pro.aidar.alatoonews.utils;

public class ValidateUtils {

    public static boolean isPasswordValid(String password){
        return password.length() >= 6;
    }
}
