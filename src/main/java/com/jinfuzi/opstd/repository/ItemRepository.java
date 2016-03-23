package com.jinfuzi.opstd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinfuzi.opstd.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
