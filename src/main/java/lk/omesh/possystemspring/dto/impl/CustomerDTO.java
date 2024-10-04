package lk.omesh.possystemspring.dto.impl;

import lk.omesh.possystemspring.dto.CustomerStatus;
import lk.omesh.possystemspring.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
    private String customerID;
    private String name;
    private Gender gender;
    private String gmail;
    private int phnNo;
}
