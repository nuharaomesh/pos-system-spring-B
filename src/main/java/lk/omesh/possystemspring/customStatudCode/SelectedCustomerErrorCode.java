package lk.omesh.possystemspring.customStatudCode;

import lk.omesh.possystemspring.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SelectedCustomerErrorCode implements CustomerStatus {
    private int statusCode;
    private String statusMessage;
}
