package lk.omesh.possystemspring.dao;

import lk.omesh.possystemspring.entity.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, String> {}
