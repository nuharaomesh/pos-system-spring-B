package lk.omesh.possystemspring.service.impl;

import jakarta.transaction.Transactional;
import lk.omesh.possystemspring.customStatudCode.SelectedItemErrorCode;
import lk.omesh.possystemspring.dao.ItemDAO;
import lk.omesh.possystemspring.dto.ItemStatus;
import lk.omesh.possystemspring.dto.impl.ItemDTO;
import lk.omesh.possystemspring.entity.impl.Item;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.exception.ItemNotFoundException;
import lk.omesh.possystemspring.service.ItemService;
import lk.omesh.possystemspring.util.AppUtil;
import lk.omesh.possystemspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public ItemStatus getItem(String ID) {
        if (itemDAO.existsById(ID))
           return mapping.toItemDTO(itemDAO.getReferenceById(ID));
        else
            return new SelectedItemErrorCode(2, "Item Not Found!");
    }

    @Override
    public List<ItemDTO> getALLItems() {
        return mapping.getItemDTOList(itemDAO.findAll());
    }

    @Override
    public void saveItem(ItemDTO dto) {
        dto.setItemID(AppUtil.generateItemID());
        Item savedItem =
                itemDAO.save(mapping.toItemEntity(dto));
        if (savedItem == null)
            throw new DataPersistException("Item Not Saved!");
    }

    @Override
    public void updateItem(String ID, ItemDTO dto) {
        Optional<Item> tmpItem = itemDAO.findById(ID);
        if (!tmpItem.isPresent())
            throw new ItemNotFoundException("Item Not Found!");
        else
            tmpItem.get().setItemName(dto.getItemName());
            tmpItem.get().setCategory(dto.getCategory());
            tmpItem.get().setPrice(dto.getPrice());
            tmpItem.get().setQty(dto.getQty());
            tmpItem.get().setImg(dto.getImg());
    }

    @Override
    public void deleteItem(String ID) {
        Optional<Item> tmpItem = itemDAO.findById(ID);
        if (!tmpItem.isPresent())
            throw new ItemNotFoundException("Item Not Found!");
        else
            itemDAO.deleteById(ID);
    }
}
