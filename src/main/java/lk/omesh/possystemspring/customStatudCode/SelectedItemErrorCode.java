package lk.omesh.possystemspring.customStatudCode;

import lk.omesh.possystemspring.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SelectedItemErrorCode implements ItemStatus {
    private int statusCode;
    private String statusMessage;
}
