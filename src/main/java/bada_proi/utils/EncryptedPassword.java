package bada_proi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

public class EncryptedPassword {
    // Encrypt Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encryptPassword(password);
        //Date date = new Date(2010, 13, 2);
        //System.out.println(date.toString());

        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
