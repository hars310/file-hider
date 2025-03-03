package service;

import java.util.Random;

public class OTPgenerate {
    public static String getOTP(){
        Random random = new Random();
        // generate a random number between 0 and 9999
        String otp =  String.format("%04d", random.nextInt(10000));
        return otp;
    }

}



