package lk.omesh.possystemspring.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lk.omesh.possystemspring.dao.ItemDAO;
import lk.omesh.possystemspring.dao.OrderDAO;
import lk.omesh.possystemspring.dao.OrderDetailsDAO;
import lk.omesh.possystemspring.dto.impl.OrderDTO;
import lk.omesh.possystemspring.entity.impl.Item;
import lk.omesh.possystemspring.entity.impl.Order;
import lk.omesh.possystemspring.entity.impl.OrderDetails;
import lk.omesh.possystemspring.entity.impl.OrderDetailsID;
import lk.omesh.possystemspring.exception.DataPersistException;
import lk.omesh.possystemspring.exception.ItemNotFoundException;
import lk.omesh.possystemspring.service.OrderService;
import lk.omesh.possystemspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @Autowired
    private Mapping mapping;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveItem(String jsonList) throws JsonProcessingException {

        List<Object> objectList = objectMapper.readValue(jsonList, List.class);

        List<OrderDTO.Item> items = ((List<Map<String, Object>>) objectList.get(0))
                .stream()
                .map(itemMap -> objectMapper.convertValue(itemMap, OrderDTO.Item.class))
                .collect(Collectors.toList());

        Map<String, Object> orderMap = (Map<String, Object>) objectList.get(1);
        OrderDTO.Order order = objectMapper.convertValue(orderMap, OrderDTO.Order.class);

        List<OrderDTO.OrderItem> orderItems = ((List<Map<String, Object>>) objectList.get(2))
                .stream()
                .map(orderItemMap -> objectMapper.convertValue(orderItemMap, OrderDTO.OrderItem.class))
                .collect(Collectors.toList());

        System.out.println(items);
        System.out.println(order);
        System.out.println(orderItems);

        updateItem(items);
        saveOrder(order);
        saveOrderDetails(orderItems);

    }

    private void updateItem(List<OrderDTO.Item> items) {
        for (OrderDTO.Item itemDTO : items) {
            Item itemEntity =
                    itemDAO.findById(itemDTO.getItemID())
                            .orElseThrow(() -> new ItemNotFoundException("Item not found: " + itemDTO.getItemID())
            );
            itemEntity.setQty(itemDTO.getQty());
            itemDAO.save(itemEntity);
        }
    }

    private void saveOrder(OrderDTO.Order order) {
        Order savedOrder = orderDAO.save(mapping.toOrderEntity(order));
        if (savedOrder == null)
            throw new DataPersistException("Item Not Saved!");
    }

    private void saveOrderDetails(List<OrderDTO.OrderItem> orderItems) {

        for (OrderDTO.OrderItem orderItem: orderItems) {
            OrderDetails tmpOrder = mapping.toOrderDetailEntity(orderItem);
            tmpOrder.setOrderDetailsID(new OrderDetailsID(orderItem.getOrderID(), orderItem.getItemID()));
            orderDetailsDAO.save(tmpOrder);
        }
    }
}
