package lk.omesh.possystemspring.util;

import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.dto.impl.CustomerDTO;
import lk.omesh.possystemspring.entity.impl.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public Customer toCustomerEntity(CustomerDTO dto) {
        return modelMapper.map(dto, Customer.class);
    }

    public CustomerDTO toCustomerDTO(Customer entity) {
        return modelMapper.map(entity, CustomerDTO.class);
    }

    public List<CustomerDTO> getCustomerDTOList(List<Customer> customerList) {
        return modelMapper.map(customerList, new TypeToken<List<CustomerDTO>>(){}.getType());
    }
}
