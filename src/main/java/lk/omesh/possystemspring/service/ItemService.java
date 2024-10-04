package lk.omesh.possystemspring.service;

import lk.omesh.possystemspring.dto.ItemStatus;
import lk.omesh.possystemspring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemStatus getItem(String ID);
    List<ItemDTO> getALLItems();
    void saveItem(ItemDTO dto);
    void updateItem(String ID, ItemDTO dto);
    void deleteItem(String ID);
}
