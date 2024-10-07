package lk.omesh.possystemspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OrderService {
    void saveItem(String jsonList) throws JsonProcessingException;
}
