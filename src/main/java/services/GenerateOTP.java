package services;

import java.security.SecureRandom;

public class GenerateOTP {
    public static String generate(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("OTP length must be greater than 0");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Append a random digit (0-9)
        }

        return otp.toString();
    }

    public static void main(String[] args) {
        // Test the OTP generation with a length of 4
        System.out.println("Generated OTP: " + generate(4));
    }
}
