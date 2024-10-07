package lk.omesh.possystemspring.controller;

import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    static Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody String jsonList) {

        try {
            orderService.placeOrder(jsonList);
            log.info("Order Successfully Saved!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            log.info("Order Not Saved" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Internal Server error" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
