package lk.omesh.possystemspring.controller;

import lk.omesh.possystemspring.customStatudCode.SelectedCustomerErrorCode;
import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.dto.impl.CustomerDTO;
import lk.omesh.possystemspring.exception.CustomerNotFoundException;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping(value = "/{customerID}")
    public CustomerStatus getCustomer(@PathVariable("customerID") String ID) {
        if (ID.isEmpty() || ID == null)
            return new SelectedCustomerErrorCode(1, "Customer ID is Not Valid!");
        return customerService.getCustomer(ID);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getALLCustomers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDTO dto) {

        try {
            customerService.saveCustomer(dto);
            log.info("Customer Successfully Saved!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            log.info("Customer Not Saved: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerID") String customerID, @RequestBody CustomerDTO dto)  {
        try {
            customerService.updateCustomer(customerID, dto);
            log.info("Customer Successfully Updated!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            log.info("Customer Not Updated: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerID") String customerID) {
        try {
            customerService.deleteCustomer(customerID);
            log.info("Customer Successfully Deleted!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            log.info("Customer Not Deleted: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
