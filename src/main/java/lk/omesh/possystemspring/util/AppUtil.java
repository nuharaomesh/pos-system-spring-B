package lk.omesh.possystemspring.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCustomerID() {
        return "CUSTOMER-" + UUID.randomUUID();
    }

    public static String generateItemID() {
        return "ITEM-" + UUID.randomUUID();
    }

    public static String itemPicToBase64(byte[] itemPic) {
        return Base64.getEncoder().encodeToString(itemPic);
    }
}
