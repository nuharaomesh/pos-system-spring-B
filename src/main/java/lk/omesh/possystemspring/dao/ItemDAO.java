package lk.omesh.possystemspring.dao;

import lk.omesh.possystemspring.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<Item, String> {
}
