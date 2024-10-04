package lk.omesh.possystemspring.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String img;

    @OneToMany(mappedBy = "item")
    private List<OrderDetails> orderDetails;
}
