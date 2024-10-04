package lk.omesh.possystemspring.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lk.omesh.possystemspring.customStatudCode.SelectedItemErrorCode;
import lk.omesh.possystemspring.dto.ItemStatus;
import lk.omesh.possystemspring.dto.impl.ItemDTO;
import lk.omesh.possystemspring.exception.CustomerNotFoundException;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.ItemService;
import lk.omesh.possystemspring.util.AppUtil;
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
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
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
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{itemID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("itemID") String itemID) {
        try {
            itemService.deleteItem(itemID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
