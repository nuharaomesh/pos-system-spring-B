package lk.omesh.possystemspring.dto.impl;

import lk.omesh.possystemspring.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStatus {
    private String itemID;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    private String img;
}
