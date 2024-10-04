package lk.omesh.possystemspring.entity.impl;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderDetailsID {
    private String orderID;
    private String itemID;
}
