package lk.omesh.possystemspring.dao;

import lk.omesh.possystemspring.entity.impl.OrderDetails;
import lk.omesh.possystemspring.entity.impl.OrderDetailsID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetails, OrderDetailsID> { }
