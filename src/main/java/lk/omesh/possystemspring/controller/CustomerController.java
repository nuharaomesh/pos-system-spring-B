package lk.omesh.possystemspring.controller;

import lk.omesh.possystemspring.customStatudCode.SelectedCustomerErrorCode;
import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.dto.impl.CustomerDTO;
import lk.omesh.possystemspring.exception.CustomerNotFoundException;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.CustomerService;
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
    @Autowired
    private Validator mvcValidator;

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
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{customerID}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerID") String customerID, @RequestBody CustomerDTO dto)  {
        try {
            customerService.updateCustomer(customerID, dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerID") String customerID) {
        try {
            customerService.deleteCustomer(customerID);
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
