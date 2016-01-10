package com.creative.foodhood.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.creative.foodhood.entity.OrderedItemEntity;

public interface OrderDetailsDao extends CrudRepository<OrderedItemEntity, String> {

	@Query("Select * from order_item_details where customerId=?0")
	public List<OrderedItemEntity> fetchOrderHistoryByUsername(String customerId);
	
	@Query("Select * from order_item_details where chefid=?0")
	public List<OrderedItemEntity> fetchOrderHistoryForChef(String chefid);
	
	@Query("select * from order_item_details where orderId=?0")
	public List<OrderedItemEntity> retrieveOrderItemDetailIdByOrderId(String orderId);
}
