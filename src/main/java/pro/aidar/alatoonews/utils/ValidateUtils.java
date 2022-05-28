package pro.aidar.alatoonews.utils;

import java.util.regex.Pattern;

public class ValidateUtils {

    public static boolean isPasswordValid(String name){
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", name);
    }

    public static boolean isUsernameValid(String username){
        return Pattern.matches("^.{4,}$", username);
    }

}
