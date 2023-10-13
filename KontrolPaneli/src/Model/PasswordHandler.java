package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHandler {
	
	
	
	public static String hashString(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Update the digest with the input string
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, e.g., log an error
            e.printStackTrace();
            return null;
        }
    }

	public static boolean compare(String encryptedStr) {
		ConfigHandler configHandler = new ConfigHandler();
		String str1 = configHandler.getPassword();
		if(str1.equals(encryptedStr)) {
			configHandler.setAccessFlag(1);
			return true;
		}
		
		return false;
	}
}
