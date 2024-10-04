package lk.omesh.possystemspring.service;

import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerStatus getCustomer(String ID);
    List<CustomerDTO> getALLCustomers();
    void saveCustomer(CustomerDTO dto);
    void updateCustomer(String ID, CustomerDTO dto);
    void deleteCustomer(String ID);
}
