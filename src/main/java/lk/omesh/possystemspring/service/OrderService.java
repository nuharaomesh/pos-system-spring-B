package lk.omesh.possystemspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    void placeOrder(String jsonList) throws JsonProcessingException;
}
