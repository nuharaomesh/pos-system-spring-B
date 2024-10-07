package lk.omesh.possystemspring.controller;

import lk.omesh.possystemspring.customStatudCode.SelectedItemErrorCode;
import lk.omesh.possystemspring.dto.ItemStatus;
import lk.omesh.possystemspring.dto.impl.ItemDTO;
import lk.omesh.possystemspring.exception.CustomerNotFoundException;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.ItemService;
import lk.omesh.possystemspring.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    static Logger log = LoggerFactory.getLogger(ItemController.class);

    @GetMapping(value = "/{itemID}")
    public ItemStatus getItem(@PathVariable("itemID") String ID) {
        if (ID.isEmpty() || ID == null)
            return new SelectedItemErrorCode(1, "Item ID is Not Valid!");
        return itemService.getItem(ID);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAllItem() {
        return itemService.getALLItems();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveItem(
            @RequestPart ("category") String category,
            @RequestPart ("img") MultipartFile itemImg,
            @RequestPart ("itemName") String itemName,
            @RequestPart ("price") String price,
            @RequestPart ("qty") String qty
    ) {

        try {

            byte[] bytesItemPic = itemImg.getBytes();
            String base64ItemPic = AppUtil.itemPicToBase64(bytesItemPic);

            var buildItemDTO = new ItemDTO();

            buildItemDTO.setItemName(itemName);
            buildItemDTO.setCategory(category);
            buildItemDTO.setPrice(Float.parseFloat(price));
            buildItemDTO.setQty(Integer.parseInt(qty));
            buildItemDTO.setImg(base64ItemPic);

            itemService.saveItem(buildItemDTO);
            log.info("Item Successfully Saved!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            log.info("Item Not Saved" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Internal server error" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{itemID}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateItem(
            @PathVariable("itemID") String itemID,
            @RequestPart ("category") String category,
            @RequestPart ("img") MultipartFile itemImg,
            @RequestPart ("itemName") String itemName,
            @RequestPart ("price") String price,
            @RequestPart ("qty") String qty
    )  {
        try {

            byte[] bytesItemPic = itemImg.getBytes();
            String base64ItemPic = AppUtil.itemPicToBase64(bytesItemPic);

            var buildItemDTO = new ItemDTO();

            buildItemDTO.setItemName(itemName);
            buildItemDTO.setCategory(category);
            buildItemDTO.setPrice(Float.parseFloat(price));
            buildItemDTO.setQty(Integer.parseInt(qty));
            buildItemDTO.setImg(base64ItemPic);

            itemService.updateItem(itemID, buildItemDTO);
            log.info("Item Successfully Updated!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            log.info("Item Not Updated" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Internal server error" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{itemID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("itemID") String itemID) {
        try {
            itemService.deleteItem(itemID);
            log.info("Item Successfully Deleted!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            log.info("Item Not Deleted" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Internal server error" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
