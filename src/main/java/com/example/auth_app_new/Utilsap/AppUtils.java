package com.example.auth_app_new.Utilsap;

import java.util.Random;

public class AppUtils {
    public static String generateCode(){
        return String.valueOf(new Random().nextInt(0,9999));
    }
}
