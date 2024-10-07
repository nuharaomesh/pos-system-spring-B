package lk.omesh.possystemspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    private String itemID;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    @Column(columnDefinition = "LONGTEXT")
    private String img;

    @OneToMany(mappedBy = "item")
    private List<OrderDetails> orderDetails;
}
