package lk.omesh.possystemspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private List<Item> items;
    private Order order;
    private List<OrderItem> orderItems;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item {
        private String itemID;
        private int qty;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Order {
        private String orderID;
        private float price;
        private String time;
        private int qty;
        private String customerID;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class OrderItem {
        private String orderID;
        private String itemID;
        private double price;
        private int counts;
    }
}
