package lk.omesh.possystemspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsID orderDetailsID;
    private float price;
    private int counts;

    @ManyToOne
    @MapsId("orderID")
    @JoinColumn(name = "orderID")
    private Order order;

    @ManyToOne
    @MapsId("itemID")
    @JoinColumn(name = "itemID")
    private Item item;
}
