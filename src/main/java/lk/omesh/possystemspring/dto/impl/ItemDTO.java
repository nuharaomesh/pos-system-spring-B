package lk.omesh.possystemspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemID;
    private String itemName;
    private String category;
    private float price;
    private int qty;
    private String img;
}
