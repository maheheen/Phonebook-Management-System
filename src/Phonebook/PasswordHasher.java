package Phonebook;
import java.security.MessageDigest;

public class PasswordHasher {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // Use MD5 algorithm
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // Convert byte to hexadecimal
            }

            return sb.toString(); // Return the hashed password as a string
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
