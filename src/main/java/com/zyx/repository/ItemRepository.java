package com.zyx.repository;


import com.zyx.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    List<Item> findAllByOrderByIdDesc();
}
