package lk.omesh.possystemspring.dao;

import lk.omesh.possystemspring.entity.impl.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, String> {
}
