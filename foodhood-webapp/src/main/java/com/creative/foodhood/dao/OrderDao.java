package com.creative.foodhood.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.creative.foodhood.entity.OrderedEntity;
import com.creative.foodhood.entity.OrderedItemEntity;

public interface OrderDao extends CrudRepository<OrderedEntity, String> {

	@Query("Select * from order_item_details where customerId=?0")
	public List<OrderedEntity> fetchMenuItemByUsername(String customerId);
}
