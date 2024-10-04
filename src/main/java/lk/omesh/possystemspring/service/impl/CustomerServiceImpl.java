package lk.omesh.possystemspring.service.impl;

import jakarta.transaction.Transactional;
import lk.omesh.possystemspring.customStatudCode.SelectedCustomerErrorCode;
import lk.omesh.possystemspring.dao.CustomerDAO;
import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.dto.impl.CustomerDTO;
import lk.omesh.possystemspring.entity.impl.Customer;
import lk.omesh.possystemspring.exception.CustomerNotFoundException;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.service.CustomerService;
import lk.omesh.possystemspring.util.AppUtil;
import lk.omesh.possystemspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public CustomerStatus getCustomer(String ID) {
        if (customerDAO.existsById(ID)) {
            Customer selectedCus = customerDAO.getReferenceById(ID);
            return mapping.toCustomerDTO(selectedCus);
        } else {
            return new SelectedCustomerErrorCode(2, "Customer Not Found!");
        }
    }

    @Override
    public List<CustomerDTO> getALLCustomers() {
        return mapping.getCustomerDTOList(customerDAO.findAll());
    }

    @Override
    public void saveCustomer(CustomerDTO dto) {
        dto.setCustomerID(AppUtil.generateCustomerID());
        System.out.println(dto.getGender());
        Customer savedCustomer =
                customerDAO.save(mapping.toCustomerEntity(dto));
        if (savedCustomer == null)
            throw new DataPersistException("Customer Not Saved!");
    }

    @Override
    public void updateCustomer(String ID, CustomerDTO dto) {
        Optional<Customer> tmpCustomer = customerDAO.findById(ID);
        if (!tmpCustomer.isPresent())
            throw new CustomerNotFoundException("Customer Not Found!");
        else
            tmpCustomer.get().setName(dto.getName());
            tmpCustomer.get().setGender(String.valueOf(dto.getGender()));
            tmpCustomer.get().setGmail(dto.getGmail());
            tmpCustomer.get().setPhnNo(dto.getPhnNo());
    }

    @Override
    public void deleteCustomer(String ID) {
        Optional<Customer> tmpCustomer = customerDAO.findById(ID);
        if (!tmpCustomer.isPresent())
            throw new CustomerNotFoundException("Customer Not Found!");
        else
            customerDAO.deleteById(ID);
    }
}
