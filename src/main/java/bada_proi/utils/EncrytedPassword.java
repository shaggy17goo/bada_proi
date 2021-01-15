package bada_proi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPassword {
    // Encryte Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(password + " -> "+ encoder.encode(password));
        System.out.println(password + " -> "+ encoder.encode(password));
        System.out.println(password + " -> "+ encoder.encode(password));
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "123";
        String encrytedPassword = encryptPassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);
    }
}
