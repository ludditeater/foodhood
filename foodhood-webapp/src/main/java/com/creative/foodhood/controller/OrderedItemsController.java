package com.creative.foodhood.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.foodhood.dao.OrderDetailsDao;
import com.creative.foodhood.entity.OrderedItemEntity;
import com.creative.foodhood.model.OrderHistoryItem;
import com.creative.foodhood.model.OrderHistorySubItem;
import com.creative.foodhood.model.OrderItem;

@RestController
@RequestMapping("/itemsOrdered")
public class OrderedItemsController {
	@Autowired
	private OrderDetailsDao orderDetailsDao;

	@RequestMapping(method = RequestMethod.POST, value = "/createOrder")
	public boolean createOrders(@RequestBody List<OrderItem> orderedItems) {
		Map<String, List<OrderedItemEntity>> orderedItemsByChefMap = new HashMap<>();
		for (OrderItem order : orderedItems) {
			OrderedItemEntity orderEntity = new OrderedItemEntity();
			BeanUtils.copyProperties(order, orderEntity);
			orderEntity.setOrderItemDetailId(UUID.randomUUID().toString());
			if (orderedItemsByChefMap.get(orderEntity.getChefName()) != null) {
				orderedItemsByChefMap.get(orderEntity.getChefName()).add(orderEntity);
			} else {
				List<OrderedItemEntity> orderedEntityList = new ArrayList<>();
				orderedEntityList.add(orderEntity);
				orderedItemsByChefMap.put(orderEntity.getChefName(), orderedEntityList);
			}
		}

		for (List<OrderedItemEntity> orderedItemEntityList : orderedItemsByChefMap.values()) {
			String orderId = UUID.randomUUID().toString();
			for (OrderedItemEntity orderDetailEntity : orderedItemEntityList) {
				orderDetailEntity.setOrderId(orderId);
				orderDetailEntity.setOrderItemDetailId(orderId);
				orderDetailsDao.save(orderDetailEntity);
			}
		}
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/historyByUser")
	public List<OrderHistoryItem> retrieveMenuItemsByUser(String username) {
		List<OrderedItemEntity> orderItemEntityList = orderDetailsDao.fetchOrderHistoryByUsername(username);
		return createOrderHistoryItemList(orderItemEntityList);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getHistoryForChef")
	public List<OrderHistoryItem> retrieveOrdersForChef(String chefId) {
		List<OrderedItemEntity> orderItemEntityList = orderDetailsDao.fetchOrderHistoryForChef(chefId);
		return createOrderHistoryItemList(orderItemEntityList);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePaymentByOrder")
	public void updatePaymentStatusOfOrder(String orderId, String paymentStatus) {
		List<OrderedItemEntity> orderItemIdsList = orderDetailsDao.retrieveOrderItemDetailIdByOrderId(orderId);
		for(OrderedItemEntity order : orderItemIdsList){
			order.setPaymentStatus(paymentStatus);
		}
		orderDetailsDao.save(orderItemIdsList);
	}

	private List<OrderHistoryItem> createOrderHistoryItemList(List<OrderedItemEntity> orderItemEntityList) {
		List<OrderHistoryItem> orderList = new ArrayList<>();
		Map<String, OrderHistoryItem> orderItemMap = new HashMap<>();
		Map<String, Integer> orderIdPriceMap = new HashMap<>();
		for (OrderedItemEntity orderEntity : orderItemEntityList) {
			OrderHistoryItem order = orderItemMap.get(orderEntity.getOrderId());
			if (order == null) {
				order = new OrderHistoryItem();
				BeanUtils.copyProperties(orderEntity, order);
				OrderHistorySubItem individualItem = new OrderHistorySubItem();
				individualItem.setQuantity(orderEntity.getQuantity());
				individualItem.setItemName(orderEntity.getItemName());
				individualItem.setChefItemAvailableTill(orderEntity.getChefItemAvailableTill());
				individualItem.setTotalPrice(orderEntity.getTotalPrice());
				List<OrderHistorySubItem> individualItemList = new ArrayList<>();
				individualItemList.add(individualItem);
				order.setOrderEachHistoryItems(individualItemList);
				orderItemMap.put(orderEntity.getOrderId(), order);
				orderIdPriceMap.put(orderEntity.getOrderId(), Integer.valueOf(orderEntity.getTotalPrice()));
				order.setTotalAmount(orderEntity.getTotalPrice());
			} else {
				OrderHistorySubItem individualItem = new OrderHistorySubItem();
				individualItem.setQuantity(orderEntity.getQuantity());
				individualItem.setItemName(orderEntity.getItemName());
				individualItem.setChefItemAvailableTill(orderEntity.getChefItemAvailableTill());
				individualItem.setTotalPrice(orderEntity.getTotalPrice());
				order.getOrderEachHistoryItems().add(individualItem);
				orderItemMap.put(orderEntity.getOrderId(), order);
				Integer prevTotalPrice = Integer.valueOf(orderIdPriceMap.get(orderEntity.getOrderId()));
				Integer currTotalPrice = prevTotalPrice + Integer.valueOf(orderEntity.getTotalPrice());
				orderIdPriceMap.put(orderEntity.getOrderId(), currTotalPrice);
				order.setTotalAmount(currTotalPrice.toString());

			}
		}
		orderList = new ArrayList<OrderHistoryItem>(orderItemMap.values());
		return orderList;
	}
}
