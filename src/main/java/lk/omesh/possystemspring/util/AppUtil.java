package lk.omesh.possystemspring.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCustomerID() {
        return "CUSTOMER-" + UUID.randomUUID();
    }
    public static String profilePicToBase64(byte[] profilePic) {
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
